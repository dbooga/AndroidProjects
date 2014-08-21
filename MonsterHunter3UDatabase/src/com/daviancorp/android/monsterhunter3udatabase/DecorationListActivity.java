package com.daviancorp.android.monsterhunter3udatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class DecorationListActivity extends GenericActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.decorations);
	}

	@Override
	protected Fragment createFragment() {
		super.detail = new DecorationListFragment();
		return super.detail;
	}

}
