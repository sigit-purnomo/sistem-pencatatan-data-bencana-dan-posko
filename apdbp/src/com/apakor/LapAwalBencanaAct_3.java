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

public class LapAwalBencanaAct_3 extends Activity implements OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	
	private static final int DIALOG1 = 1; 
	
	String jumlahMeninggal, jumlahLukaBerat, jumlahLukaRingan, jumlahHilang, jumlahJiwaMengungsi, jumlahKKMengungsi;
	Button lanjut, kembali;
	EditText edtxtJmlhMeninggal, edtxtJmlhLukaBerat, edtxtJmlhLukaRingan, edtxtJmlhHilang,
		edtxtJmlhJiwaMengungsi, edtxtJmlhKKMengungsi;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_bencana_3);
		getActionBar().setTitle("Laporan Bencana");
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
		edtxtJmlhMeninggal = (EditText) findViewById(R.id.editTextJumlahMeninggal);
		edtxtJmlhLukaBerat = (EditText) findViewById(R.id.editTextJumlahLukaBerat);
		edtxtJmlhLukaRingan = (EditText) findViewById(R.id.editTextJumlahLukaRingan);
		edtxtJmlhHilang = (EditText) findViewById(R.id.editTextJumlahHilang);
		edtxtJmlhJiwaMengungsi = (EditText) findViewById(R.id.editTextJumlahJiwaMengungsi);
		edtxtJmlhKKMengungsi = (EditText) findViewById(R.id.editTextJumlahKKMengungsi);
		
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		kembali = (Button) findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);

		jumlahMeninggal = mySharedPreferences.getString("JumlahMeninggal", "");
		jumlahLukaBerat = mySharedPreferences.getString("JumlahLukaBerat", "");
		jumlahLukaRingan = mySharedPreferences.getString("JumlahLukaRingan", "");
		jumlahHilang = mySharedPreferences.getString("JumlahHilang", "");
		jumlahJiwaMengungsi = mySharedPreferences.getString("JumlahJiwaMengungsi", "");
		jumlahKKMengungsi = mySharedPreferences.getString("JumlahKKMengungsi", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtJmlhMeninggal.setText(jumlahMeninggal);
		edtxtJmlhLukaBerat.setText(jumlahLukaBerat);
		edtxtJmlhLukaRingan.setText(jumlahLukaRingan);
		edtxtJmlhHilang.setText(jumlahHilang);
		edtxtJmlhJiwaMengungsi.setText(jumlahJiwaMengungsi);
		edtxtJmlhKKMengungsi.setText(jumlahKKMengungsi);
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("JumlahMeninggal", edtxtJmlhMeninggal.getText().toString());
		editor.putString("JumlahLukaBerat", edtxtJmlhLukaBerat.getText().toString());
		editor.putString("JumlahLukaRingan", edtxtJmlhLukaRingan.getText().toString());
		editor.putString("JumlahHilang", edtxtJmlhHilang.getText().toString());
		editor.putString("JumlahJiwaMengungsi", edtxtJmlhJiwaMengungsi.getText().toString());
		editor.putString("JumlahKKMengungsi", edtxtJmlhKKMengungsi.getText().toString());

		editor.commit();
	}
	
	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(edtxtJmlhMeninggal.getText().length()<=0||
				edtxtJmlhMeninggal.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Korban Meninggal!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhLukaBerat.getText().length()<=0||
				edtxtJmlhLukaBerat.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Korban Luka Berat!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhLukaRingan.getText().length()<=0||
				edtxtJmlhLukaRingan.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Korban Luka Ringan!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhHilang.getText().length()<=0||
				edtxtJmlhHilang.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Korban Hilang!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhJiwaMengungsi.getText().length()<=0||
				edtxtJmlhJiwaMengungsi.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Jiwa Mengungsi!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhKKMengungsi.getText().length()<=0||
				edtxtJmlhKKMengungsi.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah KK Mengungsi!", Toast.LENGTH_SHORT).show();
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
				LapAwalBencanaAct_3.this.finish();
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
			return createDialog1(LapAwalBencanaAct_3.this);
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
				i = new Intent(LapAwalBencanaAct_3.this, LapAwalBencanaAct_4.class);
				startActivity(i);
				LapAwalBencanaAct_3.this.finish();
			}
			break;
			
		case R.id.buttonKembali:
			i = new Intent(LapAwalBencanaAct_3.this, LapAwalBencanaAct_2.class);
			startActivity(i);
			LapAwalBencanaAct_3.this.finish();
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
			i = new Intent(LapAwalBencanaAct_3.this, LapAwalBencanaAct_2.class);
			startActivity(i);
			LapAwalBencanaAct_3.this.finish();
		}
		return false;
	}
}
