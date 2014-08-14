package com.daviancorp.android.monsterhunter3udatabase;

import android.support.v4.app.Fragment;

public class MonsterListActivity extends GenericActivity{
	
	@Override
	protected Fragment createFragment() {
		super.detail = new MonsterLIstFragment();
		return super.detail;
	}
	
}
