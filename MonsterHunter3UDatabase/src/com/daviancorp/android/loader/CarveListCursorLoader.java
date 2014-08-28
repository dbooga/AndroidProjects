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
			return DataManager.get(getContext()).queryCarveItem(id);
		}
		else if(from.equals("monster")) {
			return DataManager.get(getContext()).queryCarveMonsterRank(id, rank);
		}
		else {
			Log.d("heyo", "CarveCursorLoader: bad arg!!! + (" + from + ")");
			return null;
		}
	}
}
