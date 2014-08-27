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

import com.daviancorp.android.data.database.QuestCursor;
import com.daviancorp.android.data.object.Quest;
import com.daviancorp.android.loader.QuestListCursorLoader;

public class QuestListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_HUB = "QUEST_HUB";
	private static final String ARG_STARS = "QUEST_STARS";

	public static QuestListFragment newInstance(String hub, String stars) {
		Bundle args = new Bundle();
		args.putString(ARG_HUB, hub);
		args.putString(ARG_STARS, stars);
		QuestListFragment f = new QuestListFragment();
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
		String mHub = null;
		String mStars = null;
		if (args != null) {
			mHub = args.getString(ARG_HUB);
			mStars = args.getString(ARG_STARS);
		}

		return new QuestListCursorLoader(getActivity(), mHub, mStars);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		QuestListCursorAdapter adapter = new QuestListCursorAdapter(
				getActivity(), (QuestCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class QuestListCursorAdapter extends CursorAdapter {

		private QuestCursor mQuestCursor;

		public QuestListCursorAdapter(Context context, QuestCursor cursor) {
			super(context, cursor, 0);
			mQuestCursor = cursor;
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
			// Get the quest for the current row
			Quest quest = mQuestCursor.getQuest();

			// Set up the text view
			TextView questNameTextView = (TextView) view;
			String cellText = quest.getName();
			questNameTextView.setText(cellText);
		}
	}

}
