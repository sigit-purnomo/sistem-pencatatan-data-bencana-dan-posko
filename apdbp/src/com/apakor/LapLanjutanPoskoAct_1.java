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

public class LapLanjutanPoskoAct_1 extends Activity implements OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	private static final int DIALOG1 = 1; 
	Intent i;
	Button lanjut, batal;
	EditText edtxtIDPetugas, edtxtIDPosko, edtxtKapasitasPosko, edtxtJumlahFasilitasDapur,
	edtxtJumlahFasKesehatan, edtxtJumlahFasilitasMCK;
	String idPosko, idPetugas, kapasitasPosko, jumlahFasilitasDapurPosko, jumlahFasilitasKesehatan, jumlahFasilitasMCKPosko;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_lanjut_posko_1);
		
		getActionBar().setTitle("Laporan Posko");
		initiateElement();
		loadPreferences();
	}
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		batal = (Button) findViewById(R.id.buttonBatal);
		batal.setOnClickListener(this);
		
		edtxtIDPetugas = (EditText) findViewById(R.id.editTextIDPetugas);
		edtxtIDPosko = (EditText) findViewById(R.id.editTextIDPosko);
		edtxtKapasitasPosko = (EditText) findViewById(R.id.editTextKapasitasPosko);
		edtxtJumlahFasilitasDapur = (EditText) findViewById(R.id.editTextJumlahFasilitasDapur);
		edtxtJumlahFasilitasMCK = (EditText) findViewById(R.id.editTextJumlahFasilitasMCK);
		edtxtJumlahFasKesehatan = (EditText) findViewById(R.id.editTextJumlahFasilitasKesehatan);
		
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
		
		idPetugas = mySharedPreferences.getString("IDPetugas", "");
		idPosko = mySharedPreferences.getString("IDPoskoUpdate", "");
		kapasitasPosko = mySharedPreferences.getString("KapasitasPosko", "");
		jumlahFasilitasDapurPosko = mySharedPreferences.getString("JumlahFasilitasDapurPosko", "");
		jumlahFasilitasMCKPosko = mySharedPreferences.getString("JumlahFasilitasMCKPosko", "");
		jumlahFasilitasKesehatan = mySharedPreferences.getString("JumlahFasilitasKesehatan", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtIDPetugas.setText(idPetugas);
		edtxtIDPosko.setText(idPosko);
		edtxtKapasitasPosko.setText(kapasitasPosko);
		edtxtJumlahFasilitasDapur.setText(jumlahFasilitasDapurPosko);
		edtxtJumlahFasilitasMCK.setText(jumlahFasilitasMCKPosko);
		edtxtJumlahFasKesehatan.setText(jumlahFasilitasKesehatan);
	}
	
	private void clearPreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDPetugas");
		editor.remove("IDPoskoUpdate");
		
		editor.remove("KapasitasPosko");
		editor.remove("JumlahFasilitasDapurPosko");		
		editor.remove("JumlahFasilitasKesehatan");
		editor.remove("JumlahFasilitasMCKPosko");
		
//		editor.remove("jumlahKKMengungsi");
//		editor.remove("jumlahPriaMengungsi");		
//		editor.remove("jumlahWanitaMengungsi");
//		editor.remove("jumlahBalitaMengungsi");
		editor.commit();
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("IDPetugas", edtxtIDPetugas.getText().toString());
		editor.putString("IDPoskoUpdate", edtxtIDPosko.getText().toString());
		editor.putString("KapasitasPosko", edtxtKapasitasPosko.getText().toString());
		editor.putString("JumlahFasilitasDapurPosko", edtxtJumlahFasilitasDapur.getText().toString());
		editor.putString("JumlahFasilitasMCKPosko", edtxtJumlahFasilitasMCK.getText().toString());
		editor.putString("JumlahFasilitasKesehatan", edtxtJumlahFasKesehatan.getText().toString());
		
		editor.commit();
	}
	
	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(edtxtIDPetugas.getText().length()<=0||
				edtxtIDPetugas.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Petugas!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtIDPosko.getText().length()<=0||
				edtxtIDPosko.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Posko!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKapasitasPosko.getText().length()<=0||
				edtxtKapasitasPosko.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Kapasitas Posko!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahFasilitasDapur.getText().length()<=0||
				edtxtJumlahFasilitasDapur.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Fasilitas Dapur Posko!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahFasilitasMCK.getText().length()<=0||
				edtxtJumlahFasilitasMCK.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Fasilitas MCK Posko!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahFasKesehatan.getText().length()<=0||
				edtxtJumlahFasKesehatan.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Fasilitas Kesehatan Posko!", Toast.LENGTH_SHORT).show();
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
				LapLanjutanPoskoAct_1.this.finish();
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
			return createDialog1(LapLanjutanPoskoAct_1.this);
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
				i = new Intent(LapLanjutanPoskoAct_1.this, LapLanjutanPoskoAct_2.class);
				startActivity(i);
				LapLanjutanPoskoAct_1.this.finish();
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
