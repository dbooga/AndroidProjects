package com.daviancorp.android.ui.list;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.Typeface;
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
import android.widget.ListView;
import android.widget.TextView;

import com.daviancorp.android.data.database.WeaponCursor;
import com.daviancorp.android.data.object.Weapon;
import com.daviancorp.android.loader.WeaponListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.detail.WeaponDetailActivity;

public class WeaponBowgunListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TYPE = "WEAPON_TYPE";

	public static WeaponBowgunListFragment newInstance(String type) {
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, type);
		WeaponBowgunListFragment f = new WeaponBowgunListFragment();
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
		View v = inflater.inflate(R.layout.fragment_weapon_bowgun_list, null);
		return v;
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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// The id argument will be the Monster ID; CursorAdapter gives us this
		// for free
		Intent i = new Intent(getActivity(), WeaponDetailActivity.class);
		i.putExtra(WeaponDetailActivity.EXTRA_WEAPON_ID, id);
		startActivity(i);
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
			return inflater.inflate(R.layout.fragment_weapon_bowgun_listitem,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the monster for the current row
			Weapon weapon = mWeaponCursor.getWeapon();
			
			// Set up the text view
			TextView nametv = (TextView) view.findViewById(R.id.name);
			TextView attacktv = (TextView) view.findViewById(R.id.attack);
			TextView slottv = (TextView) view.findViewById(R.id.slot);
			TextView affinitytv = (TextView) view.findViewById(R.id.affinity);
			TextView defensetv = (TextView) view.findViewById(R.id.defense);

			// Need to reset drawables

			String name = weapon.getName();
			String attack = "" + weapon.getAttack();

			// Set the slot to view
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
			nametv.setTypeface(null, Typeface.BOLD);
			attacktv.setText(attack);
			slottv.setText(slot);
			affinitytv.setText(affinity);
			defensetv.setText(defense);

			// Bowgun stuff
			TextView reloadtv = (TextView) view.findViewById(R.id.reload);
			TextView recoiltv = (TextView) view.findViewById(R.id.recoil);
			TextView steadytv = (TextView) view.findViewById(R.id.steady);

			String reload = weapon.getReloadSpeed();
			String recoil = weapon.getRecoil();
			String steady = weapon.getDeviation();
			
			if (reload.equals("Average")) {
				reload = "Avg";
			} else if (reload.equals("Above Average")) {
				reload = "Above Avg";
			} else if (reload.equals("Below Average")) {
				reload = "Below Avg";
			}
			
			if (recoil.equals("Average")) {
				recoil = "Avg";
			}
			
			if (steady.startsWith("Left/Right")) {
				String[] tempSteady = steady.split(":");
				steady = "L/R:" + tempSteady[1];
			}

			reloadtv.setText(reload);
			recoiltv.setText(recoil);
			steadytv.setText(steady);

		}
	}

}
