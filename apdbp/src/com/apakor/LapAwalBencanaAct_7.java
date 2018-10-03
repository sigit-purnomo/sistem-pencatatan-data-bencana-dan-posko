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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class LapAwalBencanaAct_7 extends Activity implements OnClickListener{
	
	Button kirimCatatan, kembali;
	private static final int DIALOG1 = 1; 
	private static final int DIALOG2 = 2; 
	public static final String myShared = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	EditText edtxtSumberDaya, edtxtKendala, edtxtKebutuhan, edtxtRencanaLanjut;
	
	Bundle b;
	Intent i;
	String phoneNumber, message="", sumberDaya, kendala, kebutuhan, rencanaTdkLanjut,
			mendirikanPosko = "1",melakukanRapat ="1",melaksanakanEvakuasi ="1",
			pelayananKesehatan = "1",dapurUmum ="1",bantuanMakanan ="1",pengarahanTenaga ="1";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lap_awal_bencana_7);
		
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
			showDialog(DIALOG2);
			break;
			
		default:
			break;
		}
    	return true;
    }
	
	private void initiateElement() {
		// TODO Auto-generated method stub
		kirimCatatan = (Button) findViewById(R.id.buttonKirimCatatan);
		kirimCatatan.setOnClickListener(this);
		kembali = (Button)findViewById(R.id.buttonKembali);
		kembali.setOnClickListener(this);
		
		edtxtSumberDaya = (EditText) findViewById(R.id.editTextSumberDaya);
		edtxtKendala = (EditText) findViewById(R.id.editTextKendala);
		edtxtKebutuhan = (EditText) findViewById(R.id.editTextKebutuhan);
		edtxtRencanaLanjut = (EditText) findViewById(R.id.editTextRencanaTidakLanjut);
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
		phoneNumber = mySharedPreferences.getString("nomorSMSGateway", "Belum diset");
		ubahInputan();
	}
	
	private void clearPreferences() {
		// TODO Auto-generated method stub
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDPetugas");
		editor.remove("IDBencana");
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
	
	private void ubahInputan()
	{
		if(mySharedPreferences.getString("MendirikanPosko","").equalsIgnoreCase("Sudah"))
		{
			mendirikanPosko = "1";
		}
		else
		{
			mendirikanPosko = "0";
		}
		if(mySharedPreferences.getString("MelakukanRapat","").equalsIgnoreCase("Sudah"))
		{
			melakukanRapat = "1";
		}
		else
		{
			melakukanRapat = "0";
		}
		if(mySharedPreferences.getString("MelaksanakanEvakuasi","").equalsIgnoreCase("Sudah"))
		{
			melaksanakanEvakuasi = "1";
		}
		else
		{
			melaksanakanEvakuasi = "0";
		}
		if(mySharedPreferences.getString("PelayananKesehatan","").equalsIgnoreCase("Sudah"))
		{
			pelayananKesehatan = "1";
		}
		else
		{
			pelayananKesehatan = "0";
		}
		if(mySharedPreferences.getString("DapurUmum","").equalsIgnoreCase("Sudah"))
		{
			dapurUmum = "1";
		}
		else
		{
			dapurUmum = "0";
		}
		if(mySharedPreferences.getString("BantuanMakanan","").equalsIgnoreCase("Sudah"))
		{
			bantuanMakanan = "1";
		}
		else
		{
			bantuanMakanan = "0";
		}
		if(mySharedPreferences.getString("PengarahanTenaga","").equalsIgnoreCase("Sudah"))
		{
			pengarahanTenaga = "1";
		}
		else
		{
			pengarahanTenaga = "0";
		}
	}
	
	private void kirimPesan() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(myShared, mode);
		
		sumberDaya = edtxtSumberDaya.getText().toString();
		kendala = edtxtKendala.getText().toString();
		kebutuhan = edtxtKebutuhan.getText().toString();
		rencanaTdkLanjut = edtxtRencanaLanjut.getText().toString();
		
		String inputanForm= mySharedPreferences.getString("IDPetugas","")+"~"+mySharedPreferences.getString("IDBencana","")+"~"
		+mySharedPreferences.getString("JumlahTimBPBD","")+"~"+mySharedPreferences.getString("JumlahTimDinkes","")+"~"
		+mySharedPreferences.getString("JumlahTimDinsos","")+"~"+mySharedPreferences.getString("JumlahTimPU","")+"~"
		+mySharedPreferences.getString("JenisKejadianBencana","")+"~"+mySharedPreferences.getString("WaktuBencana","")+"~"
		+mySharedPreferences.getString("TanggalBencana","")+"~"+mySharedPreferences.getString("LatitudeBencana","")+"~"
		+mySharedPreferences.getString("LongitudeBencana","")+"~"
		
		+mySharedPreferences.getString("LokasiDusunBencana","")+"~"+mySharedPreferences.getString("LokasiDesaBencana","")+"~"
		+mySharedPreferences.getString("LokasiKecamatanBencana","")+"~"+mySharedPreferences.getString("LokasiKabupatenBencana","")+"~"
		+mySharedPreferences.getString("PenyebabBencana","")+"~"
		
		+mySharedPreferences.getString("JumlahMeninggal","")+"~"+mySharedPreferences.getString("JumlahLukaBerat","")+"~"
		+mySharedPreferences.getString("JumlahLukaRingan","")+"~"+mySharedPreferences.getString("JumlahHilang","")+"~"
		+mySharedPreferences.getString("JumlahJiwaMengungsi","")+"~"+mySharedPreferences.getString("JumlahKKMengungsi","")+"~"

		+mySharedPreferences.getString("JumlahRumahRusak","")+"~"+mySharedPreferences.getString("JumlahKantorRusak","")+"~"
		+mySharedPreferences.getString("JumlahFasilitasKesehatanRusak","")+"~"+mySharedPreferences.getString("JumlahFasilitasPendidikanRusak","")+"~"
		+mySharedPreferences.getString("JumlahFasilitasUmumRusak","")+"~"+mySharedPreferences.getString("JumlahSaranaIbadahRusak","")+"~"

		+mySharedPreferences.getString("JumlahJembatanRusak","")+"~"+mySharedPreferences.getString("JumlahJalanRusak","")+"~"
		+mySharedPreferences.getString("JumlahTanggulRusak","")+"~"+mySharedPreferences.getString("JumlahSawahRusak","")+"~"
		+mySharedPreferences.getString("JumlahLahanRusak","")+"~"+mySharedPreferences.getString("JumlahLainLainRusak","")+"~"

		+mySharedPreferences.getString("WaktuPeninjauan","")+"~"+mySharedPreferences.getString("TanggalPeninjauan","")+"~"
		+mendirikanPosko+"~"+melakukanRapat+"~"
		+melaksanakanEvakuasi+"~"+pelayananKesehatan+"~"
		+dapurUmum+"~"+bantuanMakanan+"~"+pengarahanTenaga+"~"

		+sumberDaya+"~"+kendala+"~"
		+kebutuhan+"~"+rencanaTdkLanjut;
		
		int partSMS =  hitungPartSMS(inputanForm);
		String kodeSMS = kodeSMS();
		
		String temp = "",//Nampung Hasil Segmen 
		segment = "";//Buat Segmentasi
		int x = 0, counter = 0;// x itu penghitung karakter, counter untuk nomor segment sms
		for (int i = 0; i < inputanForm.length(); i++) { //PECAH INPUTAN 
			//UBAH SEGMEN
			if(x == 0)//Part SMS Baru
			{
				counter++;
				segment += kodeSMS+String.format("%02d", partSMS)+"LBA"+String.format("%02d", counter);//Header SMS (10 karakter)
				segment += inputanForm.charAt(i);
				x++;
			}
			else if(x > 0 && x < 110)
			{
				segment += inputanForm.charAt(i);
				x++;
				
				if(x==110)//saat 130 karakter pesan akan dikirim
				{
//					temp += segment+ " /*Selesai*/"+"\n\n";
					
					message = segment;
					sendMessage(message);
					
					x = 0;
					segment = "";
				}
				else if(i==inputanForm.length()-1)//saat karakter pesan habis akan dikirim
				{
//					temp += segment+ " /*Selesai*/"+"\n\n";
					
					message = segment;
					sendMessage(message);
				}
			}
		}
//		b = new Bundle();
//		b.putString("inputanForm", temp);
//		b.putString("temp", temp);
	}
	
	private void sendMessage(String isiPesan) {
		// TODO Auto-generated method stub
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNumber, 
					null,  
					isiPesan, 
					null, 
					null);
			Toast.makeText(getApplicationContext(), "Laporan Awal Bencana Berhasil Dikirim!", Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(),"Laporan Awal Bencana Gagal Dikirim",
					Toast.LENGTH_SHORT).show();
			ex.printStackTrace();
		}
	}
	
	private void sendMessage2(String isiPesan){
		Intent smsVIntent = new Intent(Intent.ACTION_VIEW);
		// prompts only sms-mms clients
		smsVIntent.setType("vnd.android-dir/mms-sms");
		
		// extra fields for number and message respectively
		smsVIntent.putExtra("address", phoneNumber);
		smsVIntent.putExtra("sms_body", isiPesan);
		try{
			startActivity(smsVIntent);
		} catch (Exception ex) {
			Toast.makeText(LapAwalBencanaAct_7.this, "Your sms has failed...",
					Toast.LENGTH_LONG).show();
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
				temp += "yyy"+"xx"+"LBA"+String.format("%02d", counter);
				temp += inputanForm.charAt(i);
				x++;
			}
			else if(x > 0 && x < 110)
			{
				temp += inputanForm.charAt(i);
				x++;
				
				if(x==110)
				{
					x = 0;
				}
				else if(i==inputanForm.length()-1)
				{	
					jumlah = (int) Math.ceil((double)temp.length()/120);
				}
			}
		}
		return jumlah;
	}
	
	private boolean cekInputanData() {
		// TODO Auto-generated method stub
		boolean cek = true;
		if(edtxtSumberDaya.getText().length()<=0||
				edtxtSumberDaya.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Sumber Daya!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKendala.getText().length()<=0||
				edtxtKendala.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Kendala!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtKebutuhan.getText().length()<=0||
				edtxtKebutuhan.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Kebutuhan!", Toast.LENGTH_SHORT).show();
		}
		else if(edtxtRencanaLanjut.getText().length()<=0||
				edtxtRencanaLanjut.getText().toString().equalsIgnoreCase(""))
		{
			cek = false;
			Toast.makeText(getBaseContext(), "Isi Rencana Tindak Lanjut!", Toast.LENGTH_SHORT).show();
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
				clearPreferences();
				LapAwalBencanaAct_7.this.finish();
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
		
		builder.setTitle("Anda Yakin Akan Mengirim Data Bencana Ini?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				kirimPesan();
				clearPreferences();
				LapAwalBencanaAct_7.this.finish();
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
			return createDialog1(LapAwalBencanaAct_7.this);
		case DIALOG2:
			return createDialog2(LapAwalBencanaAct_7.this);
		default:
			break;
		}
		return null;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonKirimCatatan:
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
//				kirimPesan();
//				Intent i = new Intent(getApplicationContext(), Test.class);
//				i.putExtras(b);
//				startActivity(i);
//				clearPreferences();
//				LapAwalBencanaAct_7.this.finish();
//			}
//			break;
		case R.id.buttonKembali:
			Intent i = new Intent(LapAwalBencanaAct_7.this, LapAwalBencanaAct_6.class);
			startActivity(i);
			LapAwalBencanaAct_7.this.finish();
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
			Intent i = new Intent(LapAwalBencanaAct_7.this, LapAwalBencanaAct_6.class);
			startActivity(i);
			LapAwalBencanaAct_7.this.finish();
		}
		return false;
	}
}
