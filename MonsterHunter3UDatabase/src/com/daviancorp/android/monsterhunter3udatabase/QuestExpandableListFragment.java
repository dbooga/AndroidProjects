package com.daviancorp.android.monsterhunter3udatabase;

import com.daviancorp.android.data.Quest;
import com.daviancorp.android.database.QuestCursor;
import com.daviancorp.android.loader.QuestListCursorLoader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * Pieced together from: Android samples:
 * com.example.android.apis.view.ExpandableList1
 * http://androidword.blogspot.com/2012/01/how-to-use-expandablelistview.html
 * http://stackoverflow.com/questions/6938560/android-fragments-setcontentview-
 * alternative
 * http://stackoverflow.com/questions/6495898/findviewbyid-in-fragment-android
 */
public class QuestExpandableListFragment extends Fragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_HUB = "QUEST_HUB";
	private static final String ARG_STARS = "QUEST_STARS";

	public static QuestExpandableListFragment newInstance(String hub, String stars) {
		Bundle args = new Bundle();
		args.putString(ARG_HUB, hub);
		args.putString(ARG_STARS, stars);
		QuestExpandableListFragment f = new QuestExpandableListFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, getArguments(), this);
		
		View v = inflater.inflate(R.layout.fragment_questlist, null);
		ExpandableListView elv = (ExpandableListView) v
				.findViewById(R.id.questlist);
//		elv.setAdapter(new QuestExpandableListAdapter());
		return v;
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
//		setAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
//		setAdapter(null);
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

	public class QuestExpandableListCursorAdapter extends
			BaseExpandableListAdapter {

		private QuestCursor mQuestCursor;
		
		public QuestExpandableListCursorAdapter(Context context, QuestCursor cursor) {
			super();
			mQuestCursor = cursor;
		}
		
		// private String[] groups = { "People Names", "Dog Names", "Cat Names",
		// "Fish Names" };

		private String[] stars = { "1", "2", "3", "4", "5", "6", "7", "8", "9", };

		private String[][] children = {
				{ "Arnold", "Barry", "Chuck", "David" },
				{ "Ace", "Bandit", "Cha-Cha", "Deuce" },
				{ "Fluffy", "Snuggles" }, { "Goldy", "Bubbles" } };

		@Override
		public int getGroupCount() {
			return stars.length;
		}

		@Override
		public int getChildrenCount(int i) {
			return children[i].length;
		}

		@Override
		public Object getGroup(int i) {
			return stars[i];
		}

		@Override
		public Object getChild(int i, int i1) {
			return children[i][i1];
		}

		@Override
		public long getGroupId(int i) {
			return i;
		}

		@Override
		public long getChildId(int i, int i1) {
			return i1;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getGroupView(int i, boolean b, View view,
				ViewGroup viewGroup) {
			
			Quest quest = mQuestCursor.getQuest();
			
			TextView questNameTextView = (TextView) view;
			String cellText = quest.getStars();
			questNameTextView.setText(cellText);
			
			return questNameTextView;
			
//			TextView textView = new TextView(
//					QuestExpandableListFragment.this.getActivity());
//			textView.setText(getGroup(i).toString());
//			return textView;
		}

		@Override
		public View getChildView(int i, int i1, boolean b, View view,
				ViewGroup viewGroup) {
			
			Quest quest = mQuestCursor.getQuest();
			
			TextView questNameTextView = (TextView) view;
			String cellText = quest.getName();
			questNameTextView.setText(cellText);
			
			return questNameTextView;
			
//			TextView textView = new TextView(
//					QuestExpandableListFragment.this.getActivity());
//			textView.setText(getChild(i, i1).toString());
//			return textView;
		}

		@Override
		public boolean isChildSelectable(int i, int i1) {
			return true;
		}

	}

}