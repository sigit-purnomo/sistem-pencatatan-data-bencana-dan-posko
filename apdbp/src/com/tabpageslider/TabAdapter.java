package com.tabpageslider;

import com.apakor.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

	public TabAdapter(FragmentManager fm) {
	  // TODO Auto-generated constructor stub
		super(fm);
	 }
	 
	 public Fragment getItem(int index) {
		 switch (index){
		 case 0:
			 return new MenuBencanaAct();
		 case 1:
			 return new MenuPoskoAct();
		 case 2:
			 return new SettingAct();
		 }
		 return null;
	 }
	 
	 public int getCount() {
		 return 3;
	 }
	 
}