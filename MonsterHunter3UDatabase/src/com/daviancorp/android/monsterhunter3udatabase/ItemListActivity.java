package com.daviancorp.android.monsterhunter3udatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ItemListActivity extends GenericActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setTitle(R.string.items);
	}
	
	@Override
	protected Fragment createFragment() {
		super.detail = new ItemListFragment();
		return super.detail;
	}

}

