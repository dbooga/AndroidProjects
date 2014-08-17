package com.daviancorp.android.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

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

// query(SQLiteDatabase db, 
//	String[] projectionIn, 
//	String selection, 
//	String[] selectionArgs, 
//	String groupBy, 
//	String having, 
//	String sortOrder, 
//	String limit)


public class MonsterHunterDatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "MonsterHunterDatabaseHelper";
	
	private static final String DB_NAME = "mh3u.sqlite";
	private static final int VERSION = 1; // EDIT

	private final Context mContext;
	
	private boolean _Distinct;
	private String _Table; 
	private String[] _Columns; 
	private String _Selection; 
	private String[] _SelectionArgs; 
	private String _GroupBy; 
	private String _Having; 
	private String _OrderBy; 
	private String _Limit;
	

	public MonsterHunterDatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		mContext = context;
		
		_Distinct = false;
		_Table = null;
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
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
	
	private Cursor wrapHelper() {
		return getReadableDatabase().query(_Distinct, _Table, _Columns, _Selection, _SelectionArgs,_GroupBy,_Having, _OrderBy, _Limit);
	}
	
	private Cursor wrapJoinHelper(SQLiteQueryBuilder qb) {
		return qb.query(getReadableDatabase(), _Columns, _Selection, _SelectionArgs, _GroupBy, _Having, _OrderBy, _Limit);
	}
	
/********************************* COMBINING QUERIES ******************************************/
	
	/*
	 * Get all combinings
	 */
	public CombiningCursor queryCombinings() {
		// "SELECT DISTINCT * FROM combining"
		
		_Distinct = true;
		_Table = S.TABLE_COMBINING;
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new CombiningCursor(wrapHelper());
	}
	
	/*
	 * Get a specific combining
	 */
	public CombiningCursor queryCombining(long id) {
		// "SELECT DISTINCT * FROM combining WHERE _id = id LIMIT 1"
		
		_Distinct = false;
		_Table = S.TABLE_COMBINING;
		_Columns = null;
		_Selection = S.COLUMN_COMBINING_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new CombiningCursor(wrapHelper());
	}	
	
/********************************* DECORATION QUERIES ******************************************/
	
	/*
	 * Get all decoration
	 */
	public DecorationCursor queryDecorations() {

		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new DecorationCursor(wrapJoinHelper(builderDecoration()));
	}
	
	/*
	 * Get a specific decoration
	 */
	public DecorationCursor queryDecoration(long id) {

		_Columns = null;
		_Selection = "i._id" + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new DecorationCursor(wrapJoinHelper(builderDecoration()));
	}	

	/*
	 * Helper method to query for decorations
	 */
	private SQLiteQueryBuilder builderDecoration() {
//		 SELECT i._id AS item_id, i.name, i.jpn_name, i.type, i.rarity, i.carry_capacity, i.buy, i.sell, i.description, 
//		 i.icon_name, i.armor_dupe_name_fix, d.num_slots, s1._id AS skill_1_id, s1.name AS skill_1_name, its1.point_value 
//		 AS skill_1_point, s2._id AS skill_1_id, s2.name AS skill_2_name, its2.point_value AS skill_2_point
//		 FROM decorations AS d LEFT OUTER JOIN items AS i ON d._id = i._id
//		 LEFT OUTER JOIN item_to_skill_tree AS its1 ON i._id = its1.item_id and its1.point_value > 0
//		 LEFT OUTER JOIN skill_trees AS s1 ON its1.skill_tree_id = s1._id
//		 LEFT OUTER JOIN item_to_skill_tree AS its2 ON i._id = its2.item_id and s1._id != its2.skill_tree_id
//		 LEFT OUTER JOIN skill_trees AS s2 ON its2.skill_tree_id = s2._id;

		HashMap<String, String> projectionMap = new HashMap<String, String>();
		projectionMap.put("_id", "i." + S.COLUMN_ITEMS_ID + " AS " + "_id");
		projectionMap.put("item_name", "i." + S.COLUMN_ITEMS_NAME + " AS " + "item_name");
		projectionMap.put(S.COLUMN_ITEMS_JPN_NAME, "i." + S.COLUMN_ITEMS_JPN_NAME);
		projectionMap.put(S.COLUMN_ITEMS_TYPE, "i." + S.COLUMN_ITEMS_TYPE);
		projectionMap.put(S.COLUMN_ITEMS_RARITY, "i." + S.COLUMN_ITEMS_RARITY);
		projectionMap.put(S.COLUMN_ITEMS_CARRY_CAPACITY, "i." + S.COLUMN_ITEMS_CARRY_CAPACITY);
		projectionMap.put(S.COLUMN_ITEMS_BUY, "i." + S.COLUMN_ITEMS_BUY);
		projectionMap.put(S.COLUMN_ITEMS_SELL, "i." + S.COLUMN_ITEMS_SELL);
		projectionMap.put(S.COLUMN_ITEMS_DESCRIPTION, "i." + S.COLUMN_ITEMS_DESCRIPTION);
		projectionMap.put(S.COLUMN_ITEMS_ICON_NAME, "i." + S.COLUMN_ITEMS_ICON_NAME);
		projectionMap.put(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX, "i." + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX);
		projectionMap.put(S.COLUMN_DECORATIONS_NUM_SLOTS, "d." + S.COLUMN_DECORATIONS_NUM_SLOTS);
		projectionMap.put("skill_1_id", "s1." + S.COLUMN_SKILL_TREES_ID + " AS " + "skill_1_id");
		projectionMap.put("skill_1_name", "s1." + S.COLUMN_SKILL_TREES_NAME + " AS " + "skill_1_name");
		projectionMap.put("skill_1_point_value", "its1." + S.COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE + " AS " + "skill_1_point_value");
		projectionMap.put("skill_2_id", "s2." + S.COLUMN_SKILL_TREES_ID + " AS " + "skill_2_id");
		projectionMap.put("skill_2_name", "s2." + S.COLUMN_SKILL_TREES_NAME + " AS " + "skill_2_name");
		projectionMap.put("skill_2_point_value", "its2." + S.COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE + " AS " + "skill_2_point_value");
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		 
		_QB.setTables(S.TABLE_DECORATIONS + " AS d" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "d." +
		        S.COLUMN_DECORATIONS_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_ITEM_TO_SKILL_TREE +
		        " AS its1 " + " ON " + "i." + S.COLUMN_ITEMS_ID + " = " + "its1." + S.COLUMN_ITEM_TO_SKILL_TREE_ID + " AND " + 
		        "its1." + S.COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE + " > 0 " + " LEFT OUTER JOIN " + S.TABLE_SKILL_TREES + " AS s1" +
		        " ON " + "its1." + S.COLUMN_SKILL_TREES_ID + " = " + "s1." + S.COLUMN_SKILL_TREES_ID + 
		        " LEFT OUTER JOIN " + S.TABLE_ITEM_TO_SKILL_TREE +
		        " AS its2 " + " ON " + "i." + S.COLUMN_ITEMS_ID + " = " + "its2." + S.COLUMN_ITEM_TO_SKILL_TREE_ID + " AND " + 
		        "its2." + S.COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE + " > 0 " + " LEFT OUTER JOIN " + S.TABLE_SKILL_TREES + " AS s2" +
		        " ON " + "its2." + S.COLUMN_SKILL_TREES_ID + " = " + "s2." + S.COLUMN_SKILL_TREES_ID );
		
		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	
/********************************* ITEM QUERIES ******************************************/
	
	/*
	 * Get all items
	 */
	public ItemCursor queryItems() {
		// "SELECT DISTINCT * FROM items GROUP BY name LIMIT 1114"
		
		_Distinct = true;
		_Table = S.TABLE_LOCATIONS;
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1114";
		
		return new ItemCursor(wrapHelper());
	}
	
	/*
	 * Get a specific item
	 */
	public ItemCursor queryItem(long id) {
		// "SELECT DISTINCT * FROM items WHERE _id = id LIMIT 1"
		
		_Distinct = false;
		_Table = S.TABLE_ITEMS;
		_Columns = null;
		_Selection = S.COLUMN_ITEMS_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new ItemCursor(wrapHelper());
	}	
	
	

/********************************* LOCATION QUERIES ******************************************/
	
	/*
	 * Get all locations
	 */
	public LocationCursor queryLocations() {
		// "SELECT DISTINCT * FROM locations GROUP BY name"
		
		_Distinct = true;
		_Table = S.TABLE_LOCATIONS;
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new LocationCursor(wrapHelper());
	}
	
	/*
	 * Get a specific location
	 */
	public LocationCursor queryLocation(long id) {
		// "SELECT DISTINCT * FROM locations WHERE _id = id LIMIT 1"	
		
		_Distinct = false;
		_Table = S.TABLE_LOCATIONS;
		_Columns = null;
		_Selection = S.COLUMN_LOCATIONS_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new LocationCursor(wrapHelper());
	}
	
/********************************* MONSTER QUERIES ******************************************/
	
	/*
	 * Get all monsters
	 */
	public MonsterCursor queryMonsters() {
		// "SELECT DISTINCT * FROM monsters GROUP BY name"		
		
		_Distinct = true;
		_Table = S.TABLE_MONSTERS;
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = S.COLUMN_MONSTERS_NAME;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new MonsterCursor(wrapHelper());
	}
	
	/*
	 * Get all small monsters
	 */
	public MonsterCursor querySmallMonsters() {
		// "SELECT DISTINCT * FROM monsters WHERE class = 'Minion' GROUP BY name"
		
		_Distinct = true;
		_Table = S.TABLE_MONSTERS;
		_Columns = null;
		_Selection = S.COLUMN_MONSTERS_CLASS + " = ?";
		_SelectionArgs = new String[] {"Minion"};
		_GroupBy = S.COLUMN_MONSTERS_NAME;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MonsterCursor(wrapHelper());
	}
	
	/*
	 * Get all large monsters
	 */
	public MonsterCursor queryLargeMonsters() {
		// "SELECT DISTINCT * FROM monsters WHERE class = 'Boss' GROUP BY name"
		
		_Distinct = true;
		_Table = S.TABLE_MONSTERS;
		_Columns = null;
		_Selection = S.COLUMN_MONSTERS_CLASS + " = ?";
		_SelectionArgs = new String[] {"Boss"};
		_GroupBy = S.COLUMN_MONSTERS_NAME;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MonsterCursor(wrapHelper());
	}
	
	/*
	 * Get a specific monster
	 */
	public MonsterCursor queryMonster(long id) {
		// "SELECT DISTINCT * FROM monsters WHERE _id = id LIMIT 1"
		
		_Distinct = false;
		_Table = S.TABLE_MONSTERS;
		_Columns = null;
		_Selection = S.COLUMN_MONSTERS_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new MonsterCursor(wrapHelper());
	}

/********************************* SKILL TREE QUERIES ******************************************/	

	/*
	 * Get all skill tress
	 */
	public SkillTreeCursor querySkillTrees() {
		// "SELECT DISTINCT * FROM skill_trees GROUP BY name"
		
		_Distinct = true;
		_Table = S.TABLE_SKILL_TREES;
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = S.COLUMN_SKILL_TREES_NAME;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new SkillTreeCursor(wrapHelper());
	}
	
	/*
	 * Get a specific skill tree
	 */
	public SkillTreeCursor querySkillTree(long id) {
		// "SELECT DISTINCT * FROM skill_trees WHERE _id = id LIMIT 1"
		
		_Distinct = false;
		_Table = S.TABLE_SKILL_TREES;
		_Columns = null;
		_Selection = S.COLUMN_SKILL_TREES_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new SkillTreeCursor(wrapHelper());
	}	
	
	
	
	
	
}
