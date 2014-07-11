package com.daviancorp.csgospray;

import android.support.v4.app.Fragment;

public class MainActivity extends DoubleFragmentActivity {

	@Override
	protected Fragment createFragmentOne(){
		return new DrawerFragment();
	}

	@Override
	protected Fragment createFragmentTwo() {
		return new DetailFragment();

	}

}
