package com.daviancorp.android.ui.general;

import com.daviancorp.android.monsterhunter3udatabase.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class HomeActivity extends GenericActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
	}

	@Override
	protected Fragment createFragment() {
		super.detail = new HomeFragment();
		return super.detail;
	}

}
