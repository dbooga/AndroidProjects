package com.daviancorp.android.monsterhunter3udatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.daviancorp.android.adapter.MonsterListPagerAdapter;



public class MonsterListActivity extends GenericTabActivity implements ActionBar.TabListener{
	
	private ViewPager viewPager;
    private MonsterListPagerAdapter mAdapter;
    private ActionBar actionBar;
    
 // Tab titles
    private String[] tabs = { "All", "Small", "Large" };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.monsters);
		
		// Initialization
        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MonsterListPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        
        actionBar = getSupportActionBar();
     //   actionBar.setHomeButtonEnabled(false);
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
//	
//	@Override
//	protected Fragment createFragment() {
//		return null;
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.monsterlist, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.monster_listview:
//			FragmentManager fm = getSupportFragmentManager();
//			AboutDialogFragment dialog = new AboutDialogFragment();
//			dialog.show(fm, DIALOG_ABOUT);
		
			// Create new fragment and transaction
			Fragment newFragment = new HomeFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack
			transaction.replace(R.id.fragment_container, newFragment);
//			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
			
			
			return true;
		case R.id.monster_gridview:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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
