package com.daviancorp.android.monsterhunter3udatabase;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daviancorp.android.data.Armor;
import com.daviancorp.android.database.ArmorCursor;
import com.daviancorp.android.loader.ArmorListCursorLoader;

public class ArmorListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TYPE = "ARMOR_TYPE";
	private static final String ARG_SLOT = "ARMOR_SLOT";

	public static ArmorListFragment newInstance(String type, String slot) {
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, type);
		args.putString(ARG_SLOT, slot);
		ArmorListFragment f = new ArmorListFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, getArguments(), this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		String mType = null;
		String mSlot = null;
		if (args != null) {
			mType = args.getString(ARG_TYPE);
			mSlot = args.getString(ARG_SLOT);
		}

		return new ArmorListCursorLoader(getActivity(), mType, mSlot);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		ArmorListCursorAdapter adapter = new ArmorListCursorAdapter(
				getActivity(), (ArmorCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class ArmorListCursorAdapter extends CursorAdapter {

		private ArmorCursor mArmorCursor;

		public ArmorListCursorAdapter(Context context, ArmorCursor cursor) {
			super(context, cursor, 0);
			mArmorCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the armor for the current row
			Armor armor = mArmorCursor.getArmor();

			// Set up the text view
			TextView armorNameTextView = (TextView) view;
			String cellText = armor.getName();
			armorNameTextView.setText(cellText);
		}
	}

}
