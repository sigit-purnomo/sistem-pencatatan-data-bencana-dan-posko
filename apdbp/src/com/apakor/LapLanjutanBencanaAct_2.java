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

public class LapLanjutanBencanaAct_2 extends Activity implements OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	private static final int DIALOG1 = 1; 
	Intent i;
	Button lanjut, batal;
	EditText edtxtJumlahSubTim, edtxtJmlhMeninggal, edtxtJmlhLukaBerat, edtxtJmlhLukaRingan, edtxtJmlhHilang,
	edtxtJmlhJiwaMengungsi, edtxtJmlhKKMengungsi, edtxtIdBencana, edtxtIdPetugas;
	String jumlahSubTim, jumlahMeninggal, jumlahLukaBerat, 
	jumlahLukaRingan, jumlahHilang, jumlahJiwaMengungsi, jumlahKKMengungsi, idBencana, idPetugas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_lanjut_bencana_2);
		
		getActionBar().setTitle("Laporan Bencana");
		initiateElement();
		loadPreferences();
		
	}
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		edtxtJumlahSubTim = (EditText) findViewById(R.id.editTextSubTim);
		edtxtJmlhMeninggal = (EditText) findViewById(R.id.editTextJumlahMeninggal);
		edtxtJmlhLukaBerat = (EditText) findViewById(R.id.editTextJumlahLukaBerat);
		edtxtJmlhLukaRingan = (EditText) findViewById(R.id.editTextJumlahLukaRingan);
		edtxtJmlhHilang = (EditText) findViewById(R.id.editTextJumlahHilang);
		edtxtJmlhJiwaMengungsi = (EditText) findViewById(R.id.editTextJumlahJiwaMengungsi);
		edtxtJmlhKKMengungsi = (EditText) findViewById(R.id.editTextJumlahKKMengungsi);
		edtxtIdBencana = (EditText) findViewById(R.id.editTextIDBencana);
		edtxtIdPetugas = (EditText) findViewById(R.id.editTextIDPetugas);
		
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		batal = (Button) findViewById(R.id.buttonBatal);
		batal.setOnClickListener(this);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);

		idPetugas = mySharedPreferences.getString("IDPetugas", "");
		idBencana = mySharedPreferences.getString("IDBencanaUpdate", "");
		jumlahSubTim = mySharedPreferences.getString("JumlahSubTim", "");
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
		edtxtIdPetugas.setText(idPetugas);
		edtxtIdBencana.setText(idBencana);
		edtxtJumlahSubTim.setText(jumlahSubTim);
		edtxtJmlhMeninggal.setText(jumlahMeninggal);
		edtxtJmlhLukaBerat.setText(jumlahLukaBerat);
		edtxtJmlhLukaRingan.setText(jumlahLukaRingan);
		edtxtJmlhHilang.setText(jumlahHilang);
		edtxtJmlhJiwaMengungsi.setText(jumlahJiwaMengungsi);
		edtxtJmlhKKMengungsi.setText(jumlahKKMengungsi);
	}
	
	private void clearPreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDPetugas");
		editor.remove("IDBencanaUpdate");
		editor.remove("JumlahSubTim");
		
		editor.remove("JumlahMeninggal");
		editor.remove("JumlahLukaBerat");		
		editor.remove("JumlahLukaRingan");
		editor.remove("JumlahHilang");
		editor.remove("JumlahJiwaMengungsi");
		editor.remove("JumlahKKMengungsi");
		
		editor.remove("JumlahRumahRusak");
		editor.remove("JumlahKantorRusak");		
		editor.remove("JumlahFasilitasKesehatanRusak");
		editor.remove("JumlahFasilitasPendidikanRusak");
		editor.remove("JumlahFasilitasUmumRusak");
		editor.remove("JumlahSaranaIbadahRusak");
		
		editor.remove("JumlahJembatanRusak");		
		editor.remove("JumlahJalanRusak");
		editor.remove("JumlahTanggulRusak");
		editor.remove("JumlahSawahRusak");
		editor.remove("JumlahLahanRusak");
		editor.remove("JumlahLainLainRusak");
		
		editor.remove("WaktuPeninjauan");
		editor.remove("TanggalPeninjauan");
		editor.remove("MendirikanPosko");
		editor.remove("MelakukanRapat");
		editor.remove("MelaksanakanEvakuasi");
		editor.remove("PelayananKesehatan");
		editor.remove("DapurUmum");
		editor.remove("BantuanMakanan");
		editor.remove("PengarahanTenaga");
		
//		editor.remove("SumberDaya");
//		editor.remove("Kendala");
//		editor.remove("Kebutuhan");
//		editor.remove("RencanaTdkLanjut");
		editor.commit();
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("IDPetugas", edtxtIdPetugas.getText().toString());
		editor.putString("IDBencanaUpdate", edtxtIdBencana.getText().toString());
		editor.putString("JumlahSubTim", edtxtJumlahSubTim.getText().toString());
		
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
		if(edtxtIdPetugas.getText().length()<=0||
				edtxtIdPetugas.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Petugas!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtIdBencana.getText().length()<=0||
				edtxtIdBencana.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Bencana!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahSubTim.getText().length()<=0||
				edtxtJumlahSubTim.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Sub Tim!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhMeninggal.getText().length()<=0||
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
				clearPreferences();
				LapLanjutanBencanaAct_2.this.finish();
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
			return createDialog1(LapLanjutanBencanaAct_2.this);
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
				i = new Intent(LapLanjutanBencanaAct_2.this, LapLanjutanBencanaAct_3.class);
				startActivity(i);
				LapLanjutanBencanaAct_2.this.finish();
			}
			break;
			
		case R.id.buttonBatal:
			showDialog(DIALOG1);
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
			showDialog(DIALOG1);
		}
		return false;
	}

}
