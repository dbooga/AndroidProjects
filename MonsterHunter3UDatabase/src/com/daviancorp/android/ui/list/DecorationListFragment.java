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

import com.daviancorp.android.database.DecorationCursor;
import com.daviancorp.android.loader.DecorationListCursorLoader;
import com.daviancorp.android.object.Decoration;

public class DecorationListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		return new DecorationListCursorLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		DecorationListCursorAdapter adapter = new DecorationListCursorAdapter(
				getActivity(), (DecorationCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class DecorationListCursorAdapter extends CursorAdapter {

		private DecorationCursor mDecorationCursor;

		public DecorationListCursorAdapter(Context context,
				DecorationCursor cursor) {
			super(context, cursor, 0);
			mDecorationCursor = cursor;
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
			// Get the decoration for the current row
			Decoration decoration = mDecorationCursor.getDecoration();

			// Set up the text view
			TextView decorationNameTextView = (TextView) view;
			String cellText = decoration.getName();
			decorationNameTextView.setText(cellText);
		}
	}

}
