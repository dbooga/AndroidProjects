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

import com.daviancorp.android.data.Item;
import com.daviancorp.android.database.ItemCursor;
import com.daviancorp.android.loader.ItemListCursorLoader;

public class ItemListFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		return new ItemListCursorLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		ItemListCursorAdapter adapter =
				new ItemListCursorAdapter(getActivity(), (ItemCursor) cursor);
		setListAdapter(adapter);
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}
	
	private static class ItemListCursorAdapter extends CursorAdapter {
		
		private ItemCursor mItemCursor;
		
		public ItemListCursorAdapter(Context context, ItemCursor cursor) {
			super(context, cursor, 0);
			mItemCursor = cursor;
		}
		
		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater
					.inflate(android.R.layout.simple_list_item_1, parent, false);
		}
		
		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the monster for the current row
			Item item = mItemCursor.getItem();
			
			// Set up the text view
			TextView itemNameTextView = (TextView) view;
			String cellText = item.getName();
			itemNameTextView.setText(cellText);
		}
	}
	
	
}
