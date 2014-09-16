package com.daviancorp.android.ui.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.daviancorp.android.data.database.DataManager;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.adapter.DecorationDetailPagerAdapter;
import com.daviancorp.android.ui.dialog.WishlistDataAddDialogFragment;
import com.daviancorp.android.ui.general.GenericTabActivity;

public class DecorationDetailActivity extends GenericTabActivity implements
		ActionBar.TabListener {
	/** A key for passing a decoration ID as a long */
	public static final String EXTRA_DECORATION_ID =
			"com.daviancorp.android.android.ui.detail.decoration_id";

	private static final String DIALOG_WISHLIST_ADD = "wishlist_add";
	private static final int REQUEST_ADD = 0;
	
	private ViewPager viewPager;
	private DecorationDetailPagerAdapter mAdapter;
	private ActionBar actionBar;

	private long id;
	
	// Tab titles
	private String[] tabs = { "Detail", "Skills", "Components" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		id = getIntent().getLongExtra(EXTRA_DECORATION_ID, -1);
		setTitle(DataManager.get(getApplicationContext()).getDecoration(id).getName());

		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new DecorationDetailPagerAdapter(getSupportFragmentManager(), id);
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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_wishlist_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.wishlist_add:
				FragmentManager fm = getSupportFragmentManager();
				WishlistDataAddDialogFragment dialogCopy = WishlistDataAddDialogFragment
						.newInstance(id);
				dialogCopy.show(fm, DIALOG_WISHLIST_ADD);
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
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
