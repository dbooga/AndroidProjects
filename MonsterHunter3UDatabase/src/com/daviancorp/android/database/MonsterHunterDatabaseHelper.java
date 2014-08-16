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
	
/********************************* COMBINING QUERIES ******************************************/
	
	/*
	 * Get all combinings
	 */
	public CombiningCursor queryCombinings() {
		// "SELECT DISTINCT * FROM combining"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_COMBINING, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null);

		return new CombiningCursor(wrapped);
	}
	
	/*
	 * Get a specific combining
	 */
	public CombiningCursor queryCombining(long id) {
		// "SELECT DISTINCT * FROM combining WHERE _id = id LIMIT 1"
		Cursor wrapped = getReadableDatabase().query(S.TABLE_COMBINING,
				null,
				S.COLUMN_COMBINING_ID + " = ?",
				new String[]{ String.valueOf(id) },
				null,
				null,
				null,
				"1");
		return new CombiningCursor(wrapped);
	}	
	
/********************************* DECORATION QUERIES ******************************************/
	
	/*
	 * Get all decoration
	 */
	public DecorationCursor queryDecorations() {
		// "SELECT DISTINCT * FROM decorations"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_DECORATIONS, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null);

		return new DecorationCursor(wrapped);
	}
	
	/*
	 * Get a specific decoration
	 */
	public DecorationCursor queryDecoration(long id) {
		// "SELECT DISTINCT * FROM decorations WHERE _id = id LIMIT 1"
		Cursor wrapped = getReadableDatabase().query(S.TABLE_DECORATIONS,
				null,
				S.COLUMN_DECORATIONS_ID + " = ?",
				new String[]{ String.valueOf(id) },
				null,
				null,
				null,
				"1");
		return new DecorationCursor(wrapped);
	}	

/********************************* ITEM QUERIES ******************************************/
	
	/*
	 * Get all items
	 */
	public ItemCursor queryItems() {
		// "SELECT DISTINCT * FROM items GROUP BY name LIMIT 1114"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_ITEMS, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				"1114");

		return new ItemCursor(wrapped);
	}
	
	/*
	 * Get a specific item
	 */
	public ItemCursor queryItem(long id) {
		// "SELECT DISTINCT * FROM items WHERE _id = id LIMIT 1"
		Cursor wrapped = getReadableDatabase().query(S.TABLE_ITEMS,
				null,
				S.COLUMN_ITEMS_ID + " = ?",
				new String[]{ String.valueOf(id) },
				null,
				null,
				null,
				"1");
		return new ItemCursor(wrapped);
	}	
	
	

/********************************* LOCATION QUERIES ******************************************/
	
	/*
	 * Get all locations
	 */
	public LocationCursor queryLocations() {
		// "SELECT DISTINCT * FROM locations GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_LOCATIONS, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null);

		return new LocationCursor(wrapped);
	}
	
	/*
	 * Get a specific location
	 */
	public LocationCursor queryLocation(long id) {
		// "SELECT DISTINCT * FROM locations WHERE _id = id LIMIT 1"
		Cursor wrapped = getReadableDatabase().query(S.TABLE_LOCATIONS,
				null,
				S.COLUMN_LOCATIONS_ID + " = ?",
				new String[]{ String.valueOf(id) },
				null,
				null,
				null,
				"1");
		return new LocationCursor(wrapped);
	}
	
/********************************* MONSTER QUERIES ******************************************/
	
	/*
	 * Get all monsters
	 */
	public MonsterCursor queryMonsters() {
		// "SELECT DISTINCT * FROM monsters GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_MONSTERS, 
				null, 
				null, 
				null, 
				S.COLUMN_MONSTERS_NAME, 
				null, 
				null, 
				null);

		return new MonsterCursor(wrapped);
	}
	
	/*
	 * Get all small monsters
	 */
	public MonsterCursor querySmallMonsters() {
		// "SELECT DISTINCT * FROM monsters WHERE class = 'Minion' GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_MONSTERS, 
				null, 
				S.COLUMN_MONSTERS_CLASS + "=?", 
				new String[] {"Minion"}, 
				S.COLUMN_MONSTERS_NAME, 
				null, 
				null, 
				null);

		return new MonsterCursor(wrapped);
	}
	
	/*
	 * Get all large monsters
	 */
	public MonsterCursor queryLargeMonsters() {
		// "SELECT DISTINCT * FROM monsters WHERE class = 'Boss' GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_MONSTERS, 
				null, 
				S.COLUMN_MONSTERS_CLASS + " = ?", 
				new String[] {"Boss"}, 
				S.COLUMN_MONSTERS_NAME, 
				null, 
				null, 
				null);

		return new MonsterCursor(wrapped);
	}
	
	/*
	 * Get a specific monster
	 */
	public MonsterCursor queryMonster(long id) {
		// "SELECT DISTINCT * FROM monsters WHERE _id = id LIMIT 1"
		Cursor wrapped = getReadableDatabase().query(S.TABLE_MONSTERS,
				null,
				S.COLUMN_MONSTERS_ID + " = ?",
				new String[]{ String.valueOf(id) },
				null,
				null,
				null,
				"1");
		return new MonsterCursor(wrapped);
	}

/********************************* SKILL TREE QUERIES ******************************************/	

	/*
	 * Get all skill tress
	 */
	public SkillTreeCursor querySkillTrees() {
		// "SELECT DISTINCT * FROM skill_trees GROUP BY name"
		Cursor wrapped = getReadableDatabase().query(true, 
				S.TABLE_SKILL_TREES, 
				null, 
				null, 
				null, 
				S.COLUMN_SKILL_TREES_NAME, 
				null, 
				null, 
				null);

		return new SkillTreeCursor(wrapped);
	}
	
	/*
	 * Get a specific skill tree
	 */
	public SkillTreeCursor querySkillTree(long id) {
		// "SELECT DISTINCT * FROM skill_trees WHERE _id = id LIMIT 1"
		Cursor wrapped = getReadableDatabase().query(S.TABLE_SKILL_TREES,
				null,
				S.COLUMN_SKILL_TREES_ID + " = ?",
				new String[]{ String.valueOf(id) },
				null,
				null,
				null,
				"1");
		return new SkillTreeCursor(wrapped);
	}	
	
	
	
	
	
}
