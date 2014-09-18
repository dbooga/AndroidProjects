package com.daviancorp.android.ui.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;

import com.daviancorp.android.data.database.DataManager;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.adapter.ArenaQuestDetailPagerAdapter;
import com.daviancorp.android.ui.general.GenericTabActivity;

public class ArenaQuestDetailActivity extends GenericTabActivity implements
		ActionBar.TabListener {
	/** A key for passing a arena quest ID as a long */
	public static final String EXTRA_ARENA_QUEST_ID =
			"com.daviancorp.android.android.ui.detail.arena_quest_id";

	private ViewPager viewPager;
	private ArenaQuestDetailPagerAdapter mAdapter;
	private ActionBar actionBar;

	// Tab titles
	private String[] tabs = { "Detail", "Monsters", "Rewards"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		long id = getIntent().getLongExtra(EXTRA_ARENA_QUEST_ID, -1);
		setTitle(DataManager.get(getApplicationContext()).getArenaQuest(id).getName());

		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new ArenaQuestDetailPagerAdapter(getSupportFragmentManager(), id);
		viewPager.setAdapter(mAdapter);

		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
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
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPause() {
		super.onPause();
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