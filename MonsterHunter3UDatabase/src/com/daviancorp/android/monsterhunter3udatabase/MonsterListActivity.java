package com.daviancorp.android.monsterhunter3udatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

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
	
}
