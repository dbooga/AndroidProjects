package com.daviancorp.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.monsterhunter3udatabase.MonsterListFragment;
import com.daviancorp.android.monsterhunter3udatabase.QuestListFragment;
 
public class QuestListPagerAdapter extends FragmentPagerAdapter {
 
    public QuestListPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // MonsterList fragment activity
            return QuestListFragment.newInstance("Village", null);
        case 1:
            // MonsterList fragment activity
            return QuestListFragment.newInstance("Port", null);
        case 2:
            // MonsterList fragment activity
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