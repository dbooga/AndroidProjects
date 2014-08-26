package com.daviancorp.android.ui.list;

import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.general.GenericActivity;
import com.daviancorp.android.ui.general.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LocationListActivity extends GenericActivity {

	private int toggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.locations);
	}

	@Override
	protected Fragment createFragment() {
		super.detail = new LocationListFragment();
		return super.detail;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.locationlist, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Fragment newFragment;
		FragmentTransaction transaction;

		switch (item.getItemId()) {
		case R.id.location_listview:
			// FragmentManager fm = getSupportFragmentManager();
			// AboutDialogFragment dialog = new AboutDialogFragment();
			// dialog.show(fm, DIALOG_ABOUT);

			// Create new fragment and transaction
			newFragment = new HomeFragment();
			transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.fragment_container, newFragment);
			// transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();

			return true;
		case R.id.location_gridview:
			newFragment = new LocationGridFragment();
			transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.fragment_container, newFragment);
			// transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (toggle == 1) {
			finish();
		}
	}
	

}
