package com.daviancorp.android.loader;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.daviancorp.android.database.DataManager;

/*
 *  Refer to MonsterListPagerAdapter and MonsterListFragment on 
 *  how to call this loader
 */
public class WeaponListCursorLoader extends SQLiteCursorLoader {
	private String type; // "Great Sword", "Long Sword", "Sword and Shield",
						 // "Dual Blades", "Hammer", "Hunting Horn", "Lance",
						 // "Gunlance", "Switch Axe", "Light Bowgun",
						 // "Heavy Bowgun", "Bow"

	public WeaponListCursorLoader(Context context, String type) {
		super(context);
		this.type = type;
	}

	@Override
	protected Cursor loadCursor() {
		// Query the list of weapons
		if (type == null) {
			Log.d("helpme", "type is null");
			return DataManager.get(getContext()).queryWeapon();
		} else {
			Log.d("helpme", "type = " + type);
			return DataManager.get(getContext()).queryWeaponType(type);
		}
	}
}
