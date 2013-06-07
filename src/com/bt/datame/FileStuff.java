package com.bt.datame;

import java.io.*;
import android.util.Log;

public class FileStuff {
	private static final String TAG = "DataMe FileStuff";

	static String readFromFile(String fileName) {
        Log.v(TAG," Read Filer");
		String ret = "";

		try {
			File file = new File(fileName);
			InputStream in = null;

			try {
				in = new BufferedInputStream(new FileInputStream(file));
			}
			finally {
				if ( in != null ) {
					InputStreamReader inputStreamReader = new InputStreamReader(in);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String receiveString = "";
					StringBuilder stringBuilder = new StringBuilder();

					while ( (receiveString = bufferedReader.readLine()) != null ) {
						stringBuilder.append(receiveString);
					}

					in.close();
					ret = stringBuilder.toString();
				}
			}
		}
		catch (FileNotFoundException e) {
			Log.e(TAG, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(TAG, "Can not read file: " + e.toString());
		}

		return ret;

	}
}