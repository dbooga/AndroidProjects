package com.daviancorp.android.ui.list;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.daviancorp.android.data.database.DataManager;
import com.daviancorp.android.data.database.WishlistCursor;
import com.daviancorp.android.data.object.Wishlist;
import com.daviancorp.android.loader.WishlistListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.detail.WishlistDetailActivity;
import com.daviancorp.android.ui.general.AddWishlistDialogFragment;

public class WishlistListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		return new WishlistListCursorLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		WishlistListCursorAdapter adapter = new WishlistListCursorAdapter(
				getActivity(), (WishlistCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_wishlist_list, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.wishlist_add:
				FragmentManager fm = getActivity()
					.getSupportFragmentManager();
				AddWishlistDialogFragment dialog = new AddWishlistDialogFragment();
				dialog.show(fm, "TEST");
				
				DataManager.get(getActivity()).queryAddWishlist("test4");
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// The id argument will be the Skill ID; CursorAdapter gives us this for free
		Intent i = new Intent(getActivity(), WishlistDetailActivity.class);
		i.putExtra(WishlistDetailActivity.EXTRA_WISHLIST_ID, id);
		startActivity(i);
	}

	private static class WishlistListCursorAdapter extends CursorAdapter {

		private WishlistCursor mWishlistCursor;

		public WishlistListCursorAdapter(Context context,
				WishlistCursor cursor) {
			super(context, cursor, 0);
			mWishlistCursor = cursor;
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
			// Get the skill for the current row
			Wishlist wishlist = mWishlistCursor.getWishlist();

			// Set up the text view
			TextView wishlistNameTextView = (TextView) view;
			String cellText = wishlist.getName();
			wishlistNameTextView.setText(cellText);
			
		}
	}

}
