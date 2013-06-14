package com.bt.datame;

/**
 *         This Class starts a process, executes an su and a command, then terminates the process
 *         @param String command
 *         @param Boolean result
 *         
 *         			//Example of how to send command can expect result or not
        			String shell = LIB_ENV;
        			SetCmd sc = new SetCmd();
        			String[] result = sc.set(shell, true);
        			if (!result[0].contains(LIB_PATH)){
        				sc = new SetCmd();
        				shell = LIB_CMD;
            			result = sc.set(shell, false);
            			Log.v(TAG," return from cmd " + result);
        			}
        			else {
        				Log.v(TAG, shell);
        			}
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class SetCmd {
	static String[] readBuffer(BufferedReader br) {
		Log.v(TAG, " read result");
		String line = null;
		final String[] result = new String[1];
		try {
			line = br.readLine();
		} catch (final IOException e) {
			result[0] = e.toString();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (final IOException e) {
					result[0] = e.toString();
				}
			}
		}

		result[0] = line;
		return result;
	}

	protected java.lang.Process process = null;
	protected DataOutputStream os = null;
	protected DataInputStream is = null;

	protected BufferedReader br = null;

	private static final String TAG = "DataMe SetCmd";

	public SetCmd() {

	}

	protected void exec(String cmd) {
		try {
			Log.v(TAG, " exec");
			os.writeBytes(cmd + '\n');
			os.flush();
		} catch (final Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
	}

	protected void initProcess() {
		try {
			Log.v(TAG, " init process");
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			is = new DataInputStream(process.getInputStream());
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			Log.v(TAG, " init process cmd done");
		} catch (final Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
	}

	public String[] set(String cmd, Boolean response) {
		final String[] buf;
		initProcess();
		exec(cmd);
		if(response){
			buf = readBuffer(br);
		}
		else{
			buf = new String[1];
		}
		terminateProcess();
		return buf;
	}

	protected void terminateProcess() {
		int i = 99; //var used to get result if process ended
		try {
			exec("exit");
			i = process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} finally {
			if (process != null) {
				process.destroy();
			}
		}

	}

}
