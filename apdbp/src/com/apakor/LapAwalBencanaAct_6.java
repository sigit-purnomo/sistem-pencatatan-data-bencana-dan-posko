package com.apakor;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class LapAwalBencanaAct_6 extends Activity implements OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	
	private static final int DIALOG1 = 1; 
	
	Button lanjut, kembali, setWaktu, setTanggal;
	Intent i;
	String tanggal = "", waktu = "";
	RadioGroup rgPosko, rgRapat, rgEvakuasi, rgPelayananKesehatan, 
	rgDapurUmum, rgBantuanMakanan, rgPengarahan;
	ImageView imgStatusTanggal, imgStatusWaktu;
	
	String posko, rapat, evakuasi,
	kesehatan, dapur, bantuan, pengarahan;
	
	DateFormat formatDateTime = DateFormat.getDateTimeInstance();
	Calendar dateAndTime = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
		
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			dateAndTime.set(Calendar.YEAR, year);
			dateAndTime.set(Calendar.MONTH, monthOfYear);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
//			tanggal = String.valueOf(dayOfMonth)+"/"+
//			String.valueOf(monthOfYear+1)+"/"+
//			String.valueOf(year);	
			tanggal = String.valueOf(year)+"-"+
			String.valueOf(monthOfYear+1)+"-"+
			String.valueOf(dayOfMonth);

			Toast.makeText(getBaseContext(), tanggal+ " berhasil diset", Toast.LENGTH_SHORT).show();
			setStatusTanggal();
		}
	};
	
	TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			
			waktu = String.format("%02d:%02d", hourOfDay, minute);
			
			Toast.makeText(getBaseContext(), waktu + " berhasil diset", Toast.LENGTH_SHORT).show();
			setStatusWaktu();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_bencana_6);
		
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
	
	private void setDate(){
		new DatePickerDialog(LapAwalBencanaAct_6.this, date,
				dateAndTime.get(Calendar.YEAR), 
				dateAndTime.get(Calendar.MONTH), 
				dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
	}
	
	private void setTime(){
		new TimePickerDialog(LapAwalBencanaAct_6.this, time, 
				dateAndTime.get(Calendar.HOUR_OF_DAY),
				dateAndTime.get(Calendar.MINUTE), true).show();
	}
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		setWaktu = (Button) findViewById(R.id.buttonSetWaktuPeninjauan);
		setWaktu.setOnClickListener(this);
		setTanggal = (Button) findViewById(R.id.buttonSetTanggalPeninjauan);
		setTanggal.setOnClickListener(this);
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		kembali = (Button) findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
		
		rgPosko = (RadioGroup) findViewById(R.id.radioGroupPosko);
		rgRapat = (RadioGroup) findViewById(R.id.radioGroupRapat);
		rgEvakuasi = (RadioGroup) findViewById(R.id.radioGroupEvakuasi);
		rgPelayananKesehatan = (RadioGroup) findViewById(R.id.radioGroupPelayananKesehatan);
		rgDapurUmum = (RadioGroup) findViewById(R.id.radioGroupDapurUmum);
		rgBantuanMakanan = (RadioGroup) findViewById(R.id.radioGroupBantuanMakanan);
		rgPengarahan = (RadioGroup) findViewById(R.id.radioGroupPengarahan);
		
		imgStatusTanggal = (ImageView)findViewById(R.id.imageViewStatusTanggal);
		imgStatusWaktu = (ImageView)findViewById(R.id.imageViewStatusWaktu);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
	
		waktu = mySharedPreferences.getString("WaktuPeninjauan", "");
		tanggal = mySharedPreferences.getString("TanggalPeninjauan", "");
		posko = mySharedPreferences.getString("MendirikanPosko", "");
		rapat = mySharedPreferences.getString("MelakukanRapat", "");
		evakuasi = mySharedPreferences.getString("MelaksanakanEvakuasi", "");
		kesehatan = mySharedPreferences.getString("PelayananKesehatan", "");
		dapur = mySharedPreferences.getString("DapurUmum", "");
		bantuan = mySharedPreferences.getString("BantuanMakanan", "");
		pengarahan = mySharedPreferences.getString("PengarahanTenaga", "");
		
		setElement();
	}
	
	private void setElement()
	{
		if(posko.equalsIgnoreCase("Sudah"))
		{
			rgPosko.check(R.id.radio0);
		}
		else if(posko.equalsIgnoreCase("Belum"))
		{
			rgPosko.check(R.id.radio1);
		}
		
		if(rapat.equalsIgnoreCase("Sudah"))
		{
			rgRapat.check(R.id.radio2);
		}
		else if(rapat.equalsIgnoreCase("Belum"))
		{
			rgRapat.check(R.id.radio3);
		}
		
		if(evakuasi.equalsIgnoreCase("Sudah"))
		{
			rgEvakuasi.check(R.id.radio4);
		}
		else if(evakuasi.equalsIgnoreCase("Belum"))
		{
			rgEvakuasi.check(R.id.radio5);
		}
		
		if(kesehatan.equalsIgnoreCase("Sudah"))
		{
			rgPelayananKesehatan.check(R.id.radio6);
		}
		else if(kesehatan.equalsIgnoreCase("Belum"))
		{
			rgPelayananKesehatan.check(R.id.radio7);
		}
		
		if(dapur.equalsIgnoreCase("Sudah"))
		{
			rgDapurUmum.check(R.id.radio8);
		}
		else if(dapur.equalsIgnoreCase("Belum"))
		{
			rgDapurUmum.check(R.id.radio9);
		}
		
		if(bantuan.equalsIgnoreCase("Sudah"))
		{
			rgBantuanMakanan.check(R.id.radio10);
		}
		else if(bantuan.equalsIgnoreCase("Belum"))
		{
			rgBantuanMakanan.check(R.id.radio11);
		}
		
		if(pengarahan.equalsIgnoreCase("Sudah"))
		{
			rgPengarahan.check(R.id.radio12);
		}
		else if(pengarahan.equalsIgnoreCase("Belum"))
		{
			rgPengarahan.check(R.id.radio13);
		}
	}
	
	private void savePreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.putString("TanggalPeninjauan", tanggal);
		editor.putString("WaktuPeninjauan", waktu);
		
		int idRadioPosko = rgPosko.getCheckedRadioButtonId();
		RadioButton posko = (RadioButton) findViewById(idRadioPosko);
		editor.putString("MendirikanPosko", posko.getText().toString());
		
		int idRadioRapat = rgRapat.getCheckedRadioButtonId();
		RadioButton rapat = (RadioButton) findViewById(idRadioRapat);
		editor.putString("MelakukanRapat", rapat.getText().toString());
		
		int idRadioEvakuasi = rgEvakuasi.getCheckedRadioButtonId();
		RadioButton evakuasi = (RadioButton) findViewById(idRadioEvakuasi);
		editor.putString("MelaksanakanEvakuasi", evakuasi.getText().toString());
		
		int idRadioKesehatan = rgPelayananKesehatan.getCheckedRadioButtonId();
		RadioButton kesehatan = (RadioButton) findViewById(idRadioKesehatan);
		editor.putString("PelayananKesehatan", kesehatan.getText().toString());
		
		int idRadioDapurUmum = rgDapurUmum.getCheckedRadioButtonId();
		RadioButton dapur = (RadioButton) findViewById(idRadioDapurUmum);
		editor.putString("DapurUmum", dapur.getText().toString());
		
		int idRadioBantuanMakanan = rgBantuanMakanan.getCheckedRadioButtonId();
		RadioButton bantuan = (RadioButton) findViewById(idRadioBantuanMakanan);
		editor.putString("BantuanMakanan", bantuan.getText().toString());
		
		int idRadioPengerahanTenaga = rgPengarahan.getCheckedRadioButtonId();
		RadioButton pengarahan = (RadioButton) findViewById(idRadioPengerahanTenaga);
		editor.putString("PengarahanTenaga", pengarahan.getText().toString());

		editor.commit();
	}
	
	public void setStatusWaktu()
	{
		if(waktu.length()<=0||waktu.equalsIgnoreCase(""))
		{
			imgStatusWaktu.setVisibility(View.INVISIBLE);
		}
		else
		{
			imgStatusWaktu.setVisibility(View.VISIBLE);
		}
	}
	
	public void setStatusTanggal()
	{
		if(tanggal.length()<=0||tanggal.equalsIgnoreCase(""))
		{
			imgStatusTanggal.setVisibility(View.INVISIBLE);
		}
		else
		{
			imgStatusTanggal.setVisibility(View.VISIBLE);
		}
	}

	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(tanggal.length()<=0||tanggal.equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Atur Tanggal Peninjauan!", Toast.LENGTH_SHORT).show();
		}
		else if(waktu.length()<=0||waktu.equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Atur Waktu Peninjauan!", Toast.LENGTH_SHORT).show();
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
				LapAwalBencanaAct_6.this.finish();
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
			return createDialog1(LapAwalBencanaAct_6.this);
		default:
			break;
		}
		return null;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSetWaktuPeninjauan:
			setTime();
			break;
			
		case R.id.buttonSetTanggalPeninjauan:
			setDate();
			break;
			
		case R.id.buttonLanjut: //Belom FIX
			if(cekInputanData()==true)
			{
//				isiData();
				savePreferences();
				i = new Intent(LapAwalBencanaAct_6.this, LapAwalBencanaAct_7.class);
//				i.putExtras(b);
				startActivity(i);
				LapAwalBencanaAct_6.this.finish();
			}
			break;
			
		case R.id.buttonKembali:
			i = new Intent(LapAwalBencanaAct_6.this, LapAwalBencanaAct_5.class);
			startActivity(i);
			LapAwalBencanaAct_6.this.finish();
			break;
			
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setStatusTanggal();
		setStatusWaktu();
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			i = new Intent(LapAwalBencanaAct_6.this, LapAwalBencanaAct_5.class);
			startActivity(i);
			LapAwalBencanaAct_6.this.finish();
		}
		return false;
	}

}
