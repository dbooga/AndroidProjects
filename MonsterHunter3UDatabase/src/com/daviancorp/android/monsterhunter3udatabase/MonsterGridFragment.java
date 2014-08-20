package com.daviancorp.android.monsterhunter3udatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.daviancorp.android.data.Monster;
import com.daviancorp.android.database.MonsterCursor;
import com.daviancorp.android.loader.MonsterListCursorLoader;

public class MonsterGridFragment extends Fragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TAB = "MONSTER_TAB";

	private GridView mGridView;
	private MonsterGridCursorAdapter mAdapter;

	public static MonsterGridFragment newInstance(String tab) {
		Bundle args = new Bundle();
		args.putString(ARG_TAB, tab);
		MonsterGridFragment f = new MonsterGridFragment();
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
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.fragment_monster_grid, parent, false);

		mGridView = (GridView) v.findViewById(R.id.grid_monsters);

		mGridView.setAdapter(mAdapter);

		return v;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		String mTab = null;
		if (args != null) {
			mTab = args.getString(ARG_TAB);
		}

		return new MonsterListCursorLoader(getActivity(), mTab);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		mAdapter = new MonsterGridCursorAdapter(getActivity(),
				(MonsterCursor) cursor);
		if (mGridView != null){
			mGridView.setAdapter(mAdapter);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		mGridView.setAdapter(null);
	}

	private static class MonsterGridCursorAdapter extends CursorAdapter {

		private MonsterCursor mMonsterCursor;

		public MonsterGridCursorAdapter(Context context, MonsterCursor cursor) {
			super(context, cursor, 0);
			mMonsterCursor = cursor;
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
			// Get the monster for the current row
			Monster monster = mMonsterCursor.getMonster();

			// Set up the text view
			TextView monsterNameTextView = (TextView) view;
			String cellText = monster.getName();
			monsterNameTextView.setText(cellText);
		}
	}

}
