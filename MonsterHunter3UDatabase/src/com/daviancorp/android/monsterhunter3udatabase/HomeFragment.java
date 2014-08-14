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
					
				Toast.makeText(getActivity(), "Position = " + position, Toast.LENGTH_LONG).show();

				switch(position) {
					case 0:
						Intent intent = new Intent(getActivity(), MonsterListActivity.class);
						startActivity(intent);
						break;
				}
			}
		});
		
		return v;
	}

}