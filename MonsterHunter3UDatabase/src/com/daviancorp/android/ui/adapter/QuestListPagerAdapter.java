package com.daviancorp.android.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.ui.list.QuestListFragment;

public class QuestListPagerAdapter extends FragmentPagerAdapter {

	public QuestListPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return QuestListFragment.newInstance("Village", null);
		case 1:
			return QuestListFragment.newInstance("Port", null);
		case 2:
			return QuestListFragment.newInstance("DLC", null);
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