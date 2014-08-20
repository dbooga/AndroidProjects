package com.daviancorp.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.monsterhunter3udatabase.MonsterListFragment;
 
public class MonsterListPagerAdapter extends FragmentPagerAdapter {
 
    public MonsterListPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // MonsterList fragment activity
            return MonsterListFragment.newInstance(null);
        case 1:
            // MonsterListSmall activity
            return MonsterListFragment.newInstance("Small");
        case 2:
            // MonsterListLarge fragment activity
            return MonsterListFragment.newInstance("Large");
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