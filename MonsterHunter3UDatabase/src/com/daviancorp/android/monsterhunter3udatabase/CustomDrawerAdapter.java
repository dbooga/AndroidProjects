package com.daviancorp.android.monsterhunter3udatabase;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomDrawerAdapter extends ArrayAdapter<Gun> {

	Context context;
	List<Gun> drawerItemList;
	int layoutResID;

	public CustomDrawerAdapter(Context context, int layoutResourceID,
			List<Gun> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view
					.findViewById(R.id.text_item);

			view.setTag(drawerHolder);

		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();

		}

		Gun dItem = (Gun) this.drawerItemList.get(position);

		drawerHolder.ItemName.setText(dItem.getName());
		drawerHolder.ItemName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,
				dItem.getDrawable());

		return view;
	}

	private static class DrawerItemHolder {
		TextView ItemName;
	}
}