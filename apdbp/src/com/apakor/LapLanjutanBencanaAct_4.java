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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LapLanjutanBencanaAct_4 extends Activity implements OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	
	private static final int DIALOG1 = 1; 
	
	EditText edtxtJmlhJembatanRusak, edtxtJmlhJalanRusak, edtxtJmlhTanggulRusak, edtxtJmlhSawahRusak,
	edtxtJmlhLahanRusak, edtxtJmlhLainRusak;
	String jumlahJembatanRusak, jumlahJalanRusak, jumlahTanggulRusak, jumlahSawahRusak, jumlahLahanRusak, jumlahLainLainRusak;
	
	Button lanjut, kembali;
	Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_lanjut_bencana_4);
		
		getActionBar().setTitle("Laporan Bencana");
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
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		edtxtJmlhJembatanRusak = (EditText) findViewById(R.id.editTextJumlahRusakJembatan);
		edtxtJmlhJalanRusak = (EditText) findViewById(R.id.editTextJumlahRusakJalan);
		edtxtJmlhTanggulRusak = (EditText) findViewById(R.id.editTextJumlahRusakTanggul);
		edtxtJmlhSawahRusak = (EditText) findViewById(R.id.editTextJumlahRusakSawah);
		edtxtJmlhLahanRusak = (EditText) findViewById(R.id.editTextJumlahRusakLahan);
		edtxtJmlhLainRusak = (EditText) findViewById(R.id.editTextJumlahLainLainRusak);
		
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		kembali = (Button) findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
	
		jumlahJembatanRusak = mySharedPreferences.getString("JumlahJembatanRusak", "");
		jumlahJalanRusak = mySharedPreferences.getString("JumlahJalanRusak", "");
		jumlahTanggulRusak = mySharedPreferences.getString("JumlahTanggulRusak", "");
		jumlahSawahRusak = mySharedPreferences.getString("JumlahSawahRusak", "");
		jumlahLahanRusak = mySharedPreferences.getString("JumlahLahanRusak", "");
		jumlahLainLainRusak = mySharedPreferences.getString("JumlahLainLainRusak", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtJmlhJembatanRusak.setText(jumlahJembatanRusak);
		edtxtJmlhJalanRusak.setText(jumlahJalanRusak);
		edtxtJmlhTanggulRusak.setText(jumlahTanggulRusak);
		edtxtJmlhSawahRusak.setText(jumlahSawahRusak);
		edtxtJmlhLahanRusak.setText(jumlahLahanRusak);
		edtxtJmlhLainRusak.setText(jumlahLainLainRusak);
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("JumlahJembatanRusak", edtxtJmlhJembatanRusak.getText().toString());
		editor.putString("JumlahJalanRusak", edtxtJmlhJalanRusak.getText().toString());
		editor.putString("JumlahTanggulRusak", edtxtJmlhTanggulRusak.getText().toString());
		editor.putString("JumlahSawahRusak", edtxtJmlhSawahRusak.getText().toString());
		editor.putString("JumlahLahanRusak", edtxtJmlhLahanRusak.getText().toString());
		editor.putString("JumlahLainLainRusak", edtxtJmlhLainRusak.getText().toString());

		editor.commit();
	}
	
	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(edtxtJmlhJembatanRusak.getText().length()<=0||
				edtxtJmlhJembatanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Jembatan Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhJalanRusak.getText().length()<=0||
				edtxtJmlhJalanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Jalan Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhTanggulRusak.getText().length()<=0||
				edtxtJmlhTanggulRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Tanggul Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhSawahRusak.getText().length()<=0||
				edtxtJmlhSawahRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Sawah Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhLahanRusak.getText().length()<=0||
				edtxtJmlhLahanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Lahan Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhLainRusak.getText().length()<=0||
				edtxtJmlhLainRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Lain-Lain Rusak!", Toast.LENGTH_SHORT).show();
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
				LapLanjutanBencanaAct_4.this.finish();
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
			return createDialog1(LapLanjutanBencanaAct_4.this);
		default:
			break;
		}
		return null;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLanjut:
			if(cekInputanData()==true)
			{
				savePreferences();
				i = new Intent(LapLanjutanBencanaAct_4.this, LapLanjutanBencanaAct_5.class);
				startActivity(i);
				LapLanjutanBencanaAct_4.this.finish();
			}
			break;
		case R.id.buttonKembali:
			i = new Intent(LapLanjutanBencanaAct_4.this, LapLanjutanBencanaAct_3.class);
			startActivity(i);
			LapLanjutanBencanaAct_4.this.finish();
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
			i = new Intent(LapLanjutanBencanaAct_4.this, LapLanjutanBencanaAct_3.class);
			startActivity(i);
			LapLanjutanBencanaAct_4.this.finish();
		}
		return false;
	}
}
