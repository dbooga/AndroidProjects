package com.daviancorp.android.adapter;

import com.daviancorp.android.monsterhunter3udatabase.MonsterListFragment;
import com.daviancorp.android.monsterhunter3udatabase.MonsterListSmallFragment;
import com.daviancorp.android.monsterhunter3udatabase.MonsterListLargeFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class MonsterListPagerAdapter extends FragmentPagerAdapter {
 
    public MonsterListPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // MonsterList fragment activity
            return new MonsterListFragment();
        case 1:
            // MonsterListSmall activity
            return new MonsterListSmallFragment();
        case 2:
            // MonsterListLarge fragment activity
            return new MonsterListLargeFragment();
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