package com.daviancorp.android.monsterhunter3udatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

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

	static final String[] numbers = new String[] { "Monsters", "Weapons",
			"Armors", "Quests", "Items", "Combining", "Decorations", "Skills",
			"Locations", "Hunting Fleet" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, parent, false);
		
		mLogo = (ImageView) v.findViewById(R.id.logo);
		mLogo.setImageResource(R.drawable.mh3);

		gridView = (GridView) v.findViewById(R.id.grid_home);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, numbers);

		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
					
				switch(position) {
					case MONSTERS:
						Intent monster_intent = new Intent(getActivity(), MonsterListActivity.class);
						startActivity(monster_intent);
						break;
					case WEAPONS:
						break;
					case ARMORS:
						break;
					case QUESTS:
						break;
					case ITEMS:
						Intent item_intent = new Intent(getActivity(), ItemListActivity.class);
						startActivity(item_intent);
						break;
					case COMBINING:
						Intent combining_intent = new Intent(getActivity(), CombiningListActivity.class);
						startActivity(combining_intent);
						break;
					case DECORATIONS:
						break;
					case SKILLS:
						break;
					case LOCATIONS:
						Intent location_intent = new Intent(getActivity(), LocationListActivity.class);
						startActivity(location_intent);
						break;
					case HUNTINGFLEET:
						break;
				}
			}	});
		
		return v;
	}

}