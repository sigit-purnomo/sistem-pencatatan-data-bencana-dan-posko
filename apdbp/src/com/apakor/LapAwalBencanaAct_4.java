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

public class LapAwalBencanaAct_4 extends Activity implements OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	
	private static final int DIALOG1 = 1; 
	
	EditText edtxtJmlhRmhRusak, edtxtJmlhKantorRusak, edtxtJmlhFasKesehatanRusak, 
		edtxtJmlhFasPendidikanRusak, edtxtJmlhFasUmumRusak, edtxtJmlhSaranaIbadahRusak;

	String jumlahRumahRusak, jumlahKantorRusak, jumlahFasilitasKesehatanRusak, jumlahFasilitasPendidikanRusak, jumlahFasilitasUmumRusak, jumlahSaranaIbadahRusak;
	Button lanjut, kembali;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_bencana_4);
		
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
		edtxtJmlhRmhRusak = (EditText) findViewById(R.id.editTextJumlahRusakRumah);
		edtxtJmlhKantorRusak = (EditText) findViewById(R.id.editTextJumlahRusakKantor); 
		edtxtJmlhFasKesehatanRusak = (EditText)findViewById(R.id.editTextJumlahFasKesehatanRusak); 
		edtxtJmlhFasPendidikanRusak = (EditText)findViewById(R.id.editTextJumlahFasPendidikanRusak);
		edtxtJmlhFasUmumRusak = (EditText) findViewById(R.id.editTextJumlahFasUmumRusak);
		edtxtJmlhSaranaIbadahRusak = (EditText) findViewById(R.id.editTextJumlahSaranaIbadahRusak);
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		kembali = (Button) findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
	
		jumlahRumahRusak = mySharedPreferences.getString("JumlahRumahRusak", "");
		jumlahKantorRusak = mySharedPreferences.getString("JumlahKantorRusak", "");
		jumlahFasilitasKesehatanRusak = mySharedPreferences.getString("JumlahFasilitasKesehatanRusak", "");
		jumlahFasilitasPendidikanRusak = mySharedPreferences.getString("JumlahFasilitasPendidikanRusak", "");
		jumlahFasilitasUmumRusak = mySharedPreferences.getString("JumlahFasilitasUmumRusak", "");
		jumlahSaranaIbadahRusak = mySharedPreferences.getString("JumlahSaranaIbadahRusak", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtJmlhRmhRusak.setText(jumlahRumahRusak);
		edtxtJmlhKantorRusak.setText(jumlahKantorRusak);
		edtxtJmlhFasKesehatanRusak.setText(jumlahFasilitasKesehatanRusak);
		edtxtJmlhFasPendidikanRusak.setText(jumlahFasilitasPendidikanRusak);
		edtxtJmlhFasUmumRusak.setText(jumlahFasilitasUmumRusak);
		edtxtJmlhSaranaIbadahRusak.setText(jumlahSaranaIbadahRusak);
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("JumlahRumahRusak", edtxtJmlhRmhRusak.getText().toString());
		editor.putString("JumlahKantorRusak", edtxtJmlhKantorRusak.getText().toString());
		editor.putString("JumlahFasilitasKesehatanRusak", edtxtJmlhFasKesehatanRusak.getText().toString());
		editor.putString("JumlahFasilitasPendidikanRusak", edtxtJmlhFasPendidikanRusak.getText().toString());
		editor.putString("JumlahFasilitasUmumRusak", edtxtJmlhFasUmumRusak.getText().toString());
		editor.putString("JumlahSaranaIbadahRusak", edtxtJmlhSaranaIbadahRusak.getText().toString());

		editor.commit();
	}
	
	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(edtxtJmlhRmhRusak.getText().length()<=0||
				edtxtJmlhRmhRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Rumah Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhKantorRusak.getText().length()<=0||
				edtxtJmlhKantorRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Kantor Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhFasKesehatanRusak.getText().length()<=0||
				edtxtJmlhFasKesehatanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Fasilitas Kesehatan Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhFasPendidikanRusak.getText().length()<=0||
				edtxtJmlhFasPendidikanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Fasilitas Pendidikan Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhFasUmumRusak.getText().length()<=0||
				edtxtJmlhFasPendidikanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Fasilitas Umum Rusak!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJmlhSaranaIbadahRusak.getText().length()<=0||
				edtxtJmlhFasPendidikanRusak.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Sarana Ibadah Rusak!", Toast.LENGTH_SHORT).show();
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
				LapAwalBencanaAct_4.this.finish();
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
			return createDialog1(LapAwalBencanaAct_4.this);
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
				i = new Intent(LapAwalBencanaAct_4.this, LapAwalBencanaAct_5.class);
				startActivity(i);
				LapAwalBencanaAct_4.this.finish();
			}
			break;
		case R.id.buttonKembali:
			i = new Intent(LapAwalBencanaAct_4.this, LapAwalBencanaAct_3.class);
			startActivity(i);
			LapAwalBencanaAct_4.this.finish();
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
			i = new Intent(LapAwalBencanaAct_4.this, LapAwalBencanaAct_3.class);
			startActivity(i);
			LapAwalBencanaAct_4.this.finish();
		}
		return false;
	}

}
