package com.daviancorp.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.monsterhunter3udatabase.MonsterGridFragment;
 
public class MonsterGridPagerAdapter extends FragmentPagerAdapter {
 
    public MonsterGridPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // MonsterList fragment activity
            return MonsterGridFragment.newInstance(null);
        case 1:
            // MonsterListSmall activity
            return MonsterGridFragment.newInstance("Small");
        case 2:
            // MonsterListLarge fragment activity
            return MonsterGridFragment.newInstance("Large");
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