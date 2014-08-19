package com.daviancorp.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.monsterhunter3udatabase.QuestListFragment;

public class ArmorListPagerAdapter extends FragmentPagerAdapter {

	public ArmorListPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return QuestListFragment.newInstance("Both", null);
		case 1:
			return QuestListFragment.newInstance("Blade", null);
		case 2:
			return QuestListFragment.newInstance("Gunner", null);
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}