package com.apakor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuPoskoAct extends Fragment implements OnClickListener{
	
	public static final String MYPREFS = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	SharedPreferences prefs;
	String nomorSMSGateway;
	
	Intent i;
	Button lapAwalPosko, lapPerkembanganPosko;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_menu_posko, container, false);
		
		lapAwalPosko = (Button) view.findViewById(R.id.buttonLapAwalPosko);
		lapAwalPosko.setOnClickListener(this);
		lapPerkembanganPosko = (Button) view.findViewById(R.id.buttonLapPerkembanganPosko);
		lapPerkembanganPosko.setOnClickListener(this);
		loadPreferences();
		return view;
	}
	
	private void loadPreferences() {
		// TODO Auto-generated method stub
		mySharedPreferences = getActivity().getApplication().getSharedPreferences(MYPREFS,
				mode);		
		nomorSMSGateway = mySharedPreferences.getString("nomorSMSGateway", "Belum diset");
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLapAwalPosko:
			if(nomorSMSGateway.equalsIgnoreCase("Belum diset")||nomorSMSGateway.equalsIgnoreCase("")||nomorSMSGateway.length()<=0)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Set Nomor Tujuan SMS Gateway Dahulu", Toast.LENGTH_SHORT).show();
			}
			else
			{
				i = new Intent(v.getContext(),LapAwalPoskoAct1.class);
				startActivity(i);
			}
			
			break;
		case R.id.buttonLapPerkembanganPosko:
			if(nomorSMSGateway.equalsIgnoreCase("Belum diset")||nomorSMSGateway.equalsIgnoreCase("")||nomorSMSGateway.length()<=0)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Set Nomor Tujuan SMS Gateway Dahulu", Toast.LENGTH_SHORT).show();
			}
			else
			{
				i = new Intent(v.getContext(),LapLanjutanPoskoAct_1.class);
				startActivity(i);
			}
			
			break;
//		case R.id.buttonKembaliPosko:
//			MenuPoskoAct.this.finish();
//			break;
		default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadPreferences();
	}
	
}
