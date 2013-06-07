package com.bt.datame;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class CmdExec {

	protected java.lang.Process process = null;
	protected DataOutputStream os = null;
	protected DataInputStream is = null;
	protected BufferedReader br = null;

	private static final String TAG = "DataMe CmdExec";

	protected CmdExec() {

	}

	protected void initProcess() {
		try {
            Log.v(TAG," init process");
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			is = new DataInputStream(process.getInputStream());
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			Log.v(TAG, " init process cmd done");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
	}

	protected void terminateProcess() {

		try {
			exec("exit");
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} finally {
			if (process != null)
				process.destroy();
		}

	}

	protected void exec(String cmd) {
		try {
            Log.v(TAG," exec");
			os.writeBytes(cmd + '\n');
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
	}
}
