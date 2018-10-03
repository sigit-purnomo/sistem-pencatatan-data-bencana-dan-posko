package com.apakor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingAct extends Fragment implements OnClickListener{
	
	Intent i;
	Button cekGPS, setSMS, kembaliSetting;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_setting, container, false);
		
		cekGPS = (Button) view.findViewById(R.id.buttonCekGPS);
		cekGPS.setOnClickListener(this);
		setSMS = (Button) view.findViewById(R.id.buttonSetSMSGate);
		setSMS.setOnClickListener(this);
		
		return view;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonCekGPS:
			i = new Intent(getActivity(), CheckGPSAct.class);
			startActivity(i);
			break;
		case R.id.buttonSetSMSGate:
			i = new Intent(getActivity(), ChangeSMSGateAct.class);
			startActivity(i);
			break;
		default:
			break;
		}
	}

}
