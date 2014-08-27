package com.daviancorp.android.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.ui.detail.MonsterDetailFragment;

public class MonsterDetailPagerAdapter extends FragmentPagerAdapter {
	
	private long monsterId;

	public MonsterDetailPagerAdapter(FragmentManager fm, long id) {
		super(fm);
		this.monsterId = id;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return MonsterDetailFragment.newInstance(monsterId);
		case 1:
			return MonsterDetailFragment.newInstance(3);
		case 2:
			return MonsterDetailFragment.newInstance(70);
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