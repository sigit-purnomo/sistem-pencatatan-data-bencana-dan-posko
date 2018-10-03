package com.apakor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheckGPSAct extends Activity implements LocationListener, OnClickListener{
	
	LocationManager locationManager;
	String locationProvider;
	float latitude, longitude;
	Criteria criteria;
	Location currentLocation;
	ProgressDialog progDialog;
	
	Button cekGPS, kembali;
	TextView txtLatitude, txtLongitude;
	
	boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps);
		
		getActionBar().setTitle("Check GPS");
		initiateElement();
		setUpGeoCurrentLocation();
	}
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		kembali = (Button) findViewById(R.id.buttonKembaliGPS);
		kembali.setOnClickListener(this);
		cekGPS = (Button) findViewById(R.id.buttonCekPosisi);
		cekGPS.setOnClickListener(this);
		txtLatitude = (TextView) findViewById(R.id.textViewLatitude);
		txtLongitude = (TextView) findViewById(R.id.textViewLongitude);
		
		progDialog = new ProgressDialog(this);
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
	
	private void setPositionText() {
		// TODO Auto-generated method stub
		if(currentLocation!=null)
		{		
			txtLatitude.setText(String.valueOf(latitude));
			txtLongitude.setText(String.valueOf(longitude));
		}
		else
		{
			txtLatitude.setText("Maaf Data Lokasi Belum Tersedia");
			txtLongitude.setText("Maaf Data Lokasi Belum Tersedia");
		}
	}
	
	private void isiPosisi() {
		// TODO Auto-generated method stub
		if(currentLocation!=null)
		{
			latitude = (float) currentLocation.getLatitude();
			longitude = (float) currentLocation.getLongitude();
			if(progDialog.isShowing())
			{
				progDialog.dismiss();
				setPositionText();
			}
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonCekPosisi:
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
		case R.id.buttonKembaliGPS :
			CheckGPSAct.this.finish();
			break;
		default:
			break;
		}
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currentLocation = location;
		isiPosisi();
//		if(progDialog.isShowing())
//		{
//			progDialog.dismiss();
//			setPositionText();
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
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(this);
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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(locationProvider, 5000, 10, this);
//		setUpGeoCurrentLocation();
		
	}
}


