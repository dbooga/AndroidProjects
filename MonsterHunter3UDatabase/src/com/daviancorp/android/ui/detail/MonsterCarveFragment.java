package com.daviancorp.android.ui.detail;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daviancorp.android.data.database.CarveCursor;
import com.daviancorp.android.data.object.Carve;
import com.daviancorp.android.loader.CarveListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;

public class MonsterCarveFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_MONSTER_ID = "MONSTER_ID";
	private static final String ARG_RANK = "RANK";

	public static MonsterCarveFragment newInstance(long monsterId, String rank) {
		Bundle args = new Bundle();
		args.putLong(ARG_MONSTER_ID, monsterId);
		args.putString(ARG_RANK, rank);
		MonsterCarveFragment f = new MonsterCarveFragment();
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
		View v = inflater.inflate(R.layout.fragment_monster_carve_list, null);
		return v;
	}

	@SuppressLint("NewApi")
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		long monsterId = args.getLong(ARG_MONSTER_ID, -1);
		String rank = args.getString(ARG_RANK, null);

		return new CarveListCursorLoader(getActivity(), "monster", monsterId,
				rank);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor

		MonsterCarveListCursorAdapter adapter = new MonsterCarveListCursorAdapter(
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
		Intent i = new Intent(getActivity(), ItemDetailActivity.class);
		i.putExtra(ItemDetailActivity.EXTRA_ITEM_ID, (long) v.getTag());
		startActivity(i);
	}

	private static class MonsterCarveListCursorAdapter extends CursorAdapter {

		private CarveCursor mCarveCursor;

		public MonsterCarveListCursorAdapter(Context context, CarveCursor cursor) {
			super(context, cursor, 0);
			mCarveCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(R.layout.fragment_monster_carve_listitem,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the item for the current row
			Carve carve = mCarveCursor.getCarve();

			// Set up the text view
			LinearLayout itemLayout = (LinearLayout) view
					.findViewById(R.id.listitem);
			ImageView itemImageView = (ImageView) view
					.findViewById(R.id.item_image);

			TextView itemTextView = (TextView) view.findViewById(R.id.item);
			TextView carveTextView = (TextView) view.findViewById(R.id.carve);
			TextView amountTextView = (TextView) view.findViewById(R.id.amount);
			TextView percentageTextView = (TextView) view
					.findViewById(R.id.percentage);

			String cellItemText = carve.getItem().getName();
			String cellCarveText = carve.getLocation();
			int cellAmountText = carve.getNumCarves();
			int cellPercentageText = carve.getPercentage();

			itemTextView.setText(cellItemText);
			carveTextView.setText(cellCarveText);
			amountTextView.setText("" + cellAmountText);

			String percent = "" + cellPercentageText + "%";
			percentageTextView.setText(percent);

			Drawable i = null;
			String cellImage = "icons_items/" + carve.getItem().getFileLocation();
			try {
				i = Drawable.createFromStream(
						context.getAssets().open(cellImage), null);
			} catch (IOException e) {
				e.printStackTrace();
			}

			itemImageView.setImageDrawable(i);

			itemLayout.setTag(carve.getItem().getId());
		}
	}

}
