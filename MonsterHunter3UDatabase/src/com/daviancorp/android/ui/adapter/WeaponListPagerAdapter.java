package com.daviancorp.android.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.ui.list.WeaponListFragment;

public class WeaponListPagerAdapter extends FragmentPagerAdapter {

	public WeaponListPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		
		// "Great Sword", "Long Sword", "Sword and Shield",
		// "Dual Blades", "Hammer", "Hunting Horn", "Lance",
		// "Gunlance", "Switch Axe", "Light Bowgun",
		// "Heavy Bowgun", "Bow"

		switch (index) {
		case 0:
			return WeaponListFragment.newInstance("Great Sword");
		case 1:
			return WeaponListFragment.newInstance("Long Sword");
		case 2:
			return WeaponListFragment.newInstance("Sword and Shield");
		case 3:
			return WeaponListFragment.newInstance("Dual Blades");
		case 4:
			return WeaponListFragment.newInstance("Hammer");
		case 5:
			return WeaponListFragment.newInstance("Hunting Horn");
		case 6:
			return WeaponListFragment.newInstance("Lance");
		case 7:
			return WeaponListFragment.newInstance("Gunlance");
		case 8:
			return WeaponListFragment.newInstance("Switch Axe");
		case 9:
			return WeaponListFragment.newInstance("Light Bowgun");
		case 10:
			return WeaponListFragment.newInstance("Heavy Bowgun");
		case 11:
			return WeaponListFragment.newInstance("Bow");
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 12;
	}

}