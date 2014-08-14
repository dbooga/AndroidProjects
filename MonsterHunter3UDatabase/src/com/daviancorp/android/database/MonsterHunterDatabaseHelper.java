package com.daviancorp.android.database;

import java.io.IOException;
import java.io.InputStream;

import com.daviancorp.android.data.Monster;
import com.daviancorp.android.monsterhunter3udatabase.R;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
//

public class MonsterHunterDatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "MonsterHunterDatabaseHelper";
	
	private static final String DB_NAME = "mh3u.sqlite";
	private static final int VERSION = 1; // EDIT

	private static final String TABLE_MONSTERS = "monsters";
	private static final String COLUMN_MONSTER_ID = "_id";
	private static final String COLUMN_MONSTER_NAME = "name";
	private static final String COLUMN_MONSTER_CLASS = "class";
	private static final String COLUMN_MONSTER_TRAIT = "trait";

	private final Context mContext;

	public MonsterHunterDatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.d("#######", "onCreate()");
		String text;
		
		try {
			InputStream is = mContext.getResources().openRawResource(
					R.raw.mh3u);
			Log.d(TAG, "try");
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

			Log.d(TAG, "try good");
		} catch (IOException e) {
			// Should never happen!
			Log.d(TAG, "catch");
			throw new RuntimeException(e);
		}

		String[] str = text.split(";");
		int ee = 1;
		String curr = "";
		Log.d(TAG, "for");
		try {
			for (String s : str) {
				curr = s;
				ee++;
				if (!s.equals("") && !s.startsWith("-") && !s.startsWith("/")
						&& !s.startsWith("\n")) {
					db.execSQL(s);
				}
			}
		} catch (Exception e) {
			Log.d(TAG, "****! " + ee + ": " + curr);
			throw e;
		}
		Log.d(TAG, "good!");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Implement schema changes and data massage here when upgrading

	}

	/*
	 * Get all monsters
	 */
	public MonsterCursor queryMonsters() {
		// "SELECT * FROM monsters "
		Cursor wrapped = getReadableDatabase().query(TABLE_MONSTERS,
				null, null, null, null, null, null);
		return new MonsterCursor(wrapped);
		
	}
	
	
	
	
	/**
	 * A convenience class to wrap a cursor that returns rows from the "monsters" table.
	 * The {@link getMonster()} method will give you a Monster instance representing
	 * the current row.
	 */
	public static class MonsterCursor extends CursorWrapper {
		
		public MonsterCursor(Cursor c) {
			super(c);
		}
		
		/**
		 * Returns a Monster object configured for the current row,
		 * or null if the current row is invalid.
		 */
		public Monster getMonster() {
			if (isBeforeFirst() || isAfterLast())
				return null;
			Monster monster = new Monster();
			
			long monsterId = getLong(getColumnIndex(COLUMN_MONSTER_ID));
			monster.setId(monsterId);
			String name = getString(getColumnIndex(COLUMN_MONSTER_NAME));
			monster.setName(name);
			String monsterClass = getString(getColumnIndex(COLUMN_MONSTER_CLASS));
			monster.setMonsterClass(monsterClass);
			String trait = getString(getColumnIndex(COLUMN_MONSTER_TRAIT));
			monster.setTrait(trait);

			return monster;
		}
	}
}
