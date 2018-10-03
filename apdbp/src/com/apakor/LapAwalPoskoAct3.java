package com.apakor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LapAwalPoskoAct3 extends Activity implements OnClickListener{
	
	Button kirimCatatan, kembali;
	private static final int DIALOG1 = 1;
	private static final int DIALOG2 = 2; 
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	EditText edtxtJumlahKKMengungsi, edtxtJumlahPriaMengungsi, edtxtJumlahWanitaMengungsi, edtxtJumlahBalitaMengungsi;
	
	String message, phoneNumber, jumlahKKMengungsi, jumlahWanitaMengungsi, jumlahPriaMengungsi, jumlahBalitaMengungsi;
	Intent i;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_posko_3);
		
		getActionBar().setTitle("Laporan Posko");
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
			showDialog(DIALOG2);
			break;
			
		default:
			break;
		}
    	return true;
    }
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		kirimCatatan = (Button) findViewById(R.id.buttonLanjut);
		kirimCatatan.setOnClickListener(this);
		kembali = (Button) findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
		
		edtxtJumlahKKMengungsi = (EditText) findViewById(R.id.editTextJumlahKKMengungsi);
		edtxtJumlahWanitaMengungsi = (EditText) findViewById(R.id.editTextJumlahWanitaMengungsi);
		edtxtJumlahPriaMengungsi = (EditText) findViewById(R.id.editTextJumlahPriaMengungsi);
		edtxtJumlahBalitaMengungsi = (EditText) findViewById(R.id.editTextJumlahBalitaMengungsi);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);	
		phoneNumber = mySharedPreferences.getString("nomorSMSGateway", "Belum diset");
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
	
	private void kirimPesan() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);	
		
		jumlahKKMengungsi = edtxtJumlahKKMengungsi.getText().toString();
		jumlahWanitaMengungsi = edtxtJumlahWanitaMengungsi.getText().toString();
		jumlahPriaMengungsi = edtxtJumlahPriaMengungsi.getText().toString();
		jumlahBalitaMengungsi = edtxtJumlahBalitaMengungsi.getText().toString();
		
		String inputanForm = mySharedPreferences.getString("IDPetugas","")+"~"+mySharedPreferences.getString("IDPosko","")+"~"+mySharedPreferences.getString("IDBencanaPosko","")+"~"
		+mySharedPreferences.getString("NamaPosko","")+"~"+mySharedPreferences.getString("LatitudePosko","")+"~"
		+mySharedPreferences.getString("LongitudePosko","")+"~"+mySharedPreferences.getString("DusunPosko","")+"~"
		+mySharedPreferences.getString("KecamatanPosko","")+"~"+mySharedPreferences.getString("KotaPosko","")+"~"
		+mySharedPreferences.getString("ProvinsiPosko","")+"~"
		
		+mySharedPreferences.getString("KapasitasPosko","")+"~"+mySharedPreferences.getString("JumlahFasilitasDapurPosko","")+"~"
		+mySharedPreferences.getString("JumlahFasilitasKesehatan","")+"~"+mySharedPreferences.getString("JumlahFasilitasMCKPosko","")+"~"

		+jumlahKKMengungsi+"~"+jumlahPriaMengungsi+"~"
		+jumlahWanitaMengungsi+"~"+jumlahBalitaMengungsi;
		
		int partSMS =  hitungPartSMS(inputanForm);
		String kodeSMS = kodeSMS();
		
		String temp = "",//Nampung Hasil Segmen 
		segment = "";//Buat Segmentasi
		int x = 0, counter = 0;
		for (int i = 0; i < inputanForm.length(); i++) { //PECAH INPUTAN 
			//UBAH SEGMEN
			if(x == 0)
			{
				counter++;
				segment += kodeSMS+String.format("%02d", partSMS)+"LPA"+String.format("%02d", counter);//Header SMS (10 karakter)
				segment += inputanForm.charAt(i);
				x++;
			}
			else if(x > 0 && x < 120)
			{
				segment += inputanForm.charAt(i);
				x++;
				
				if(x==120)
				{
					message = segment;
					sendSMS(message);
					
					x = 0;
					segment = "";
				}
				else if(i==inputanForm.length()-1)
				{
					message = segment;
					sendSMS(message);
					
					segment = "";
				}
			}
		}
		
//		b = new Bundle();
//		b.putString("inputanForm", message);
//		b.putString("temp", temp);
	}
	
	private void sendSMS(String isiPesan) {
		// TODO Auto-generated method stub
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNumber, 
					null,  
					isiPesan, 
					null, 
					null);
			Toast.makeText(getApplicationContext(), "Laporan Awal Posko Berhasil Dikirim!", Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(),"Laporan Awal Posko Gagal Dikirim",
					Toast.LENGTH_SHORT).show();
			ex.printStackTrace();
		}
	}
	
	private String kodeSMS()
	{
		String kodeSMS = "";
		//Dapat datetime
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String formattedDate = df.format(c.getTime());
		//hashing datetime
		String kode = MD5(formattedDate);
		//potong 3 karakter awal
		kodeSMS = kode.substring(0, 3);
		
		return kodeSMS;
	}
	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
	    }
	    return null;
	}
	
	private int hitungPartSMS(String inputanForm)
	{
		int jumlah = 0;
		String temp = "";//Buat Segmentasi isi pesan + header
		int x = 0, counter = 0;
		for (int i = 0; i < inputanForm.length(); i++) { //PECAH INPUTAN 
			//UBAH SEGMEN
			if(x == 0)
			{
				counter++;
				temp += "yyy"+"xx"+"LPA"+String.format("%02d", counter);
				temp += inputanForm.charAt(i);
				x++;
			}
			else if(x > 0 && x < 120)
			{
				temp += inputanForm.charAt(i);
				x++;
				
				if(x==120)
				{
					x = 0;
				}
				else if(i==inputanForm.length()-1)
				{	
					jumlah = (int) Math.ceil((double)temp.length()/130);
				}
			}
		}
		return jumlah;
	}
	
	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(edtxtJumlahKKMengungsi.getText().length()<=0||
				edtxtJumlahKKMengungsi.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah KK Mengungsi!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahWanitaMengungsi.getText().length()<=0||
				edtxtJumlahWanitaMengungsi.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Wanita Mengungsi!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahPriaMengungsi.getText().length()<=0||
				edtxtJumlahPriaMengungsi.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Pria Mengungsi!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtJumlahBalitaMengungsi.getText().length()<=0||
				edtxtJumlahBalitaMengungsi.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Field Jumlah Balita Mengungsi!", Toast.LENGTH_SHORT).show();
		}
		return cek;
	}
	
	private Dialog createDialog2(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Batal Membuat Laporan?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				LapAwalPoskoAct3.this.finish();
			}
		});
		
		builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {	
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		return builder.create();
	}
	
	private Dialog createDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Akan Mengirim Data Posko Ini?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				kirimPesan();
				clearPreferences();
				LapAwalPoskoAct3.this.finish();
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
			return createDialog1(LapAwalPoskoAct3.this);
		case DIALOG2:
			return createDialog2(LapAwalPoskoAct3.this);
		default:
			break;
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLanjut:
			if(phoneNumber.equalsIgnoreCase("Belum diset")||phoneNumber.equalsIgnoreCase("")||phoneNumber.length()<=0)
			{
				Toast.makeText(getApplicationContext(), "Set Nomor Tujuan SMS Gateway Dahulu", Toast.LENGTH_SHORT).show();
			}
			else
			{
				if(cekInputanData()==true)
				{
					showDialog(DIALOG1);
				}
			}
			break;
//			if(cekInputanData()==true)
//			{
//				isiData();
//				Intent i = new Intent(getApplicationContext(), Test.class);
//				i.putExtras(b);
//				startActivity(i);
//				clearPreferences();
//				this.finish();
//			}
//			break;
		case R.id.buttonKembali:
			Intent i = new Intent(getApplicationContext(), LapAwalPoskoAct2.class);
			startActivity(i);
			this.finish();
			break;
		default:
			break;
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		loadPreferences();
		super.onResume();
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			Intent i = new Intent(getApplicationContext(), LapAwalPoskoAct2.class);
			startActivity(i);
			this.finish();
		}
		return false;
	}

}
