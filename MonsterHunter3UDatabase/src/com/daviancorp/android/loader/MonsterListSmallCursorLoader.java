package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.daviancorp.android.database.DataManager;

public class MonsterListSmallCursorLoader extends SQLiteCursorLoader {
	
	public MonsterListSmallCursorLoader(Context context) {
		super(context);
	}

	@Override
	protected Cursor loadCursor() {
		// Query the list of small monsters
		return DataManager.get(getContext()).querySmallMonsters();
	}
}
