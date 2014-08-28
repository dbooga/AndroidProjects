package com.daviancorp.android.ui.detail;

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

import com.daviancorp.android.data.database.GatheringCursor;
import com.daviancorp.android.data.object.Gathering;
import com.daviancorp.android.loader.GatheringListCursorLoader;

public class TestFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_ITEM_ID = "ITEM_ID";
	
	public static TestFragment newInstance(long itemId) {
		Bundle args = new Bundle();
		args.putLong(ARG_ITEM_ID, itemId);
		TestFragment f = new TestFragment();
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
		long itemId = args.getLong(ARG_ITEM_ID, -1);
		
		return new GatheringListCursorLoader(getActivity(), "location", itemId, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		TestListCursorAdapter adapter = new TestListCursorAdapter(
				getActivity(), (GatheringCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class TestListCursorAdapter extends CursorAdapter {

		private GatheringCursor mCarveCursor;

		public TestListCursorAdapter(Context context, GatheringCursor cursor) {
			super(context, cursor, 0);
			mCarveCursor = cursor;
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
			// Get the item for the current row
			Gathering carve = mCarveCursor.getGathering();

			// Set up the text view
			TextView itemNameTextView = (TextView) view;
			String cellText = carve.getItem().getName()  + "\t\t\t" + carve.getSite();
			itemNameTextView.setText(cellText);
		}
	}

}
