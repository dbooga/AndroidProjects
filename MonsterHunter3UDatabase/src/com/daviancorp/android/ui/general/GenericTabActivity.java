package com.daviancorp.android.ui.general;

import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.list.ArmorListActivity;
import com.daviancorp.android.ui.list.CombiningListActivity;
import com.daviancorp.android.ui.list.DecorationListActivity;
import com.daviancorp.android.ui.list.HuntingFleetListActivity;
import com.daviancorp.android.ui.list.ItemListActivity;
import com.daviancorp.android.ui.list.LocationListActivity;
import com.daviancorp.android.ui.list.MonsterListActivity;
import com.daviancorp.android.ui.list.QuestListActivity;
import com.daviancorp.android.ui.list.SkillTreeListActivity;

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

	String[] values = new String[] { "Monsters", "Weapons", "Armors", "Quests",
			"Items", "Combining", "Decorations", "Skills", "Locations",
			"Hunting Fleet" };

	protected ListView mDrawerList;
	protected DrawerLayout mDrawerLayout;

	protected Fragment detail;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.app_name);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeAsUpIndicator(R.drawable.drawer);
		getActionBar().setHomeButtonEnabled(true);

		setContentView(R.layout.activity_tab_list);

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, values);

		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// sendResult(Activity.RESULT_OK, position);
				Intent intent;

				switch (position) {
				case MONSTERS:
					intent = new Intent(GenericTabActivity.this,
							MonsterListActivity.class);
					startActivity(intent);
					break;
				case WEAPONS:
					break;
				case ARMORS:
					intent = new Intent(GenericTabActivity.this,
							ArmorListActivity.class);
					startActivity(intent);
					break;
				case QUESTS:
					intent = new Intent(GenericTabActivity.this,
							QuestListActivity.class);
					startActivity(intent);
					break;
				case ITEMS:
					intent = new Intent(GenericTabActivity.this,
							ItemListActivity.class);
					startActivity(intent);
					break;
				case COMBINING:
					intent = new Intent(GenericTabActivity.this,
							CombiningListActivity.class);
					startActivity(intent);
					break;
				case DECORATIONS:
					intent = new Intent(GenericTabActivity.this,
							DecorationListActivity.class);
					startActivity(intent);
					break;
				case SKILLS:
					intent = new Intent(GenericTabActivity.this,
							SkillTreeListActivity.class);
					startActivity(intent);
					break;
				case LOCATIONS:
					intent = new Intent(GenericTabActivity.this,
							LocationListActivity.class);
					startActivity(intent);
					break;
				case HUNTINGFLEET:
					intent = new Intent(GenericTabActivity.this,
							HuntingFleetListActivity.class);
					startActivity(intent);
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
			} else
				mDrawerLayout.openDrawer(mDrawerList);
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
