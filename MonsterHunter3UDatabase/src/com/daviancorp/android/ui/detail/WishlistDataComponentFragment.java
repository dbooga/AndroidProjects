package com.daviancorp.android.ui.detail;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daviancorp.android.data.database.WishlistComponentCursor;
import com.daviancorp.android.data.object.WishlistComponent;
import com.daviancorp.android.loader.WishlistComponentListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.dialog.WishlistComponentEditDialogFragment;

public class WishlistDataComponentFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {

	private static final String ARG_ID = "ID";

	private static final String DIALOG_WISHLIST_COMPONENT_EDIT = "wishlist_component_edit";
	private static final int REQUEST_REFRESH = 0;
	private static final int REQUEST_EDIT = 1;

	private ListView mListView;
	private ActionMode mActionMode;
	
	private boolean started;

	public static WishlistDataComponentFragment newInstance(long id) {
		Bundle args = new Bundle();
		args.putLong(ARG_ID, id);
		WishlistDataComponentFragment f = new WishlistDataComponentFragment();
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
		View v = inflater.inflate(R.layout.fragment_list_generic, container, false);

		mListView = (ListView) v.findViewById(android.R.id.list);
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			// Use floating context menus on Froyo and Gingerbread
			registerForContextMenu(mListView);
		} else {
			// Use contextual action bar on Honeycomb and higher
			mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			        @Override
			        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			            if (mActionMode != null) {
			                return false;
			            }
			
			            mActionMode = getActivity().startActionMode(new mActionModeCallback());
			            mActionMode.setTag(position);
			            mListView.setItemChecked(position, true);
			            return true;
			        }
			});
		}
		
		return v;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getActivity().getMenuInflater().inflate(R.menu.context_wishlist_data_component, menu);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_EDIT) {
			if(data.getBooleanExtra(WishlistComponentEditDialogFragment.EXTRA_EDIT, false)) {
				updateUI();
			}
		}
		else if (requestCode == REQUEST_REFRESH) {
			if(data.getBooleanExtra(WishlistDataDetailFragment.EXTRA_REFRESH, false)) {
				updateUI();
			}
		}
	}
	
	private void updateUI() {
		if (started) {
			getLoaderManager().getLoader( 0 ).forceLoad();
			WishlistComponentCursorAdapter adapter = (WishlistComponentCursorAdapter) getListAdapter();
			adapter.notifyDataSetChanged();
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		
		boolean temp = onItemSelected(item, position);
		
		if(temp) {
			return true;
		}
		else {
			return super.onContextItemSelected(item);
		}
	}
	
	private boolean onItemSelected(MenuItem item, int position) {
		WishlistComponentCursorAdapter adapter = (WishlistComponentCursorAdapter) getListAdapter();
		WishlistComponent wishlistComponent = ((WishlistComponentCursor) adapter.getItem(position)).getWishlistComponent();
		long id = wishlistComponent.getId();
		String name = wishlistComponent.getItem().getName();
		
		FragmentManager fm = getActivity().getSupportFragmentManager();
		
		switch (item.getItemId()) {
			case R.id.menu_item_edit_wishlist_data:
				WishlistComponentEditDialogFragment dialogEdit = WishlistComponentEditDialogFragment.newInstance(id, name);
				dialogEdit.setTargetFragment(WishlistDataComponentFragment.this, REQUEST_EDIT);
				dialogEdit.show(fm, DIALOG_WISHLIST_COMPONENT_EDIT);
				return true;
			default:
				return false;
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// The id argument will be the Item ID; CursorAdapter gives us this
		// for free
		
		Intent i = null;
		long mId = (long) v.getTag();
		
		if (mId < 1314) {
			i = new Intent(getActivity(), ItemDetailActivity.class);
			i.putExtra(ItemDetailActivity.EXTRA_ITEM_ID, mId);
		}
		else if (mId < 2955) {
			i = new Intent(getActivity(), ArmorDetailActivity.class);
			i.putExtra(ArmorDetailActivity.EXTRA_ARMOR_ID, mId);
		}
		else {
			i = new Intent(getActivity(), WeaponDetailActivity.class);
			i.putExtra(WeaponDetailActivity.EXTRA_WEAPON_ID, mId);
		}
		startActivity(i);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		long mId = -1;
		if (args != null) {
			mId = args.getLong(ARG_ID);
		}
		return new WishlistComponentListCursorLoader(getActivity(), mId);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		WishlistComponentCursorAdapter adapter = new WishlistComponentCursorAdapter(
				getActivity(), (WishlistComponentCursor) cursor);
		setListAdapter(adapter);
		started = true;
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class WishlistComponentCursorAdapter extends CursorAdapter {

		private WishlistComponentCursor mWishlistComponentCursor;

		public WishlistComponentCursorAdapter(Context context, WishlistComponentCursor cursor) {
			super(context, cursor, 0);
			mWishlistComponentCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(R.layout.fragment_wishlist_data_listitem,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the skill for the current row
			WishlistComponent component = mWishlistComponentCursor.getWishlistComponent();

			// Set up the text view
			LinearLayout root = (LinearLayout) view.findViewById(R.id.listitem);
			ImageView itemImageView = (ImageView) view.findViewById(R.id.item_image);
			TextView itemTextView = (TextView) view.findViewById(R.id.item);
			TextView amtTextView = (TextView) view.findViewById(R.id.amt);
			TextView extraTextView = (TextView) view.findViewById(R.id.extra);
			
			long id = component.getItem().getId();
			int quantity = component.getQuantity();
			int notes = component.getNotes();
			
			String nameText = component.getItem().getName();
			String amtText = "" + quantity;
			String extraText = "" + notes;
			
			itemTextView.setText(nameText);
			amtTextView.setText(amtText);
			extraTextView.setText(extraText);

			if (notes >= quantity) {
				itemTextView.setTextColor(Color.RED);
			}
			
			Drawable i = null;
			String cellImage = "";
			String cellRare = "" + component.getItem().getRarity();
			
			if (id < 1314) {
				cellImage = "icons_items/" + component.getItem().getFileLocation();
			} 
			else if ((id >= 1314) && (id < 1646)) {
				cellImage = "icons_armor/icons_head/head" + cellRare + ".png";
			}
			else if ((id >= 1646) && (id < 1983)) {
				cellImage = "icons_armor/icons_body/body" + cellRare + ".png";
			}
			else if ((id >= 1983) && (id < 2303)) {
				cellImage = "icons_armor/icons_arms/arms" + cellRare + ".png";
			}
			else if ((id >= 2303) && (id < 2623)) {
				cellImage = "icons_armor/icons_waist/waist" + cellRare + ".png";
			}
			else if ((id >= 2623) && (id < 2955)) {
				cellImage = "icons_armor/icons_legs/legs" + cellRare + ".png";
			}
			else if ((id >= 2955) && (id < 3091)) {
				cellImage = "icons_weapons/icons_great_sword/great_sword" + cellRare + ".png";
			}
			else if ((id >= 3091) && (id < 3191)) {
				cellImage = "icons_weapons/icons_hunting_horn/hunting_horn" + cellRare + ".png";
			}
			else if ((id >= 3191) && (id < 3305)) {
				cellImage = "icons_weapons/icons_long_sword/long_sword" + cellRare + ".png";
			}
			else if ((id >= 3305) && (id < 3445)) {
				cellImage = "icons_weapons/icons_sword_and_shield/sword_and_shield" + cellRare + ".png";
			}
			else if ((id >= 3445) && (id < 3570)) {
				cellImage = "icons_weapons/icons_dual_blades/dual_blades" + cellRare + ".png";
			}
			else if ((id >= 3570) && (id < 3704)) {
				cellImage = "icons_weapons/icons_hammer/hammer" + cellRare + ".png";
			}
			else if ((id >= 3704) && (id < 3849)) {
				cellImage = "icons_weapons/icons_lance/lance" + cellRare + ".png";
			}
			else if ((id >= 3849) && (id < 3961)) {
				cellImage = "icons_weapons/icons_gunlance/gunlance" + cellRare + ".png";
			}
			else if ((id >= 3961) && (id < 4074)) {
				cellImage = "icons_weapons/icons_switch_axe/switch_axe" + cellRare + ".png";
			}
			else if ((id >= 4074) && (id < 4170)) {
				cellImage = "icons_weapons/icons_light_bowgun/light_bowgun" + cellRare + ".png";
			}
			else if ((id >= 4170) && (id < 4261)) {
				cellImage = "icons_weapons/icons_heavy_bowgun/heavy_bowgun" + cellRare + ".png";
			}
			else if (id >= 4261) {
				cellImage = "icons_weapons/icons_bow/bow" + cellRare + ".png";
			}
			
			try {
				i = Drawable.createFromStream(
						context.getAssets().open(cellImage), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			itemImageView.setImageDrawable(i);

			root.setTag(id);
		}
	}

	private class mActionModeCallback implements ActionMode.Callback {
	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(R.menu.context_wishlist_data_component, menu);
	        return true;
	    }

	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        return false;
	    }

	    @Override
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	    	int position = Integer.parseInt(mode.getTag().toString());
	    	mode.finish();
	    	return onItemSelected(item, position);
	    }

	    @Override
	    public void onDestroyActionMode(ActionMode mode) {		        
	        for (int i = 0; i < mListView.getCount(); i++) {
	        	mListView.setItemChecked(i, false);
	        }

	        mActionMode = null;
	    }
	}
}
