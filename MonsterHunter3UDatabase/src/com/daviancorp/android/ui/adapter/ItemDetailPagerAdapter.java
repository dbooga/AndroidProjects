package com.daviancorp.android.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daviancorp.android.ui.detail.ItemCarveFragment;
import com.daviancorp.android.ui.detail.ItemComponentFragment;
import com.daviancorp.android.ui.detail.ItemDetailFragment;
import com.daviancorp.android.ui.detail.ItemLocationFragment;
import com.daviancorp.android.ui.detail.ItemQuestFragment;

public class ItemDetailPagerAdapter extends FragmentPagerAdapter {
	
	private long itemId;

	public ItemDetailPagerAdapter(FragmentManager fm, long id) {
		super(fm);
		this.itemId = id;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return ItemDetailFragment.newInstance(itemId);
		case 1:
			return ItemQuestFragment.newInstance(itemId);
		case 2:
			return ItemCarveFragment.newInstance(itemId);
		case 3:
			return ItemLocationFragment.newInstance(itemId);
		case 4:
			return ItemComponentFragment.newInstance(itemId);
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 5;
	}

}