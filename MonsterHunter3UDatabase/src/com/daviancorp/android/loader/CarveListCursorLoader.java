package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.daviancorp.android.data.database.DataManager;

public class CarveListCursorLoader extends SQLiteCursorLoader {
	private String from;	// "item" or "monster"
	private long id; 		// Item or Monster id
	private String rank; 	// (For Monsters only) "LR", "HR", "G", or null

	public CarveListCursorLoader(Context context, String from, long id, String rank) {
		super(context);
		this.from = from;
		this.id = id;
		this.rank = rank;
	}

	@Override
	protected Cursor loadCursor() {
		if (from.equals("item")) {
			Log.d("heyo", "TestCursorLoader: from item");
			return DataManager.get(getContext()).queryCarveFromItem(id);
		}
		else if(from.equals("monster")) {
			Log.d("heyo", "TestCursorLoader: from monster");
			return DataManager.get(getContext()).queryCarveFromMonsterRank(id, rank);
		}
		else {
			Log.d("heyo", "TestCursorLoader: bad arg!!! + (" + from + ")");
			return null;
		}
	}
}
