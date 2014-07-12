package com.daviancorp.csgospray;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.daviancorp.csgospray.R;

public class DrawerFragment extends Fragment {
	private DrawerLayout mDrawerLayout;
	private static final String TAG = "DrawerFragment";
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

		return v;
	}

}
