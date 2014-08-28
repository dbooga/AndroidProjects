package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.daviancorp.android.data.database.DataManager;

public class ItemToSkillTreeListCursorLoader extends SQLiteCursorLoader {
	private String from;	// "item" or "skillTree"
	private long id; 		// Item or Quest id

	public ItemToSkillTreeListCursorLoader(Context context, String from, long id) {
		super(context);
		this.from = from;
		this.id = id;
	}

	@Override
	protected Cursor loadCursor() {
		if (from.equals("item")) {
			return DataManager.get(getContext()).queryItemToSkillTreeItem(id);
		}
		else if(from.equals("skillTree")) {
			return DataManager.get(getContext()).queryItemToSkillTreeSkillTree(id);
		}
		else {
			Log.d("heyo", "ItemToSkillTreeListCursorLoader: bad arg!!! + (" + from + ")");
			return null;
		}
	}
}
