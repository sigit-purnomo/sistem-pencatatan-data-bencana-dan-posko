package com.apakor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LapAwalBencanaAct_2 extends Activity implements OnClickListener {

	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	
	private static final int DIALOG1 = 1; 
	
	Button lanjut, kembali;
	EditText edtxtDusun, edtxtDesa, edtxtKecamatan, edtxtKabupaten, edtxtPenyebabBencana;
	
	String lokasiDusunBencana, lokasiDesaBencana, lokasiKecamatanBencana, lokasiKabupatenBencana, penyebabBencana;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_bencana_2);
//		b = getIntent().getExtras();
		initiateElement();
		loadPreferences();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_laporan, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) {
		case R.id.menu_keluar:
			showDialog(DIALOG1);
			break;
			
		default:
			break;
		}
    	return true;
    }
	
	private void initiateElement()
    {
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		kembali = (Button) findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
		
		edtxtDusun = (EditText) findViewById(R.id.editTextLokasiDusun);
		edtxtDesa = (EditText) findViewById(R.id.editTextLokasiDesa);
		edtxtKecamatan = (EditText) findViewById(R.id.editTextLokasiKecamatan);
		edtxtKabupaten = (EditText) findViewById(R.id.editTextLokasiKabupaten);
		edtxtPenyebabBencana = (EditText) findViewById(R.id.editTextPenyebabBencana);
    }
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
	
		lokasiDesaBencana = mySharedPreferences.getString("LokasiDusunBencana", "");
		lokasiDusunBencana = mySharedPreferences.getString("LokasiDesaBencana", "");
		lokasiKecamatanBencana = mySharedPreferences.getString("LokasiKecamatanBencana", "");
		lokasiKabupatenBencana = mySharedPreferences.getString("LokasiKabupatenBencana", "");
		penyebabBencana = mySharedPreferences.getString("PenyebabBencana", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtDesa.setText(lokasiDesaBencana);
		edtxtDusun.setText(lokasiDusunBencana);
		edtxtKecamatan.setText(lokasiKecamatanBencana);
		edtxtKabupaten.setText(lokasiKabupatenBencana);
		edtxtPenyebabBencana.setText(penyebabBencana);
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("LokasiDusunBencana", edtxtDusun.getText().toString());
		editor.putString("LokasiDesaBencana", edtxtDesa.getText().toString());
		editor.putString("LokasiKecamatanBencana", edtxtKecamatan.getText().toString());
		editor.putString("LokasiKabupatenBencana", edtxtKabupaten.getText().toString());
		editor.putString("PenyebabBencana", edtxtPenyebabBencana.getText().toString());

		editor.commit();
	}
	
	public boolean cekInputanData()
	{
		boolean cek = true;
		if(edtxtDusun.getText().length()<=0||
			edtxtDusun.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Dusun!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtDesa.getText().length()<=0||
				edtxtDesa.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Desa!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKecamatan.getText().length()<=0||
				edtxtKecamatan.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Kecamatan!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKabupaten.getText().length()<=0||
				edtxtKabupaten.getText().toString().equalsIgnoreCase("")) 
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Kabupaten!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtPenyebabBencana.getText().length()<=0||
				edtxtPenyebabBencana.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Penyebab Bencana!", Toast.LENGTH_SHORT).show();
		}
		return cek;
	}
	
	private Dialog createDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Batal Membuat Laporan?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				LapAwalBencanaAct_2.this.finish();
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
			return createDialog1(LapAwalBencanaAct_2.this);
		default:
			break;
		}
		return null;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLanjut: //Lanjutkan
			if(cekInputanData()==true)
			{
//				isiData();
				savePreferences();
				i = new Intent(LapAwalBencanaAct_2.this, LapAwalBencanaAct_3.class);
				startActivity(i);
				LapAwalBencanaAct_2.this.finish();
			}
			break;
		case R.id.buttonKembali: //Batal
			i = new Intent(LapAwalBencanaAct_2.this, LapAwalBencanaAct_1.class);
			startActivity(i);
			LapAwalBencanaAct_2.this.finish();
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			i = new Intent(LapAwalBencanaAct_2.this, LapAwalBencanaAct_1.class);
			startActivity(i);
			LapAwalBencanaAct_2.this.finish();
		}
		return false;
	}

}
