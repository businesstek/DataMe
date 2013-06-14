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
	private static final String R_CMD = "R -f /data/local/bt/bt1.R";
	private static final String SH_CMD = "/data/local/bt/bt.sh";
	
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
                //Send Shell Command
    			String shell = SH_CMD;
    			SetCmd sc = new SetCmd();
    			String[] result = sc.set(shell, false);
        		Log.v(TAG, shell);

                //display R command;
        			tb1.setText(R_CMD);
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
