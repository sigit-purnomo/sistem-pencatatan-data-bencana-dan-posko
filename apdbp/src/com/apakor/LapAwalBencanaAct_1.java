package com.apakor;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class LapAwalBencanaAct_1 extends Activity implements LocationListener, OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	
	private static final int DIALOG1 = 1; 
	
	LocationManager locationManager;
	String locationProvider;
	Criteria criteria;
	Location currentLocation;
	
	Button setWaktu, setTanggal, setPosisi, lanjut, batal;
	Spinner spJenisKejadian;
	EditText edtxtTimBPBD, edtxtTimDinsos, edtxtTimDinkes, edtxtTimPU, edtxtIDBencana, edtxtIDPetugas;
	String jumlahTimBPBD, jumlahTimDinkes, jumlahTimDinsos, jumlahTimPU, 
	jenisKejadianBencana, TanggalBencana, WaktuBencana, LatitudeBencana, LongitudeBencana, idBencana, idPetugas,
	tanggal = "", waktu = "", latitude = "", longitude = "";
	Intent i;
	ImageView imgStatusTanggal, imgStatusWaktu, imgStatusLokasi;
	
	ProgressDialog progDialog;
	
	boolean flag = false;
	
	java.text.DateFormat formatDateTime = java.text.DateFormat.getDateTimeInstance();
	Calendar dateAndTime = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() { //Set value datepicker
		
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			dateAndTime.set(Calendar.YEAR, year);
			dateAndTime.set(Calendar.MONTH, monthOfYear);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			tanggal = String.valueOf(year)+"-"+
			String.valueOf(monthOfYear+1)+"-"+
			String.valueOf(dayOfMonth)
			;		

			Toast.makeText(getBaseContext(), tanggal+ " berhasil diset", Toast.LENGTH_SHORT).show();
			setStatusTanggal();

		}
	};
	
	TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() { //Set value timepicker
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			
			waktu = String.format("%02d:%02d", hourOfDay, minute);
			
			Toast.makeText(getBaseContext(), waktu + " berhasil diset", Toast.LENGTH_SHORT).show();
			setStatusWaktu();
		}
	};
	
	private void setDate(){ //Method buat datepicker
		new DatePickerDialog(LapAwalBencanaAct_1.this, date,
				dateAndTime.get(Calendar.YEAR), 
				dateAndTime.get(Calendar.MONTH), 
				dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
	}
	
	private void setTime(){ //Method buat timepicker
		new TimePickerDialog(LapAwalBencanaAct_1.this, time, 
				dateAndTime.get(Calendar.HOUR_OF_DAY),
				dateAndTime.get(Calendar.MINUTE), true).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_bencana_1);
		getActionBar().setTitle("Laporan Bencana");
		initiateElement();
		setUpGeoCurrentLocation();
		loadPreferences();
	}
	
	private void initiateElement()
    {
		setWaktu = (Button) findViewById(R.id.buttonSetWaktuBencana);
		setWaktu.setOnClickListener(this);
		setTanggal = (Button) findViewById(R.id.buttonSetTanggaBencana);
		setTanggal.setOnClickListener(this);
		setPosisi = (Button) findViewById(R.id.buttonSetPosisi);
		setPosisi.setOnClickListener(this);
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		batal = (Button) findViewById(R.id.buttonBatal);
		batal.setOnClickListener(this);
		
		spJenisKejadian = (Spinner) findViewById(R.id.spinnerJenisKejadian);
		edtxtTimBPBD = (EditText) findViewById(R.id.editTextTimBPBD);
		edtxtTimDinsos = (EditText) findViewById(R.id.editTextTimDinsos);
		edtxtTimDinkes = (EditText) findViewById(R.id.editTextTimDinkes);
		edtxtTimPU = (EditText) findViewById(R.id.editTextTimPU);
		edtxtIDBencana = (EditText)findViewById(R.id.editTextIDBencana);
		edtxtIDPetugas = (EditText)findViewById(R.id.editTextIDPetugas);
		
		imgStatusLokasi = (ImageView)findViewById(R.id.imageViewStatusLokasi);
		imgStatusTanggal = (ImageView)findViewById(R.id.imageViewStatusTanggal);
		imgStatusWaktu = (ImageView)findViewById(R.id.imageViewStatusWaktu);
		
		progDialog = new ProgressDialog(this);
    }
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
		
		idPetugas = mySharedPreferences.getString("IDPetugas", "");
		idBencana = mySharedPreferences.getString("IDBencana", "");
		jumlahTimBPBD = mySharedPreferences.getString("JumlahTimBPBD", "");
		jumlahTimDinkes = mySharedPreferences.getString("JumlahTimDinkes", "");
		jumlahTimDinsos = mySharedPreferences.getString("JumlahTimDinsos", "");
		jumlahTimPU = mySharedPreferences.getString("JumlahTimPU", "");
		jenisKejadianBencana = mySharedPreferences.getString("JenisKejadianBencana", "");
		tanggal= mySharedPreferences.getString("TanggalBencana", "");
		waktu = mySharedPreferences.getString("WaktuBencana", "");
		latitude = mySharedPreferences.getString("LatitudeBencana", "");
		longitude = mySharedPreferences.getString("LongitudeBencana", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtIDPetugas.setText(idPetugas);
		edtxtIDBencana.setText(idBencana);
		edtxtTimBPBD.setText(jumlahTimBPBD);
		edtxtTimDinkes.setText(jumlahTimDinkes);
		edtxtTimDinsos.setText(jumlahTimDinsos);
		edtxtTimPU.setText(jumlahTimPU);
		if(!jenisKejadianBencana.equalsIgnoreCase(""))
		{
			ArrayAdapter myAdap = (ArrayAdapter) spJenisKejadian.getAdapter(); //cast to an ArrayAdapter
			int spinnerPosition = myAdap.getPosition(jenisKejadianBencana);
			spJenisKejadian.setSelection(spinnerPosition);
		}
	}
	
	private void clearPreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDPetugas");
		editor.remove("IDBencana");
		editor.remove("IDBencanaUpdate");
		editor.remove("JumlahSubTim");
		
		editor.remove("JumlahTimBPBD");
		editor.remove("JumlahTimDinkes");
		editor.remove("JumlahTimDinsos");
		editor.remove("JumlahTimPU");
		editor.remove("JenisKejadianBencana");		
		editor.remove("WaktuBencana");
		editor.remove("TanggalBencana");
		editor.remove("LatitudeBencana");
		editor.remove("LongitudeBencana");
		
		editor.remove("LokasiDusunBencana");
		editor.remove("LokasiDesaBencana");		
		editor.remove("LokasiKecamatanBencana");
		editor.remove("LokasiKabupatenBencana");
		editor.remove("PenyebabBencana");
		
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
		
		editor.putString("IDPetugas", edtxtIDPetugas.getText().toString());
		editor.putString("IDBencana", edtxtIDBencana.getText().toString());
		editor.putString("JumlahTimBPBD", edtxtTimBPBD.getText().toString());
		editor.putString("JumlahTimDinkes", edtxtTimDinkes.getText().toString());
		editor.putString("JumlahTimDinsos", edtxtTimDinsos.getText().toString());
		editor.putString("JumlahTimPU", edtxtTimPU.getText().toString());
		editor.putString("JenisKejadianBencana", spJenisKejadian.getSelectedItem().toString());
		editor.putString("TanggalBencana", tanggal);
		editor.putString("WaktuBencana", waktu);
		editor.putString("LatitudeBencana", latitude);
		editor.putString("LongitudeBencana", longitude);
		editor.commit();
	}
	
	private void setUpGeoCurrentLocation() {
		// TODO Auto-generated method stub
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		locationProvider = locationManager.getBestProvider(criteria, true);
		currentLocation = locationManager.getLastKnownLocation(locationProvider);
		locationManager.requestLocationUpdates(locationProvider, 5000, 10, this);
	
		isiPosisi();
	}
	
	private void cekPosisi() {
		// TODO Auto-generated method stub
		if(currentLocation!=null)
		{
			Toast.makeText(getBaseContext(), "Data lokasi berhasil diset", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(getBaseContext(), "Maaf data lokasi Belum tersedia", Toast.LENGTH_SHORT).show();
		}
		setStatusLokasi();
	}
	
	private void isiPosisi() {
		// TODO Auto-generated method stub
		if(currentLocation!=null)
		{
			latitude = String.valueOf(currentLocation.getLatitude());
			longitude = String.valueOf(currentLocation.getLongitude());
			if(progDialog.isShowing())
			{
				progDialog.dismiss();
				cekPosisi();
			}
		}
	}
	
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currentLocation = location;
		isiPosisi();
//		if(progDialog.isShowing())
//		{
//			progDialog.dismiss();
//			cekPosisi();
//		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
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
	
	public void setStatusLokasi()
	{
		if(latitude.length()<=0||latitude.equalsIgnoreCase("")||
				longitude.length()<=0||longitude.equalsIgnoreCase(""))
		{
			imgStatusLokasi.setVisibility(View.INVISIBLE);
		}
		else
		{
			imgStatusLokasi.setVisibility(View.VISIBLE);
		}
	}
	
	public boolean cekInputanData()
	{
		boolean cek = true;
		if(edtxtIDPetugas.getText().length()<=0||
				edtxtIDPetugas.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Petugas!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtIDBencana.getText().length()<=0||
				edtxtIDBencana.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Bencana!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtTimBPBD.getText().length()<=0||
				edtxtTimBPBD.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Tim BPBD!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtTimDinsos.getText().length()<=0||
				edtxtTimDinsos.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Tim Dinas Sosial!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtTimDinkes.getText().length()<=0||
				edtxtTimDinkes.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Tim Dinas Kesehatan!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtTimPU.getText().length()<=0||
				edtxtTimPU.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Tim Perhubungan!", Toast.LENGTH_SHORT).show();
		}
		else if(waktu.length()<=0||waktu.equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Atur Waktu Bencana!", Toast.LENGTH_SHORT).show();
			
		}
		else if(tanggal.length()<=0||tanggal.equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Atur Tanggal Bencana!", Toast.LENGTH_SHORT).show();
		}
		else if(latitude.length()<=0||latitude.equalsIgnoreCase("")||
				longitude.length()<=0||longitude.equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Set Lokasi Dahulu!", Toast.LENGTH_SHORT).show();
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
				LapAwalBencanaAct_1.this.finish();
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
			return createDialog1(LapAwalBencanaAct_1.this);
		default:
			break;
		}
		return null;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSetWaktuBencana: //Set Waktu
			setTime();
			break;
			
		case R.id.buttonSetTanggaBencana: //Set Tanggal
			setDate();
			break;
			
		case R.id.buttonSetPosisi: // Set latitude longitude
			flag = displayGpsStatus();
			if(flag)
			{
				progDialog.setMessage("Mencari data lokasi...");
				progDialog.setTitle("Loading");
				progDialog.setIcon(R.drawable.marker);
				progDialog.show();
//				setUpGeoCurrentLocation();
				locationManager.requestLocationUpdates(locationProvider, 5000, 10, this);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Tolong Aktifkan GPS Pada Perangkat Anda!", Toast.LENGTH_SHORT).show();
			}
			
			break;
			
		case R.id.buttonLanjut: //Lanjutkan
			if(cekInputanData()==true)
			{
				savePreferences();
				i = new Intent(LapAwalBencanaAct_1.this, LapAwalBencanaAct_2.class);
				startActivity(i);
				LapAwalBencanaAct_1.this.finish();
			}
			break;
		case R.id.buttonBatal: //Batal
			showDialog(DIALOG1);
			break;
			
		default:
			break;
		}
	}
	
	private Boolean displayGpsStatus() {  
		ContentResolver contentResolver = getBaseContext().getContentResolver();  
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(contentResolver,LocationManager.GPS_PROVIDER);  
		if (gpsStatus) {  
			return true;  
		} 
		else 
		{  
			return false;  
		}  
	 }  
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(locationProvider, 5000, 10, this);
		setStatusLokasi();
		setStatusTanggal();
		setStatusWaktu();
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
