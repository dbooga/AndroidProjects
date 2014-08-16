package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.daviancorp.android.database.DataManager;

public class MonsterListLargeCursorLoader extends SQLiteCursorLoader {
	
	public MonsterListLargeCursorLoader(Context context) {
		super(context);
	}

	@Override
	protected Cursor loadCursor() {
		// Query the list of large monsters
		return DataManager.get(getContext()).queryLargeMonsters();
	}
}
