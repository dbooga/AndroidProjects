package com.daviancorp.android.monsterhunter3udatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

public class MonsterListActivity extends GenericActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Monsters");
	}
	
	@Override
	protected Fragment createFragment() {
		super.detail = new MonsterListFragment();
		return super.detail;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
//			FragmentManager fm = getSupportFragmentManager();
//			AboutDialogFragment dialog = new AboutDialogFragment();
//			dialog.show(fm, DIALOG_ABOUT);
		
			// Create new fragment and transaction
			Fragment newFragment = new HomeFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.fragment_container, newFragment);
//			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
			
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
