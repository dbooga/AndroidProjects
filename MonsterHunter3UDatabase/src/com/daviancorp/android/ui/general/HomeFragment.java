package com.daviancorp.android.ui.general;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.daviancorp.android.data.object.Quest;
import com.daviancorp.android.loader.QuestLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.list.ArmorListActivity;
import com.daviancorp.android.ui.list.CombiningListActivity;
import com.daviancorp.android.ui.list.DecorationListActivity;
import com.daviancorp.android.ui.list.HuntingFleetListActivity;
import com.daviancorp.android.ui.list.ItemListActivity;
import com.daviancorp.android.ui.list.LocationGridActivity;
import com.daviancorp.android.ui.list.MonsterGridActivity;
import com.daviancorp.android.ui.list.QuestListActivity;
import com.daviancorp.android.ui.list.SkillTreeListActivity;
import com.daviancorp.android.ui.list.WeaponGridActivity;
import com.daviancorp.android.ui.list.WishlistListActivity;

public class HomeFragment extends Fragment {

	private ImageView mLogo;
	private GridView gridView;
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
	private final static int WISHLISTS = 10;

	static final String[] numbers = new String[] { "Monsters", "Weapons",
			"Armors", "Quests", "Items", "Combining", "Decorations", "Skills",
			"Locations", "Hunting Fleet", "Wishlists" };

	private ProgressDialog progress;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LoaderManager lm = getLoaderManager();
		lm.initLoader(0, null, new DummyLoaderCallbacks());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, parent, false);
		
		mLogo = (ImageView) v.findViewById(R.id.logo);
		gridView = (GridView) v.findViewById(R.id.grid_home);

		mLogo.setImageResource(R.drawable.mh3_cleaned);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.fragment_home_grid_text, numbers);

		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Intent intent;

				switch (position) {
				case MONSTERS:
					intent = new Intent(getActivity(),MonsterGridActivity.class);
					startActivity(intent);
					break;
				case WEAPONS:
					intent = new Intent(getActivity(), WeaponGridActivity.class);
					startActivity(intent);
					break;
				case ARMORS:
					intent = new Intent(getActivity(), ArmorListActivity.class);
					startActivity(intent);
					break;
				case QUESTS:
					intent = new Intent(getActivity(), QuestListActivity.class);
					startActivity(intent);
					break;
				case ITEMS:
					intent = new Intent(getActivity(), ItemListActivity.class);
					startActivity(intent);
					break;
				case COMBINING:
					intent = new Intent(getActivity(),
							CombiningListActivity.class);
					startActivity(intent);
					break;
				case DECORATIONS:
					intent = new Intent(getActivity(),
							DecorationListActivity.class);
					startActivity(intent);
					break;
				case SKILLS:
					intent = new Intent(getActivity(),
							SkillTreeListActivity.class);
					startActivity(intent);
					break;
				case LOCATIONS:
					intent = new Intent(getActivity(),
							LocationGridActivity.class);
					startActivity(intent);
					break;
				case HUNTINGFLEET:
					intent = new Intent(getActivity(),
							HuntingFleetListActivity.class);
					startActivity(intent);
					break;
				case WISHLISTS:
					intent = new Intent(getActivity(),
							WishlistListActivity.class);
					startActivity(intent);
					break;					
				}
			}
		});
		
		return v;
	}
	
	/*
	 * For dummy query
	 */
	private class DummyLoaderCallbacks implements LoaderCallbacks<Quest> {
		
		@Override
		public Loader<Quest> onCreateLoader(int id, Bundle args) {
			progress = new ProgressDialog(getActivity());
			progress.setTitle("Loading");
			progress.setMessage("Loading database...");
			progress.show();
			return new QuestLoader(getActivity(), 1);
		}
		
		@Override
		public void onLoadFinished(Loader<Quest> loader, Quest dummy) {
			progress.dismiss();
		}
		
		@Override
		public void onLoaderReset(Loader<Quest> loader) {
			// Do nothing
		}
	}
}