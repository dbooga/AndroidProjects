package com.daviancorp.android.ui.list;

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

import com.daviancorp.android.database.HuntingFleetCursor;
import com.daviancorp.android.loader.HuntingFleetListCursorLoader;
import com.daviancorp.android.object.HuntingFleet;

public class HuntingFleetListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TYPE = "HUNTINGFLEET_TYPE";
	private static final String ARG_LOCATION = "HUNTINGFLEET_LOCATION";

	public static HuntingFleetListFragment newInstance(String type,
			String location) {
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, type);
		args.putString(ARG_LOCATION, location);
		HuntingFleetListFragment f = new HuntingFleetListFragment();
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
		String mLocation = null;
		if (args != null) {
			mType = args.getString(ARG_TYPE);
			mLocation = args.getString(ARG_LOCATION);
		}

		return new HuntingFleetListCursorLoader(getActivity(), mType, mLocation);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		HuntingFleetListCursorAdapter adapter = new HuntingFleetListCursorAdapter(
				getActivity(), (HuntingFleetCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class HuntingFleetListCursorAdapter extends CursorAdapter {

		private HuntingFleetCursor mHuntingFleetCursor;

		public HuntingFleetListCursorAdapter(Context context,
				HuntingFleetCursor cursor) {
			super(context, cursor, 0);
			mHuntingFleetCursor = cursor;
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
			// Get the hunting fleet for the current row
			HuntingFleet huntingfleet = mHuntingFleetCursor.getHuntingFleet();

			// Set up the text view
			TextView HuntingFleetNameTextView = (TextView) view;
			String cellText = huntingfleet.getLocation() + "\t\t" + huntingfleet.getItem().getName();
			HuntingFleetNameTextView.setText(cellText);
		}
	}

}
