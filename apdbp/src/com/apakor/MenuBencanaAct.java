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

public class MenuBencanaAct extends Fragment implements OnClickListener{
	
	Intent i;
	Button lapAwalBencana, lapPerkembanganBencana;
	
	public static final String MYPREFS = "mySharedPreferences";
	public static final int mode = Activity.MODE_PRIVATE;
	private SharedPreferences mySharedPreferences;
	SharedPreferences prefs;
	String nomorSMSGateway;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_menu_bencana, container, false);
		
		lapAwalBencana = (Button) view.findViewById(R.id.buttonLapAwalBencana);
		lapAwalBencana.setOnClickListener(this);
		lapPerkembanganBencana = (Button) view.findViewById(R.id.buttonLapPerkembanganBencana);
		lapPerkembanganBencana.setOnClickListener(this);
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
		case R.id.buttonLapAwalBencana:
			if(nomorSMSGateway.equalsIgnoreCase("Belum diset")||nomorSMSGateway.equalsIgnoreCase("")||nomorSMSGateway.length()<=0)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Set Nomor Tujuan SMS Gateway Dahulu", Toast.LENGTH_SHORT).show();
			}
			else
			{
				i = new Intent(v.getContext(), LapAwalBencanaAct_1.class);
				startActivity(i);
			}
			
			break;
		case R.id.buttonLapPerkembanganBencana:
			if(nomorSMSGateway.equalsIgnoreCase("Belum diset")||nomorSMSGateway.equalsIgnoreCase("")||nomorSMSGateway.length()<=0)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Set Nomor Tujuan SMS Gateway Dahulu", Toast.LENGTH_SHORT).show();
			}
			else
			{
				i = new Intent(v.getContext(), LapLanjutanBencanaAct_2.class);
				startActivity(i);
			}
			
			break;	

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
