package com.daviancorp.android.ui.list;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviancorp.android.data.database.WeaponCursor;
import com.daviancorp.android.data.object.Weapon;
import com.daviancorp.android.loader.WeaponListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;

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
			return inflater.inflate(R.layout.fragment_weapon_listitem,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the monster for the current row
			Weapon weapon = mWeaponCursor.getWeapon();

			// Set up the text view
			TextView nametv = (TextView) view.findViewById(R.id.name);
			TextView attacktv = (TextView) view.findViewById(R.id.attack);
			TextView elementtv = (TextView) view.findViewById(R.id.element);
			TextView slottv = (TextView) view.findViewById(R.id.slot);
			TextView affinitytv = (TextView) view.findViewById(R.id.affinity);
			TextView defensetv = (TextView) view.findViewById(R.id.defense);
			
			String name = weapon.getName();
			String attack = "" + weapon.getAttack();
			
			String element = "";
			if(weapon.getElementalAttack() != null) {
				element = weapon.getElementalAttack();
			}
			else if(weapon.getAwakenedElementalAttack() != null) {
				element = "(" + weapon.getAwakenedElementalAttack() + ")";
			}
			
			String slot = "";
			switch (weapon.getNumSlots()) {
				case 0:
					slot = "---";
					break;
				case 1:
					slot = "O--";
					break;
				case 2:
					slot = "OO-";
					break;
				case 3:
					slot = "OOO";
					break;
				default:
					slot = "error!!";
					break;
			}
			
			String affinity = "";
			if (weapon.getAffinity() != 0) {
				affinity = "" + weapon.getAffinity() + "%";
			}

			String defense = "";
			if (weapon.getDefense() != 0) {
				defense = "" + weapon.getDefense();
			}
			
			nametv.setText(name);
			attacktv.setText(attack);
			elementtv.setText(element);
			slottv.setText(slot);
			affinitytv.setText(affinity);
			defensetv.setText(defense);
			
			String type = weapon.getWtype();
			if (type.equals("Hunting Horn")) {
				TextView specialtv = (TextView) view.findViewById(R.id.special);
				String special = weapon.getHornNotes();
				specialtv.setText(special);
			}
			if (type.equals("Gunlance")) {
				TextView specialtv = (TextView) view.findViewById(R.id.special);
				String special = weapon.getShellingType();
				specialtv.setText(special);
			}
			if (type.equals("Switch Axe")) {
				TextView specialtv = (TextView) view.findViewById(R.id.special);
				String special = weapon.getPhial();
				specialtv.setText(special);
			}
			if (!type.equals("Light Bowgun") && !type.equals("Heavy Bowgun") && !type.equals("Bow")) {
				ImageView sharpnesstv = (ImageView) view.findViewById(R.id.sharpness);
				
				String cellImage1 = "icons_sharpness/" + weapon.getSharpnessFile();
				Drawable s = null;

				try {
					s = Drawable.createFromStream(context.getAssets()
							.open(cellImage1), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				sharpnesstv.setImageDrawable(s);
			}
		}
	}

}
