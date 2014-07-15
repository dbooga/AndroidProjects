package com.daviancorp.csgospray;

import android.support.v4.app.Fragment;

public class MainActivity extends DoubleFragmentActivity {
	private Fragment detail;
	private Fragment drawer;
	
	@Override
	protected Fragment createFragmentOne(){
		detail = new DetailFragment();
		return detail;
	}

	@Override
	protected Fragment createFragmentTwo() {
		drawer = new DrawerFragment();
		return drawer;
	}

	public Fragment getDetail() {
		return detail;
	}

	public Fragment getDrawer() {
		return drawer;
	}

}
