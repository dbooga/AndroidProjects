package com.daviancorp.csgospray;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class DrawerFragment extends Fragment {
	public static final String EXTRA_GUN = "com.daviancorp.csgospray.gun";

	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	CustomDrawerAdapter adapter;
	List<Gun> dataList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dataList = GunData.getInstance().getData();

		adapter = new CustomDrawerAdapter(getActivity(), R.layout.list_item,
				dataList);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_drawer, parent, false);

		mDrawerList = (ListView) v.findViewById(R.id.list_drawer);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);

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
			mDrawerLayout.closeDrawers();
		}
	}

	public DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	public ListView getDrawerList() {
		return mDrawerList;
	}

}
