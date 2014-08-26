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

import com.daviancorp.android.database.MonsterCursor;
import com.daviancorp.android.loader.MonsterListCursorLoader;
import com.daviancorp.android.object.Monster;

public class MonsterListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_TAB = "MONSTER_TAB";

	public static MonsterListFragment newInstance(String tab) {
		Bundle args = new Bundle();
		args.putString(ARG_TAB, tab);
		MonsterListFragment f = new MonsterListFragment();
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
		String mTab = null;
		if (args != null) {
			mTab = args.getString(ARG_TAB);
		}

		return new MonsterListCursorLoader(getActivity(), mTab);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		MonsterListCursorAdapter adapter = new MonsterListCursorAdapter(
				getActivity(), (MonsterCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class MonsterListCursorAdapter extends CursorAdapter {

		private MonsterCursor mMonsterCursor;

		public MonsterListCursorAdapter(Context context, MonsterCursor cursor) {
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
