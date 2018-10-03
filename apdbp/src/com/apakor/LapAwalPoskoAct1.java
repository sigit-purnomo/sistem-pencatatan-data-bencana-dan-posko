package com.apakor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class LapAwalPoskoAct1 extends Activity implements LocationListener, OnClickListener{
	
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	private static final int DIALOG1 = 1; 
	LocationManager locationManager;
	String locationProvider;
	Criteria criteria;
	Location currentLocation;
	
	Intent i;
	Button lanjut, batal, setPosisi;
	EditText edtxtIDPosko, edtxtNamaPosko, latitudePosko, longitudePosko, 
	edtxtDusun, edtxtKecamatan, edtxtKota, edtxtIDBencana, edtxtIDPetugas;
	Spinner spProvinsi;
	String latitude = "", longitude = "", namaPosko, dusun, kecamatan, kota, provinsi, idBencana, idPosko, idPetugas;
	ProgressDialog progDialog;
	
	ImageView imgStatusLokasi;
	boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_posko_1);
		
		getActionBar().setTitle("Laporan Posko");
		initiateElement();
		setUpGeoCurrentLocation();
		loadPreferences();
	}
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		setPosisi = (Button) findViewById(R.id.buttonSetPosisi);
		setPosisi.setOnClickListener(this);
		lanjut = (Button) findViewById(R.id.buttonLanjut);
		lanjut.setOnClickListener(this);
		batal = (Button) findViewById(R.id.buttonBatal);
		batal.setOnClickListener(this);
		
		edtxtIDPetugas = (EditText) findViewById(R.id.editTextIDPetugas);
		edtxtIDPosko = (EditText) findViewById(R.id.editTextIDPosko);
		edtxtIDBencana = (EditText) findViewById(R.id.editTextIDBencana);
		edtxtNamaPosko = (EditText) findViewById(R.id.editTextNamaPosko);
		edtxtDusun = (EditText) findViewById(R.id.editTextLokasiDusun);
		edtxtKecamatan = (EditText) findViewById(R.id.editTextKecamatan);
		edtxtKota = (EditText) findViewById(R.id.editTextKota);
		spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
		
		imgStatusLokasi = (ImageView)findViewById(R.id.imageViewStatusLokasi);
		
		progDialog = new ProgressDialog(this);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
		
		idPetugas = mySharedPreferences.getString("IDPetugas", "");
		idPosko = mySharedPreferences.getString("IDPosko", "");
		idBencana = mySharedPreferences.getString("IDBencanaPosko", "");
		latitude = mySharedPreferences.getString("LatitudePosko", "");
		longitude = mySharedPreferences.getString("LongitudePosko", "");
		namaPosko = mySharedPreferences.getString("NamaPosko", "");
		dusun = mySharedPreferences.getString("DusunPosko", "");
		kecamatan = mySharedPreferences.getString("KecamatanPosko", "");
		kota = mySharedPreferences.getString("KotaPosko", "");
		provinsi = mySharedPreferences.getString("ProvinsiPosko", "");
		
		setElement();
	}
	
	private void setElement()
	{
		edtxtIDPetugas.setText(idPetugas);
		edtxtIDPosko.setText(idPosko);
		edtxtIDBencana.setText(idBencana);
		edtxtNamaPosko.setText(namaPosko);
		edtxtDusun.setText(dusun);
		edtxtKecamatan.setText(kecamatan);
		edtxtKota.setText(kota);
		if(!provinsi.equalsIgnoreCase(""))
		{
			ArrayAdapter myAdap = (ArrayAdapter) spProvinsi.getAdapter(); //cast to an ArrayAdapter
			int spinnerPosition = myAdap.getPosition(provinsi);
			spProvinsi.setSelection(spinnerPosition);
		}
	}
	
	private void clearPreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDPetugas");
		editor.remove("IDPosko");
		editor.remove("IDBencanaPosko");
		editor.remove("NamaPosko");
		editor.remove("LatitudePosko");
		editor.remove("LongitudePosko");
		editor.remove("DusunPosko");
		editor.remove("KecamatanPosko");		
		editor.remove("KotaPosko");
		editor.remove("ProvinsiPosko");
		
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
		editor.putString("IDPosko", edtxtIDPosko.getText().toString());
		editor.putString("IDBencanaPosko", edtxtIDBencana.getText().toString());
		editor.putString("NamaPosko", edtxtNamaPosko.getText().toString());
		editor.putString("DusunPosko", edtxtDusun.getText().toString());
		editor.putString("KecamatanPosko", edtxtKecamatan.getText().toString());
		editor.putString("KotaPosko", edtxtKota.getText().toString());
		editor.putString("ProvinsiPosko", spProvinsi.getSelectedItem().toString());
		editor.putString("LatitudePosko", latitude);
		editor.putString("LongitudePosko", longitude);
		
		editor.commit();
	}
	
	private void setUpGeoCurrentLocation() {
		// TODO Auto-generated method stub
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		locationProvider = locationManager.getBestProvider(criteria, true);
		currentLocation = locationManager.getLastKnownLocation(locationProvider);
		locationManager.requestLocationUpdates(locationProvider, 400, 1, this);
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
			Toast.makeText(getBaseContext(), "Maaf data lokasi belum tersedia", Toast.LENGTH_SHORT).show();
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
		locationManager.requestLocationUpdates(locationProvider, 400, 1, this);
		setStatusLokasi();
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
		else if(edtxtIDBencana.getText().length()<=0||
				edtxtIDBencana.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field ID Bencana!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtNamaPosko.getText().length()<=0||
				edtxtNamaPosko.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Posko!", Toast.LENGTH_SHORT).show();
		}
		else if(latitude.length()<=0||latitude.equalsIgnoreCase("")||
				longitude.length()<=0||longitude.equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Set Lokasi Dahulu!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtDusun.getText().length()<=0||
				edtxtDusun.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Dusun!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKecamatan.getText().length()<=0||
				edtxtKecamatan.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Kecamatan!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKota.getText().length()<=0||
				edtxtKota.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Nama Kota!", Toast.LENGTH_SHORT).show();
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
				LapAwalPoskoAct1.this.finish();
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
			return createDialog1(LapAwalPoskoAct1.this);
		default:
			break;
		}
		return null;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSetPosisi:
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
			
		case R.id.buttonLanjut:
			if(cekInputanData()==true)
			{
				savePreferences();
				i = new Intent(LapAwalPoskoAct1.this, LapAwalPoskoAct2.class);
				startActivity(i);
				LapAwalPoskoAct1.this.finish();
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
