package com.daviancorp.android.monsterhunter3udatabase;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DrawerFragment extends Fragment {
	public static final String EXTRA_GUN = "com.daviancorp.csgospray.gun";

	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	CustomDrawerAdapter adapter;
	List<Gun> dataList;

	GridView gridView;
	private ImageView mLogo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_drawer, parent, false);

		String[] values = new String[] { "Monsters", "Weapons", "Armors",
				"Quests", "Items", "Combining", "Decorations", "Skills",
				"Locations", "Hunting Fleet" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.list_item, values);
		ArrayAdapter<String> gridadapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, values);

		mLogo = (ImageView) v.findViewById(R.id.logo);
		mLogo.setImageResource(R.drawable.mh3);
		gridView = (GridView) v.findViewById(R.id.grid_home);

		gridView.setAdapter(gridadapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Toast.makeText(getActivity(), "Position = " + position,
						Toast.LENGTH_LONG).show();

			}
		});

		mLogo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "clicked", Toast.LENGTH_LONG)
						.show();
			}
		});

		mDrawerList = (ListView) v.findViewById(R.id.list_drawer);
		mDrawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		return v;
	}

	private void sendResult(int resultCode, int position) {
		if (getTargetFragment() == null)
			return;

		Intent i = new Intent();
		i.putExtra(EXTRA_GUN, position);

		getTargetFragment().onActivityResult(getTargetRequestCode(),
				resultCode, i);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			sendResult(Activity.RESULT_OK, position);
			Toast.makeText(getActivity(), "Position = " + position,
					Toast.LENGTH_LONG).show();
			mDrawerLayout.closeDrawers();
			// mDrawerLayout.setVisibility(View.INVISIBLE);
			// mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		}
	}

	public DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	public ListView getDrawerList() {
		return mDrawerList;
	}

}
