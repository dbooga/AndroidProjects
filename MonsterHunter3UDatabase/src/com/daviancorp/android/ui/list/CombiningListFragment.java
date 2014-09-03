package com.daviancorp.android.ui.list;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daviancorp.android.data.database.CombiningCursor;
import com.daviancorp.android.data.object.Combining;
import com.daviancorp.android.loader.CombiningListCursorLoader;
import com.daviancorp.android.monsterhunter3udatabase.R;
import com.daviancorp.android.ui.detail.ItemDetailActivity;

public class CombiningListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Initialize the loader to load the list of runs
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_list_combining, null);
		
		return v;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// You only ever load the runs, so assume this is the case
		return new CombiningListCursorLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// Create an adapter to point at this cursor
		CombiningListCursorAdapter adapter = new CombiningListCursorAdapter(
				getActivity(), (CombiningCursor) cursor);
		setListAdapter(adapter);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Stop using the cursor (via the adapter)
		setListAdapter(null);
	}

	private static class CombiningListCursorAdapter extends CursorAdapter {

		private CombiningCursor mCombiningCursor;

		public CombiningListCursorAdapter(Context context,
				CombiningCursor cursor) {
			super(context, cursor, 0);
			mCombiningCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// Use a layout inflater to get a row view
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			return inflater
					.inflate(R.layout.fragment_combining, parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {

			// Get the combination for the current row
			Combining item = mCombiningCursor.getCombining();
			View v = view;

			String item1 = item.getItem1().getName();
			String item2 = item.getItem2().getName();
			String item3 = item.getCreatedItem().getName();

			TextView itemtv1 = (TextView) v.findViewById(R.id.item1);
			TextView itemtv2 = (TextView) v.findViewById(R.id.item2);
			TextView itemtv3 = (TextView) v.findViewById(R.id.item3);

			itemtv1.setText(item1);
			itemtv2.setText(item2);
			itemtv3.setText(item3);
			
//			int[] attrs = new int[] { android.R.attr.selectableItemBackground };
//			
//			TypedArray a = context.getTheme().obtainStyledAttributes(attrs);
//			
//			Drawable d = a.getDrawable(0);

//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ){
//				itemtv1.setBackground(d);
//				itemtv2.setBackground(d);
//				itemtv3.setBackground(d);
//			}
//			else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ){
//				itemtv1.setBackgroundDrawable(d);
//				itemtv2.setBackgroundDrawable(d);
//				itemtv3.setBackgroundDrawable(d);
//			}
//			else{
				itemtv1.setBackgroundResource(android.R.drawable.list_selector_background);
				itemtv2.setBackgroundResource(android.R.drawable.list_selector_background);
				itemtv3.setBackgroundResource(android.R.drawable.list_selector_background);
//			}
//			a.recycle();
			
			itemtv1.setOnClickListener(new TextClickListener(context, item.getItem1().getId()));
			itemtv2.setOnClickListener(new TextClickListener(context, item.getItem2().getId()));
			itemtv3.setOnClickListener(new TextClickListener(context, item.getCreatedItem().getId()));
		}

		private static class TextClickListener implements OnClickListener {
			private Context c;
			private Long id;

			public TextClickListener(Context context, Long id) {
				super();
				this.id = id;
				this.c = context;
			}

			@Override
			public void onClick(View v) {
				Intent i = new Intent(c, ItemDetailActivity.class);
				i.putExtra(ItemDetailActivity.EXTRA_ITEM_ID, id);
				c.startActivity(i);
			}
		}

		@Override
		public boolean isEnabled(int position) {
			return false;
		}

	}

}
