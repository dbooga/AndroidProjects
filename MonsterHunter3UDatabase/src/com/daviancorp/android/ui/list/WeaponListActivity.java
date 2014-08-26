package com.daviancorp.android.ui.list;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;

import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.adapter.WeaponListPagerAdapter;
import com.daviancorp.android.ui.general.GenericTabActivity;

public class WeaponListActivity extends GenericTabActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private WeaponListPagerAdapter mAdapter;
	private ActionBar actionBar;
	private int toggle;

	// "Great Sword", "Long Sword", "Sword and Shield",
	// "Dual Blades", "Hammer", "Hunting Horn", "Lance",
	// "Gunlance", "Switch Axe", "Light Bowgun",
	// "Heavy Bowgun", "Bow"

	// Tab titles
	private String[] tabs = {"Great Sword", "Long Sword",
			"Sword and Shield", "Dual Blades", "Hammer", "Hunting Horn",
			"Lance", "Gunlance", "Switch Axe", "Light Bowgun", "Heavy Bowgun",
			"Bow" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.weapons);

		toggle = 0;

		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new WeaponListPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);

		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this).setIcon(R.drawable.sword));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
