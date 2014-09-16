package com.daviancorp.android.ui.list;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.daviancorp.android.data.database.ArmorCursor;
import com.daviancorp.android.data.object.Armor;
import com.daviancorp.android.loader.ArmorListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.dialog.WishlistDataAddDialogFragment;
import com.daviancorp.android.ui.dialog.WishlistDataAddMultiDialogFragment;

public class ArmorListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TYPE = "ARMOR_TYPE";
	private static final String ARG_SLOT = "ARMOR_SLOT";

	private static final String DIALOG_WISHLIST_DATA_ADD_MULTI = "wishlist_data_add_multi";
	private static final int REQUEST_ADD_MULTI = 0;
	
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		setContextMenu(v);
		return v;
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
	
	/*
	 *  Context menu
	 */

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.context_wishlist_data_add, menu);
	}
	
	protected void setContextMenu(View v) {
		ListView mListView = (ListView) v.findViewById(android.R.id.list);
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			// Use floating context menus on Froyo and Gingerbread
			registerForContextMenu(mListView);
		} else {
			// Use contextual action bar on Honeycomb and higher
			mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			mListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
				
				public void onItemCheckedStateChanged(ActionMode mode, int position,
						long id, boolean checked) {
					// Required, but not used in this implementation
				}
				
				// ActionMode.Callback methods
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					MenuInflater inflater = mode.getMenuInflater();
					inflater.inflate(R.menu.context_wishlist_data_add, menu);
					return true;
				}

			    @Override
			    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			        return false;
			     // Required, but not used in this implementation
			    }

			    @Override
			    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {					
			    	switch (item.getItemId()) {
			    		case R.id.menu_item_wishlist_add:
			    			ArmorListCursorAdapter adapter = (ArmorListCursorAdapter) getListAdapter();
			    			ArrayList<Long> idArray = new ArrayList<Long>();
			    			
			    			for (int i = 0; i < adapter.getCount(); i++) {
			    				if (getListView().isItemChecked(i)) {
			    					idArray.add(((ArmorCursor) adapter.getItem(i)).getArmor().getId());
			    				}
			    			}
			    			
			    			long[] ids = new long[idArray.size()];
			    			for (int j = 0; j < idArray.size(); j++) {
			    				ids[j] = idArray.get(j);
			    			}
			    			
			    			FragmentManager fm = getActivity().getSupportFragmentManager();
			    			
							WishlistDataAddMultiDialogFragment dialogAdd = 
									WishlistDataAddMultiDialogFragment.newInstance(ids);
							dialogAdd.setTargetFragment(ArmorListFragment.this, REQUEST_ADD_MULTI);
							dialogAdd.show(fm, DIALOG_WISHLIST_DATA_ADD_MULTI);
							
			    			mode.finish();
			    			adapter.notifyDataSetChanged();
			    			return true;
			    		default:
			    			return false;
			    	}
			    }

			    @Override
			    public void onDestroyActionMode(ActionMode mode) {		
			    	// Required, but not used in this implementation
			    }
			});
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		
		ArmorListCursorAdapter adapter = (ArmorListCursorAdapter) getListAdapter();
		Armor armor = ((ArmorCursor) adapter.getItem(position)).getArmor();

		long id = armor.getId();
		
		FragmentManager fm = getActivity().getSupportFragmentManager();
		
		switch (item.getItemId()) {
			case R.id.menu_item_wishlist_add:
				WishlistDataAddDialogFragment dialogAdd = WishlistDataAddDialogFragment.newInstance(id);
				dialogAdd.setTargetFragment(ArmorListFragment.this, REQUEST_ADD_MULTI);
				dialogAdd.show(fm, DIALOG_WISHLIST_DATA_ADD_MULTI);
				return true;
		}
		
		return super.onContextItemSelected(item);
	}
}
