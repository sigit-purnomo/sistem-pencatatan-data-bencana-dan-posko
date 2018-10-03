package com.apakor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeSMSGateAct extends Activity{
	
	private static final int DIALOG1 = 1;
	
	public static final String MYPREFS = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	SharedPreferences prefs;
	String nomorSMSGateway;
	EditText edtxtNomorSMSGateway;
	Button save, cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_tujuan_sms);
		
		getActionBar().setTitle("Ubah SMS Gateaway");
		
		edtxtNomorSMSGateway = (EditText) findViewById(R.id.editTextNomorSMSGateaway);
		save = (Button) findViewById(R.id.buttonUbah);
		cancel = (Button) findViewById(R.id.buttonBatal);
		loadPreferences();
		setBehaviour();
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(MYPREFS,
				mode);		
		nomorSMSGateway = mySharedPreferences.getString("nomorSMSGateway", "Belum diset");
		setElement();
	}

	private void setElement() {
		// TODO Auto-generated method stub
		edtxtNomorSMSGateway.setText(nomorSMSGateway);
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		editor.putString("nomorSMSGateway", edtxtNomorSMSGateway.getText().toString());
		
		editor.commit();
	}
	
	private Dialog createDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Akan Mengubah Nomor Tujuan SMS Gateway?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(edtxtNomorSMSGateway.getText().toString().equalsIgnoreCase("Belum diset")||
						edtxtNomorSMSGateway.getText().toString().equalsIgnoreCase("")||
						edtxtNomorSMSGateway.getText().length()<=0)
				{
					Toast.makeText(getApplicationContext(), "Mohon Isi Nomor Tujuan SMS Gateway", Toast.LENGTH_SHORT).show();
				}
				else if(edtxtNomorSMSGateway.getText().length()<10 || edtxtNomorSMSGateway.getText().length()>13)
				{
					Toast.makeText(getApplicationContext(), "Mohon Isikan Inputan Valid", Toast.LENGTH_SHORT).show();
				}
				else
				{
					savePreferences();
					Toast.makeText(getBaseContext(), "Nomor Tujuan SMS Gateway Berhasil Diubah", Toast.LENGTH_SHORT).show();
					finish();
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
			return createDialog1(ChangeSMSGateAct.this);
		default:
			break;
		}
		return null;
	}
	
	private void setBehaviour() {
		// TODO Auto-generated method stub
		save.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DIALOG1);
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
