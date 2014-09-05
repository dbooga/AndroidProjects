package com.daviancorp.android.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daviancorp.android.data.database.CarveCursor;
import com.daviancorp.android.data.object.Carve;
import com.daviancorp.android.loader.CarveListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;

public class ItemCarveFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_ITEM_ID = "ITEM_ID";
	
	public static ItemCarveFragment newInstance(long itemId) {
		Bundle args = new Bundle();
		args.putLong(ARG_ITEM_ID, itemId);
		ItemCarveFragment f = new ItemCarveFragment();
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
		View v = inflater.inflate(R.layout.fragment_item_carve_list, null);
		return v;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		long itemId = args.getLong(ARG_ITEM_ID, -1);
		
		return new CarveListCursorLoader(getActivity(), "item", itemId, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		ItemCarveListCursorAdapter adapter = new ItemCarveListCursorAdapter(
				getActivity(), (CarveCursor) cursor);
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
		Intent i = new Intent(getActivity(), MonsterDetailActivity.class);
		i.putExtra(MonsterDetailActivity.EXTRA_MONSTER_ID, (long) v.getTag());
		startActivity(i);
	}

	private static class ItemCarveListCursorAdapter extends CursorAdapter {

		private CarveCursor mCarveCursor;

		public ItemCarveListCursorAdapter(Context context, CarveCursor cursor) {
			super(context, cursor, 0);
			mCarveCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(R.layout.fragment_item_carve_listitem,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the item for the current row
			Carve carve = mCarveCursor.getCarve();

			// Set up the text view
			LinearLayout itemLayout = (LinearLayout) view.findViewById(R.id.listitem);
			
			TextView rankTextView = (TextView) view.findViewById(R.id.rank);
			TextView monsterTextView = (TextView) view.findViewById(R.id.monster);
			TextView carveTextView = (TextView) view.findViewById(R.id.carve);
			TextView amountTextView = (TextView) view.findViewById(R.id.amount);
			TextView percentageTextView = (TextView) view
					.findViewById(R.id.percentage);

			String cellRankText = carve.getRank();
			String cellMonsterText = carve.getMonster().getName();
			String cellCarveText = carve.getLocation();
			int cellAmountText = carve.getNumCarves();
			int cellPercentageText = carve.getPercentage();

			rankTextView.setText(cellRankText);
			monsterTextView.setText(cellMonsterText);
			carveTextView.setText(cellCarveText);
			amountTextView.setText("" + cellAmountText);

			String percent = "" + cellPercentageText + "%";
			percentageTextView.setText(percent);

			itemLayout.setTag(carve.getMonster().getId());
			
			
			
//			TextView itemNameTextView = (TextView) view;
//			String cellText = carve.getMonster().getName() + "\t\t\t\t" + carve.getLocation();
//			itemNameTextView.setText(cellText);
//			
//			itemNameTextView.setTag(carve.getMonster().getId());
			
			
		}
	}

}
