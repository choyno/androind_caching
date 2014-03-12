package com.example.caching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private String TEMP_FILE_NAME; 
	 EditText etContent;
	 File tempFile;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		  /** Getting reference to btn_save of the layout activity_main */
        Button btnSave = (Button) findViewById(R.id.btn_save);
       
        /** Defining click event listener for the button btn_save */
        OnClickListener saveClickListener = new OnClickListener() {
 
            @Override
            public void onClick(View v) {  
                fileChecker();
                checkWriter(); 
            }

			
        };
 
        /** Setting the click event listener for the button btn_save */
        btnSave.setOnClickListener(saveClickListener);
    }
	
	private void checkWriter() { 
        try {
        	FileWriter writer=null;
            writer = new FileWriter(tempFile); 
            /** Saving the contents to the file*/
            EditText etContent  = (EditText) findViewById(R.id.heelo_text); 
            writer.write(etContent.getText().toString()); 
            /** Closing the writer object */
            writer.close();

            Toast.makeText(getBaseContext(), "Temporarily saved contents in " + tempFile.getPath(), Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	public void fileChecker(){ 
		   etContent  = (EditText) findViewById(R.id.heelo_text); 
           String name = etContent.getText().toString(); 
           TEMP_FILE_NAME =  ("dmm_"+ name + ".txt"); 
           File cDir = getBaseContext().getCacheDir();  
           tempFile = new File(cDir.getPath() + "/" + TEMP_FILE_NAME) ;
    
           String strLine="";
           StringBuilder text = new StringBuilder();
    
           /** Reading contents of the temporary file, if already exists */
           try {
               FileReader fReader = new FileReader(tempFile);
               BufferedReader bReader = new BufferedReader(fReader);
    
               /** Reading the contents of the file , line by line */
               while( (strLine=bReader.readLine()) != null  ){
                   text.append(strLine+"\n");
               }
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }catch(IOException e){
               e.printStackTrace();
           }
           /** Setting content from file */
           etContent.setText(text);
    
		
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
