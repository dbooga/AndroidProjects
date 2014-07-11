package com.daviancorp.csgospray;

import java.util.HashMap;
import java.util.Map;

import com.example.csgospray.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GunAdapter extends ArrayAdapter<String> {

	private static final String TAG = "GunAdapter";

	private final Context context;
	private final String[] values;

	private Map<String, Integer> guns;

	public GunAdapter(Context context, String[] values) {
		super(context, 0, values);
		this.context = context;
		this.values = values;
		getImageId();
		Log.d(TAG, "hey");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "hey1");
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_item, parent,
				false);
		Log.d(TAG, "hey2");
		TextView textView = (TextView) rowView
				.findViewById(R.id.text_item);
		String s = values[position];
		textView.setText(s);
		Log.d(TAG, "hey3");
		textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, guns.get(s));

		return rowView;
	}

	private void getImageId() {
		guns = new HashMap<String, Integer>();
		guns.put("p90", R.drawable.p90);
		guns.put("ppbizon", R.drawable.ppbizon);
	}
}
