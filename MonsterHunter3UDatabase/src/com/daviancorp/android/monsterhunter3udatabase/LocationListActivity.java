package com.daviancorp.android.monsterhunter3udatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class LocationListActivity extends GenericActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setTitle(R.string.locations);
	}
	
	@Override
	protected Fragment createFragment() {
		super.detail = new LocationListFragment();
		return super.detail;
	}

}

