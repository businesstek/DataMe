package com.bt.datame;

import java.io.BufferedReader;
import java.io.IOException;

import android.util.Log;

public class SetCmd extends CmdExec {
	private String cmd;

	private static final String TAG = "DataMe SetCmd";

	public SetCmd(String cmd) {
		// initialize process
		initProcess();
		this.cmd = cmd;
	}

	public String[] set() {
		Log.v(TAG, " set cmd");
		exec(cmd);
		String[] buf = readBuffer(br);
		//String[] buf = {"o","k","\0"};
		// terminate process
		terminateProcess();
		return buf;
	}

	static String[] readBuffer(BufferedReader br) {
        Log.v(TAG," read result");
		String line = null;
		String[] result = new String[1];
		try {
			while (!br.ready());
			line=br.readLine();
		} catch (IOException e) {
			result[0] = e.toString();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					result[0] = e.toString();
				}
			}
		}

		result[0] = line;
		return result;
	}

}
