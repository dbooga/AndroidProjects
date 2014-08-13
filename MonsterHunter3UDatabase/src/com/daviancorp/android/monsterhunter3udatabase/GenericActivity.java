package com.daviancorp.android.monsterhunter3udatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.daviancorp.android.monsterhunter3udatabase.DoubleFragmentActivity;

/*
 * Any subclass needs to:
 *  - override onCreate() to set title
 *  - override createFragmentOne() for detail fragments
 */

public abstract class GenericActivity extends DoubleFragmentActivity {
	
	private static final String DIALOG_ABOUT = "about";

	protected Fragment detail;
	protected Fragment drawer;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			DrawerLayout dLayout = ((DrawerFragment) drawer).getDrawerLayout();
			ListView dList = ((DrawerFragment) drawer).getDrawerList();
			if (dLayout.isDrawerVisible(dList)) {
				dLayout.closeDrawers();
			}
			else dLayout.openDrawer(dList);
			return true;
		case R.id.about:
			FragmentManager fm = getSupportFragmentManager();
			AboutDialogFragment dialog = new AboutDialogFragment();
			dialog.show(fm, DIALOG_ABOUT);
		
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Drawer Fragment
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

}
