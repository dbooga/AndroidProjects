package com.daviancorp.android.monsterhunter3udatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
 * Any subclass needs to:
 *  - override onCreate() to set title
 *  - override createFragmentOne() for detail fragments
 */

public abstract class GenericActivity extends SingleFragmentActivity {

	private static final String DIALOG_ABOUT = "about";
	
	String[] values = new String[] { "Monsters", "Weapons", "Armors",
			"Quests", "Items", "Combining", "Decorations", "Skills",
			"Locations", "Hunting Fleet" };
	
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	
	protected Fragment detail;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.app_name);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		setContentView(R.layout.activity_main);
		
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
					case 0:
						Intent intent = new Intent(GenericActivity.this, MonsterListActivity.class);
						startActivity(intent);
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
		case R.id.about:
			FragmentManager fm = getSupportFragmentManager();
			AboutDialogFragment dialog = new AboutDialogFragment();
			dialog.show(fm, DIALOG_ABOUT);
		
			return true;
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
