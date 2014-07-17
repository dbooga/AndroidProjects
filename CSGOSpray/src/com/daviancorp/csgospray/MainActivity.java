package com.daviancorp.csgospray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends DoubleFragmentActivity {
	public static final int OPTION_PATTERN = 0;
	public static final int OPTION_COMPENSATION = 1;
	public static final int OPTION_INVERTED = 2;
	private int option;
	
	private Fragment detail;
	private Fragment drawer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.spray_pattern);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.pattern:
			option = OPTION_PATTERN;
			((DetailFragment)detail).updateGunView();
			setTitle(R.string.spray_pattern);
			return true;
		case R.id.compensation:
			option = OPTION_COMPENSATION;
			((DetailFragment)detail).updateGunView();
			setTitle(R.string.spray_compensation);
			return true;
		case R.id.inverted:
			option = OPTION_INVERTED;
			((DetailFragment)detail).updateGunView();
			setTitle(R.string.spray_inverted);
			return true;
		case R.id.about:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected Fragment createFragmentOne() {
		detail = new DetailFragment();
		return detail;
	}

	@Override
	protected Fragment createFragmentTwo() {
		drawer = new DrawerFragment();
		return drawer;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	public Fragment getDetail() {
		return detail;
	}

	public Fragment getDrawer() {
		return drawer;
	}

	public int getOption() {
		return option;
	}

}
