package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.daviancorp.android.database.DataManager;

public class MonsterListCursorLoader extends SQLiteCursorLoader {
	
	public MonsterListCursorLoader(Context context) {
		super(context);
	}
	
	@Override
	protected Cursor loadCursor() {
		// Query the list of all monsters
		return DataManager.get(getContext()).queryMonsters();
	}
}
