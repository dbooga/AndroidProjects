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

import com.daviancorp.android.data.Weapon;
import com.daviancorp.android.database.WeaponCursor;
import com.daviancorp.android.loader.WeaponListCursorLoader;

public class WeaponListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TYPE = "WEAPON_TYPE";

	public static WeaponListFragment newInstance(String type) {
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, type);
		WeaponListFragment f = new WeaponListFragment();
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
		if (args != null) {
			mType = args.getString(ARG_TYPE);
		}

		return new WeaponListCursorLoader(getActivity(), mType);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		WeaponListCursorAdapter adapter = new WeaponListCursorAdapter(
				getActivity(), (WeaponCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class WeaponListCursorAdapter extends CursorAdapter {

		private WeaponCursor mWeaponCursor;

		public WeaponListCursorAdapter(Context context, WeaponCursor cursor) {
			super(context, cursor, 0);
			mWeaponCursor = cursor;
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
			// Get the monster for the current row
			Weapon weapon = mWeaponCursor.getWeapon();

			// Set up the text view
			TextView weaponNameTextView = (TextView) view;
			String cellText = weapon.getName();
			weaponNameTextView.setText(cellText);
		}
	}

}
