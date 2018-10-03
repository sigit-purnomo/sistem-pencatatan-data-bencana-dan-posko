package com.apakor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAct extends Activity {
	
	public static final String MYPREFS = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	SharedPreferences prefs;
	
	Intent i;
	EditText editTxtUsername, editTxtPassword;
	Button login, keluar;
	String password;
//	String username;
	boolean session;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        getActionBar().setTitle("Login");
        initiateElement();
        loadPreferences();
        
        if(session)//Misal session aktif langsung masuk main
		{
        	i = new Intent(LoginAct.this,MenuUtamaAct.class);
			startActivity(i);
			LoginAct.this.finish();
		}
        else//Misal session tidak aktif login dulu
        {
            login.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
//    				if(editTxtUsername.getText().toString().equalsIgnoreCase("") ||
    				if(editTxtPassword.getText().toString().equalsIgnoreCase(""))
    				{
    					Toast.makeText(getApplicationContext(), "Login Gagal! Mohon Lengkapi Password Anda", Toast.LENGTH_SHORT).show();
    				}
    				else
    				{
//    					if(editTxtUsername.getText().toString().equalsIgnoreCase(username)&&
        				if(editTxtPassword.getText().toString().equalsIgnoreCase(password))//kondisi username password betul
        				{
        					session = true;
        					
        					savePreferences();
        					Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_SHORT).show();
        					i = new Intent(LoginAct.this,MenuUtamaAct.class);
            				startActivity(i);
            				LoginAct.this.finish();
        				}
        				else
        				{
        					Toast.makeText(getApplicationContext(), "Login Gagal! Cek Kembali Password Anda", Toast.LENGTH_SHORT).show();
        				}
    				}
    			}
    		});
            
            keluar.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoginAct.this.finish();
				}
			});
        }  
    }
    
    private void initiateElement()
    {
//    	editTxtUsername = (EditText) findViewById(R.id.editTextUsername);
    	editTxtPassword = (EditText) findViewById(R.id.editTextPassword);
    	login = (Button) findViewById(R.id.buttonLogin);
    	keluar = (Button) findViewById(R.id.buttonKeluar);
    }
    
    private void loadPreferences() {
		// TODO Auto-generated method stub
    	mySharedPreferences = getApplication().getSharedPreferences(MYPREFS,
				mode);		
//		username = mySharedPreferences.getString("username", "apakor");
		password = mySharedPreferences.getString("password", "apakor");
		session = mySharedPreferences.getBoolean("sesion", false);
	}
    
    private void savePreferences() {
		// TODO Auto-generated method stub
    	Editor editor = mySharedPreferences.edit();
		editor.putBoolean("sesion", session);
		
		editor.commit();
	}
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	loadPreferences();
    }
}
