package com.daviancorp.csgospray;

import java.util.ArrayList;
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
	private static final String TAG = "DrawerFragment";

	public static final String EXTRA_GUN = "com.daviancorp.csgospray.gun";

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	CustomDrawerAdapter adapter;
	List<DrawerItem> dataList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dataList = new ArrayList<DrawerItem>();
		dataList.add(new DrawerItem("p90", R.drawable.p90));
		dataList.add(new DrawerItem("ppbizon", R.drawable.ppbizon));
		adapter = new CustomDrawerAdapter(getActivity(), R.layout.list_item,
				dataList);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_drawer, parent, false);

		mDrawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) v.findViewById(R.id.list_drawer);
		mDrawerList.setAdapter(adapter);
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		return v;
	}

	private void sendResult(int resultCode, int position) {
		if (getTargetFragment() == null) return;
		
		Intent i = new Intent();
	//	i.putExtra(EXTRA_GUN, value)
		
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			sendResult(Activity.RESULT_OK, position);

		}
	}
}
