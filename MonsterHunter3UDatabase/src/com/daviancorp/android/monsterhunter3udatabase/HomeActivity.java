package com.daviancorp.android.monsterhunter3udatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class HomeActivity extends GenericActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setTitle(R.string.app_name);
	}
	
	@Override
	protected Fragment createFragment() {
		super.drawer = new DrawerFragment();
		return super.drawer;
	}

}

