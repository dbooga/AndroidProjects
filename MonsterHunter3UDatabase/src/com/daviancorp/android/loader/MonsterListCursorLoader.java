package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.daviancorp.android.database.DataManager;

public class MonsterListCursorLoader extends DataLoader<Cursor> {
	
	public MonsterListCursorLoader(Context context) {
		super(context);
	}
	
	@Override
	public Cursor loadInBackground() {
		// Query the list of monsters
		return DataManager.get(getContext()).queryMonsters();
	}
}