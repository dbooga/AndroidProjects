package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;

import com.daviancorp.android.database.DataManager;

public class QuestListCursorLoader extends SQLiteCursorLoader {
	
	public QuestListCursorLoader(Context context) {
		super(context);
	}
	
	@Override
	protected Cursor loadCursor() {
		// Query the list of all quests
		return DataManager.get(getContext()).queryQuests();
	}
}

