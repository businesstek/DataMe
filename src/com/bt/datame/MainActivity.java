package com.bt.datame;
/* 
 * DataMe is an Android App to prototype using R on Android
 * 
 * phase 1: BusinessTek, 6/7/2013
 * R is manually installed then a R script is run auto generating data
 * and displaying in a text area by reading a text file
 * 
 * phase 2 will install R and read data from a source
 * phase 3 will graphically display the data
 * 
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class MainActivity extends Activity {
	private static final String TAG = "DataMe";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final EditText tb1 = (EditText) findViewById(R.id.txt_command);
        final EditText tb2 = (EditText) findViewById(R.id.txt_pickdata);
        final EditText tb3 = (EditText) findViewById(R.id.txt_result);
        
        Button.OnClickListener listener = new View.OnClickListener(){
        	public void onClick(View v){
        		//get file 
                Log.v(TAG," Button File Chooser");
        		
        		//execute cmd
                Log.v(TAG," Button Command");
                //can't get shell to work so send all one string
        			String shell ="export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/gcc/lib;export PATH=$PATH:/data/local/gcc/bin;ls;R -f /data/local/bt/bt1.R";
        			String[] result = new SetCmd(shell).set();
                    Log.v(TAG," return from cmd " + result);

                //display R command
        			shell = "R -f /data/local/bt/bt1.R";
        			tb1.setText(shell);
        			//shell = tb1.toString(); //later for entering command

        			tb2.setText(result[0]);
        			tb3.setText(FileStuff.readFromFile("/data/local/bt/bt1.txt"));
        	}
        };
        ((Button) findViewById(R.id.btn_command)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn_pickdata)).setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    

}
