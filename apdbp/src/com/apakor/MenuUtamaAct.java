package com.apakor;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.tabpageslider.TabAdapter;

@TargetApi(14)
public class MenuUtamaAct extends FragmentActivity implements TabListener {

	private static final int DIALOG1 = 1; 
	public static final String MYPREFS = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	SharedPreferences prefs;
	
	private ViewPager viewPager;
	private TabAdapter mAdapter;
	private ActionBar actionBar;
	private String[] tabs = {"Laporan Bencana","Laporan Posko","Setting"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu_utama);
        
        clearPreferencesBencana();
        clearPreferencesPosko();
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabAdapter(getSupportFragmentManager());
        
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        for(String tab_name : tabs){
        	actionBar.addTab(actionBar.newTab().setText
        			(tab_name).setTabListener(this));
        }
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_utama, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) {
		case R.id.menu_keluar:
			showDialog(DIALOG1);
			break;
			
		case R.id.menu_ubah_pass:
			Intent i = new Intent(getApplicationContext(), ChangePassAct.class);
			startActivity(i);
			break;

		default:
			break;
		}
    	return true;
    }
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	private void clearPreferencesBencana() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(MYPREFS,
				mode);	
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDBencanaUpdate");
		editor.remove("JumlahSubTim");
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

		editor.remove("IDPetugas");
		editor.commit();
	}
	
	private void clearPreferencesPosko() {
		// TODO Auto-generated method stub
		mySharedPreferences = getApplication().getSharedPreferences(MYPREFS,
				mode);	
		Editor editor = mySharedPreferences.edit();
		
		editor.remove("IDPoskoUpdate");
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
	
	private Dialog createDialog1(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle("Anda Yakin Keluar Dari Menu Utama?");
		builder.setIcon(R.drawable.warning);
		builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mySharedPreferences = getApplication().getSharedPreferences(MYPREFS,
						mode);		
				Editor editor = mySharedPreferences.edit();
				editor.putBoolean("sesion", false);
				
				editor.commit();
				
				Intent a = new Intent(getApplicationContext(), LoginAct.class);
				startActivity(a);
				
				MenuUtamaAct.this.finish();
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
			return createDialog1(MenuUtamaAct.this);
		default:
			break;
		}
		return null;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		clearPreferencesBencana();
        clearPreferencesPosko();
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
