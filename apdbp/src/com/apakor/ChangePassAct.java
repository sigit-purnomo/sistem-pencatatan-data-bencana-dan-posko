package com.apakor;

import java.util.zip.Checksum;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassAct extends Activity {
	
	public static final String MYPREFS = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	SharedPreferences prefs;
	
	Button ubah, batal, resetPass;
	EditText edtxtPasswordLama, edtxtPasswordBaru, edtxtVerPasswordBaru;
	String pass, passLama, passBaru, verPassBaru;
	CheckBox cekShow;
	
	private static final int DIALOG1 = 1;
	private static final int DIALOG2 = 2; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pass);
		getActionBar().setTitle("Ubah Password");
		initiateElement();
        loadPreferences();
	}
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		
		edtxtPasswordLama = (EditText) findViewById(R.id.editTextPasswordLama);
		edtxtPasswordBaru = (EditText) findViewById(R.id.editTextPasswordBaru);
		edtxtVerPasswordBaru = (EditText) findViewById(R.id.editTextVerPasswordBaru);
		cekShow = (CheckBox) findViewById(R.id.checkBoxShowPass);
		ubah = (Button) findViewById(R.id.buttonUbah);
		batal = (Button) findViewById(R.id.buttonBatal);
		resetPass = (Button) findViewById(R.id.buttonResetPass);
		
		ubah.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DIALOG2);
			}
		});
		
		batal.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangePassAct.this.finish();
			}
		});
		
		resetPass.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DIALOG1);
			}
		});
		
		cekShow.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked==true)
				{
					edtxtVerPasswordBaru.setTransformationMethod(null);
					edtxtPasswordBaru.setTransformationMethod(null);
					edtxtPasswordLama.setTransformationMethod(null);
				}
				else
				{
					edtxtVerPasswordBaru.setTransformationMethod(new PasswordTransformationMethod());
					edtxtPasswordBaru.setTransformationMethod(new PasswordTransformationMethod());
					edtxtPasswordLama.setTransformationMethod(new PasswordTransformationMethod());
					
				}
			}
		});
	}
	
	private Dialog createDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Mereset Ulang Password?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				resetPassword();
				ChangePassAct.this.finish();
			}
		});
		
		builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {	
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		return builder.create();
	}
	
	private Dialog createDialog2(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Mengubah Password?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				pass = edtxtPasswordLama.getText().toString();
				passBaru = edtxtPasswordBaru.getText().toString();
				verPassBaru = edtxtVerPasswordBaru.getText().toString();
				
				if(pass.equalsIgnoreCase("")||passBaru.equalsIgnoreCase("")||verPassBaru.equalsIgnoreCase(""))//Inputan masih kosong
				{
					Toast.makeText(getApplicationContext(), "Ubah Password Gagal! Tolong Lengkapi Inputan Anda", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(passBaru.equalsIgnoreCase(verPassBaru)) //Verifikasi Cocok
					{
						if(pass.equalsIgnoreCase(passLama))//Pass Lama Sesuai
						{
							savePreferences();
							Toast.makeText(getApplicationContext(), "Ubah Password Berhasil!", Toast.LENGTH_SHORT).show();
							ChangePassAct.this.finish();
						}
						else//Pass Lama Tidak Sesuai
						{
							Toast.makeText(getApplicationContext(), "Ubah Password Gagal! Password Lama Anda Salah", Toast.LENGTH_SHORT).show();
						}
					}
					else//Verifikasi gagal
					{
						Toast.makeText(getApplicationContext(), "Ubah Password Gagal! Inputan Password Baru Dan Verifikasi Password Baru Tidak Sama", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {	
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		return builder.create();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG1:
			return createDialog1(ChangePassAct.this);
		case DIALOG2:
			return createDialog2(ChangePassAct.this);
		default:
			break;
		}
		return null;
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
    	mySharedPreferences = getApplication().getSharedPreferences(MYPREFS,
				mode);		
		passLama = mySharedPreferences.getString("password", "apakor");
	}
	
	private void resetPassword() {
		// TODO Auto-generated method stub
    	Editor editor = mySharedPreferences.edit();
		editor.putString("password", "apakor");
		
		editor.commit();
		Toast.makeText(getApplicationContext(), "Reset Password Berhasil!", Toast.LENGTH_SHORT).show();
	}	
    
    private void savePreferences() {
		// TODO Auto-generated method stub
    	Editor editor = mySharedPreferences.edit();
		editor.putString("password", passBaru);
		
		editor.commit();
	}	
}
