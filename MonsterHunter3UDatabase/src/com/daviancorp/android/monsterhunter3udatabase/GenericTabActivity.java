package com.daviancorp.android.monsterhunter3udatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Any subclass needs to:
 *  - override onCreate() to set title
 *  - override createFragmentOne() for detail fragments
 */

public abstract class GenericTabActivity extends ActionBarActivity {

	protected static final String DIALOG_ABOUT = "about";
	
	private final static int MONSTERS = 0;
	private final static int WEAPONS = 1;
	private final static int ARMORS = 2;
	private final static int QUESTS = 3;
	private final static int ITEMS = 4;
	private final static int COMBINING = 5;
	private final static int DECORATIONS = 6;
	private final static int SKILLS = 7;
	private final static int LOCATIONS = 8;
	private final static int HUNTINGFLEET = 9;
	
	String[] values = new String[] { "Monsters", "Weapons", "Armors",
			"Quests", "Items", "Combining", "Decorations", "Skills",
			"Locations", "Hunting Fleet" };
	
	protected ListView mDrawerList;
	protected DrawerLayout mDrawerLayout;
	
	protected Fragment detail;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.app_name);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		setContentView(R.layout.activity_monsterlist);
		
		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, values);
		
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//sendResult(Activity.RESULT_OK, position);
				switch(position) {
				case MONSTERS:
					Intent monster_intent = new Intent(GenericTabActivity.this, MonsterListActivity.class);
					startActivity(monster_intent);
					break;
				case WEAPONS:
					break;
				case ARMORS:
					break;
				case QUESTS:
					break;
				case ITEMS:
					Intent item_intent = new Intent(GenericTabActivity.this, ItemListActivity.class);
					startActivity(item_intent);
					break;
				case COMBINING:
					Intent combining_intent = new Intent(GenericTabActivity.this, CombiningListActivity.class);
					startActivity(combining_intent);
					break;
				case DECORATIONS:
					break;
				case SKILLS:
					break;
				case LOCATIONS:
					Intent location_intent = new Intent(GenericTabActivity.this, LocationListActivity.class);
					startActivity(location_intent);
					break;
				case HUNTINGFLEET:
					break;
			}
				mDrawerLayout.closeDrawers();
			}
		});
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDrawerLayout.isDrawerVisible(mDrawerList)) {
				mDrawerLayout.closeDrawers();
			}
			else mDrawerLayout.openDrawer(mDrawerList);
			return true;
//		case R.id.about:
//			FragmentManager fm = getSupportFragmentManager();
//			AboutDialogFragment dialog = new AboutDialogFragment();
//			dialog.show(fm, DIALOG_ABOUT);
//		
//			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	public Fragment getDetail() {
		return detail;
	}

	public DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	public ListView getDrawerList() {
		return mDrawerList;
	}
}
