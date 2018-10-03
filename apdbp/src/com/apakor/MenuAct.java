package com.apakor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuAct extends Activity implements OnClickListener{
	Intent i;
	Button lapBencana, lapPosko, setting, keluar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_utama);
		
		getActionBar().setTitle("Menu Utama");
		setBehavior();	
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// TODO Auto-generated method stub
//		getMenuInflater().inflate(R.menu.activity_menu_utama, menu);
//		return true;
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case R.id.menu_setting:
//			i = new Intent(getApplicationContext(), SettingAct.class);
//			MenuAct.this.startActivity(i);
//			break;
//		case R.id.menu_keluar:
//			MenuAct.this.finish();
//			break;
//		default:
//			break;
//		}
//		return true;
//	}
	
	private void setBehavior()
	{
		lapBencana = (Button) findViewById(R.id.buttonLapBencana);
		lapBencana.setOnClickListener(this);
		lapPosko = (Button) findViewById(R.id.buttonLapPosko);
		lapPosko.setOnClickListener(this);
		setting = (Button) findViewById(R.id.buttonSetting);
		setting.setOnClickListener(this);
		keluar = (Button) findViewById(R.id.buttonKeluar);
		keluar.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLapBencana:
			i = new Intent(v.getContext(),MenuBencanaAct.class);
			MenuAct.this.startActivity(i);
			break;
		case R.id.buttonLapPosko:
			i = new Intent(v.getContext(),MenuPoskoAct.class);
			MenuAct.this.startActivity(i);
			break;
		case R.id.buttonSetting:
			i = new Intent(v.getContext(), SettingAct.class);
			MenuAct.this.startActivity(i);
			break;
		case R.id.buttonKeluar:
			MenuAct.this.finish();
			break;

		default:
			break;
		}
	}
}
