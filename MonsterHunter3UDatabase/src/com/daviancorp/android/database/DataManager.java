package com.daviancorp.android.database;

import android.content.Context;
import android.util.Log;

public class DataManager {
	private static final String TAG = "DataManager";

	
	private static DataManager sDataManager;
	private Context mAppContext;
	private MonsterHunterDatabaseHelper mHelper;
	
	private DataManager(Context appContext) {
		mAppContext = appContext;
		
		mHelper = new MonsterHunterDatabaseHelper(mAppContext);

		Log.d(TAG, "MHDH created");
	}
	
	public static DataManager get(Context c) {
		Log.d(TAG, "DataManager created");
		if (sDataManager == null) {
			// Use the application context to avoid leaking activities
			sDataManager = new DataManager(c.getApplicationContext());
		}
		return sDataManager;
	}
	
	public MonsterListCursor queryMonsters() {
		return mHelper.queryMonsters();
	}
	
	
}
