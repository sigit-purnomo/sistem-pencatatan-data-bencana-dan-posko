package com.apakor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Test extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		TextView txtForm = (TextView) findViewById(R.id.txtTestInputan);
		TextView txtTemp = (TextView) findViewById(R.id.txtTestTemp);
		TextView txtParts = (TextView) findViewById(R.id.txtParts);
		txtTemp.setVisibility(View.GONE);
		txtParts.setVisibility(View.GONE);
		Bundle b = getIntent().getExtras();
		
		StringBuilder formbuilder  = new StringBuilder();
		formbuilder.append("Inputan Form : "+b.getString("inputanForm")+"\n\n" +b.getString("inputanForm").length());
		txtForm.setText(formbuilder.toString());
		
//		StringBuilder tempbuilder  = new StringBuilder();
//		tempbuilder.append("SMS Segment : "+b.getString("temp")+"\n\n" +b.getString("temp").length());
//		txtTemp.setText(tempbuilder.toString());
		
//		StringBuilder partsbuilder  = new StringBuilder();
//		partsbuilder.append("SMS Parts : "+b.getStringArrayList("parts"));
//		txtParts.setText(tempbuilder.toString());
				
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

}
