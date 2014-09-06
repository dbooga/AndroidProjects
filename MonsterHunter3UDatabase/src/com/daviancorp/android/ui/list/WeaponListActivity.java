package com.daviancorp.android.ui.list;

import java.io.IOException;

import android.graphics.drawable.Drawable;
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
//	private String[] tabs = {"Great Sword", "Long Sword",
//			"Sword and Shield", "Dual Blades", "Hammer", "Hunting Horn",
//			"Lance", "Gunlance", "Switch Axe", "Light Bowgun", "Heavy Bowgun",
//			"Bow" };
//	private String[] tabs = {
//			"icons_great_sword/great_sword1.png",	
//			"icons_long_sword/long_sword1.png",
//			"icons_sword_and_shield/sword_and_shield1.png",
//			"icons_dual_blades/dual_blades1.png",
//			"icons_hammer/hammer1.png",
//			"icons_hunting_horn/hunting_horn1.png",
//			"icons_lance/lance1.png",
//			"icons_gunlance/gunlance1.png",
//			"icons_switch_axe/switch_axe1.png",
//			"icons_light_bowgun/light_bowgun1.png",
//			"icons_heavy_bowgun/heavy_bowgun1.png",
//			"icons_bow/bow1.png",
//	};
	private int[] tabs = {
			R.drawable.great_sword1,	
			R.drawable.long_sword1,
			R.drawable.sword_and_shield1,
			R.drawable.dual_blades1,
			R.drawable.hammer1,
			R.drawable.hunting_horn1,
			R.drawable.lance1,
			R.drawable.gunlance1,
			R.drawable.switch_axe1,
			R.drawable.light_bowgun1,
			R.drawable.heavy_bowgun1,
			R.drawable.bow1,
	};

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
		for (int tab_icon : tabs) {
			
//			String cellImage = "icons_weapons/" + tab_icon;
//
//			Drawable itemImage = null;
//
//			try {
//				itemImage = Drawable.createFromStream(
//						getAssets().open(cellImage), null);
//				
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}		

			actionBar.addTab(actionBar.newTab()
					.setTabListener(this).setIcon(tab_icon));
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
