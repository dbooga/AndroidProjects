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

public class MainActivity extends SingleFragmentActivity {
	
	private static final String DIALOG_ABOUT = "about";

	private Fragment detail;
	private Fragment drawer;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);

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

	@Override
	protected Fragment createFragment() {
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

