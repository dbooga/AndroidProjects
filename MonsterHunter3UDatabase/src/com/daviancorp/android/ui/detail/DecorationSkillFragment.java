package com.daviancorp.android.ui.detail;

import android.annotation.SuppressLint;
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

import com.daviancorp.android.data.database.ItemToSkillTreeCursor;
import com.daviancorp.android.data.object.ItemToSkillTree;
import com.daviancorp.android.loader.ItemToSkillTreeListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;

public class DecorationSkillFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	private static final String ARG_DECORATION_ID = "DECORATION_ID";

	public static DecorationSkillFragment newInstance(long decorationId) {
		Bundle args = new Bundle();
		args.putLong(ARG_DECORATION_ID, decorationId);
		DecorationSkillFragment f = new DecorationSkillFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, getArguments(), this);
	}

	@SuppressLint("NewApi")
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		long decorationId = args.getLong(ARG_DECORATION_ID, -1);

		return new ItemToSkillTreeListCursorLoader(getActivity(), "item", decorationId, "Decoration");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor

		ItemToSkillTreeListCursorAdapter adapter = new ItemToSkillTreeListCursorAdapter(
				getActivity(), (ItemToSkillTreeCursor) cursor);
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
		Intent i = new Intent(getActivity(), SkillTreeDetailActivity.class);
		i.putExtra(SkillTreeDetailActivity.EXTRA_SKILLTREE_ID, (long) v.getTag());
		startActivity(i);
	}

	private static class ItemToSkillTreeListCursorAdapter extends CursorAdapter {

		private ItemToSkillTreeCursor mItemToSkillTreeCursor;

		public ItemToSkillTreeListCursorAdapter(Context context,
				ItemToSkillTreeCursor cursor) {
			super(context, cursor, 0);
			mItemToSkillTreeCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			return inflater.inflate(R.layout.fragment_decoration_skill,
					parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// Get the item for the current row
			ItemToSkillTree itemToSkillTree = mItemToSkillTreeCursor
					.getItemToSkillTree();

			// Set up the text view
			LinearLayout itemLayout = (LinearLayout) view
					.findViewById(R.id.listitem);
			TextView skillTextView = (TextView) view
					.findViewById(R.id.skill);
			TextView pointTextView = (TextView) view
					.findViewById(R.id.point);

			String cellSkill = itemToSkillTree.getSkillTree().getName();
			String cellPoints = "" + itemToSkillTree.getPoints();
			
			skillTextView.setText(cellSkill);
			pointTextView.setText(cellPoints);

			itemLayout.setTag(itemToSkillTree.getSkillTree().getId());
		}
	}

}
