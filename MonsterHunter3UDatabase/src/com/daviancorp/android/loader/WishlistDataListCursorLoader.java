package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.daviancorp.android.data.database.DataManager;

public class WishlistDataListCursorLoader extends SQLiteCursorLoader {

	private long id;
	private String type;		// "wishlist", "component" 
	
	public WishlistDataListCursorLoader(Context context, long id, String type) {
		super(context);
		this.id = id;
		this.type = type;
	}

	@Override
	protected Cursor loadCursor() {
		if (type.equals("wishlist")) {
			return DataManager.get(getContext()).queryWishlistData(id);
		}
		else if (type.equals("component")) {
			return DataManager.get(getContext()).queryWishlistDataComponent(id);
		}
		else {
			Log.d("helpme", "WishlistDataListCursorLoader: bad input!");
			return null;
		}
	}
}
