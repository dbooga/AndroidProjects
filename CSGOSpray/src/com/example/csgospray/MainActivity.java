package com.example.csgospray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

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
