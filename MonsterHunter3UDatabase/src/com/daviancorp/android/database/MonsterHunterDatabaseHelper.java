package com.daviancorp.android.database;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.daviancorp.android.monsterhunter3udatabase.R;

//
//   QUERY REFERENCE:
//   
//query(boolean distinct, 
//		String table, 
//		String[] columns, 
//		String selection, 
//		String[] selectionArgs, 
//		String groupBy, 
//		String having, 
//		String orderBy, 
//		String limit)


public class MonsterHunterDatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "MonsterHunterDatabaseHelper";
	
	private static final String DB_NAME = "mh3u.sqlite";
	private static final int VERSION = 1; // EDIT

	private final Context mContext;

	public MonsterHunterDatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String text;
		
		try {
			InputStream is = mContext.getResources().openRawResource(
					R.raw.mh3u);
			// We guarantee that the available method returns the total
			// size of the asset... of course, this does mean that a single
			// asset can't be more than 2 gigs.
			int size = is.available();

			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			// Convert the buffer into a string.
			text = new String(buffer);
		} catch (IOException e) {
			// Should never happen!
			throw new RuntimeException(e);
		}

		String[] str = text.split(";");
		
		try {
			for (String s : str) {
				if (!s.equals("") && !s.startsWith("-") && !s.startsWith("/")
						&& !s.startsWith("\n")) {
					db.execSQL(s);
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Implement schema changes and data massage here when upgrading

	}

	/*
	 * Get all monsters
	 */
	public MonsterListCursor queryMonsters() {
		// "SELECT DISTINCT * FROM monsters GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_MONSTERS, 
				null, 
				null, 
				null, 
				S.COLUMN_MONSTER_NAME, 
				null, 
				null, 
				null);

		return new MonsterListCursor(wrapped);
	}
	
	/*
	 * Get all small monsters
	 */
	public MonsterListCursor querySmallMonsters() {
		// "SELECT DISTINCT * FROM monsters WHERE class = 'Minion' GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_MONSTERS, 
				null, 
				S.COLUMN_MONSTER_CLASS + "=?", 
				new String[] {"Minion"}, 
				S.COLUMN_MONSTER_NAME, 
				null, 
				null, 
				null);

		return new MonsterListCursor(wrapped);
	}
	
	/*
	 * Get all large monsters
	 */
	public MonsterListCursor queryLargeMonsters() {
		// "SELECT DISTINCT * FROM monsters WHERE class = 'Boss' GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_MONSTERS, 
				null, 
				S.COLUMN_MONSTER_CLASS + "=?", 
				new String[] {"Boss"}, 
				S.COLUMN_MONSTER_NAME, 
				null, 
				null, 
				null);

		return new MonsterListCursor(wrapped);
	}
	
}
