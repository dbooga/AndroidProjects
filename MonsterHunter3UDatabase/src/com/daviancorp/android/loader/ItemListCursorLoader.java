package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.daviancorp.android.data.database.DataManager;

public class ItemListCursorLoader extends SQLiteCursorLoader {

	public ItemListCursorLoader(Context context) {
		super(context);
	}

	@Override
	protected Cursor loadCursor() {
		// Query the list of all items
		return DataManager.get(getContext()).queryItems();
	}
}
