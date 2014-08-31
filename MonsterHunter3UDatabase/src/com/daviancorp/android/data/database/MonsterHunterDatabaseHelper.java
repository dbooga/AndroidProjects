package com.daviancorp.android.data.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

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
		String temp = "";
		String before = "";
		
		try {
			for (String s : str) {
				temp = s;
				if (!s.equals("") && !s.startsWith("-") && !s.startsWith("/")
						&& !s.startsWith("\n") && !s.startsWith("\r")) {
					db.execSQL(s);
				}
				before = s;
			}
		} catch (Exception e) {
			int t = (int) temp.charAt(0);
			Log.d("helpme", "Before: " + before);
			Log.d("helpme", "String: " + temp);
			Log.d("helpme", "ascii: " + t);
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
	
/********************************* ARMOR QUERIES ******************************************/
	
	/*
	 * Get all armor
	 */
	public ArmorCursor queryArmor() {

		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new ArmorCursor(wrapJoinHelper(builderArmor()));
	}
	
	/*
	 * Get a specific armor
	 */
	public ArmorCursor queryArmor(long id) {

		_Columns = null;
		_Selection = "a." + S.COLUMN_ARMOR_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new ArmorCursor(wrapJoinHelper(builderArmor()));
	}	
	
	/*
	 * Get a specific armor based on hunter type
	 */
	public ArmorCursor queryArmorType(String type) {

		_Columns = null;
		_Selection = "a." + S.COLUMN_ARMOR_HUNTER_TYPE + " = ? " + " OR " +
					"a." + S.COLUMN_ARMOR_HUNTER_TYPE + " = 'Both'";
		_SelectionArgs = new String[]{type};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ArmorCursor(wrapJoinHelper(builderArmor()));
	}
	
	/*
	 * Get a specific armor based on slot
	 */
	public ArmorCursor queryArmorSlot(String slot) {

		_Columns = null;
		_Selection = "a." + S.COLUMN_ARMOR_SLOT + " = ?";
		_SelectionArgs = new String[]{slot};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ArmorCursor(wrapJoinHelper(builderArmor()));
	}
	
	/*
	 * Get a specific armor based on hunter type and slot
	 */
	public ArmorCursor queryArmorTypeSlot(String type, String slot) {

		_Columns = null;
		_Selection = "(a." + S.COLUMN_ARMOR_HUNTER_TYPE + " = ?" + " OR " +
				"a." + S.COLUMN_ARMOR_HUNTER_TYPE + " = 'Both') " + " AND " + 
				"a." + S.COLUMN_ARMOR_SLOT + " = ?";
		_SelectionArgs = new String[]{type, slot};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ArmorCursor(wrapJoinHelper(builderArmor()));
	}

	/*
	 * Helper method to query for armor
	 */
	private SQLiteQueryBuilder builderArmor() {
//		SELECT a._id AS _id, a.slot, a.defense, a.max_defense, a.fire_res, a.thunder_res,
//		a.dragon_res, a.water_res, a.ice_res, a.gender, a.hunter_type, a.num_slots,
//		i.name, i.jpn_name, i.type, i.rarity, i.carry_capacity, i.buy, i.sell, i.description,
//		i.icon_name, i.armor_dupe_name_fix
//		FROM armor AS a LEFT OUTER JOIN	items AS i ON a._id = i._id;

		String a = "a";
		String i = "i";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", a + "." + S.COLUMN_ARMOR_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_ARMOR_SLOT, a + "." + S.COLUMN_ARMOR_SLOT);
		projectionMap.put(S.COLUMN_ARMOR_DEFENSE, a + "." + S.COLUMN_ARMOR_DEFENSE);
		projectionMap.put(S.COLUMN_ARMOR_MAX_DEFENSE, a + "." + S.COLUMN_ARMOR_MAX_DEFENSE);
		projectionMap.put(S.COLUMN_ARMOR_FIRE_RES, a + "." + S.COLUMN_ARMOR_FIRE_RES);
		projectionMap.put(S.COLUMN_ARMOR_THUNDER_RES, a + "." + S.COLUMN_ARMOR_THUNDER_RES);
		projectionMap.put(S.COLUMN_ARMOR_DRAGON_RES, a + "." + S.COLUMN_ARMOR_DRAGON_RES);
		projectionMap.put(S.COLUMN_ARMOR_WATER_RES, a + "." + S.COLUMN_ARMOR_WATER_RES);
		projectionMap.put(S.COLUMN_ARMOR_ICE_RES, a + "." + S.COLUMN_ARMOR_ICE_RES);
		projectionMap.put(S.COLUMN_ARMOR_GENDER, a + "." + S.COLUMN_ARMOR_GENDER);
		projectionMap.put(S.COLUMN_ARMOR_HUNTER_TYPE, a + "." + S.COLUMN_ARMOR_HUNTER_TYPE);
		projectionMap.put(S.COLUMN_ARMOR_NUM_SLOTS, a + "." + S.COLUMN_ARMOR_NUM_SLOTS);
		projectionMap.put(S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME);
		projectionMap.put(S.COLUMN_ITEMS_JPN_NAME, i + "." + S.COLUMN_ITEMS_JPN_NAME);
		projectionMap.put(S.COLUMN_ITEMS_TYPE, i + "." + S.COLUMN_ITEMS_TYPE);
		projectionMap.put(S.COLUMN_ITEMS_RARITY, i + "." + S.COLUMN_ITEMS_RARITY);
		projectionMap.put(S.COLUMN_ITEMS_CARRY_CAPACITY, i + "." + S.COLUMN_ITEMS_CARRY_CAPACITY);
		projectionMap.put(S.COLUMN_ITEMS_BUY, i + "." + S.COLUMN_ITEMS_BUY);
		projectionMap.put(S.COLUMN_ITEMS_SELL, i + "." + S.COLUMN_ITEMS_SELL);
		projectionMap.put(S.COLUMN_ITEMS_DESCRIPTION, i + "." + S.COLUMN_ITEMS_DESCRIPTION);
		projectionMap.put(S.COLUMN_ITEMS_ICON_NAME, i + "." + S.COLUMN_ITEMS_ICON_NAME);
		projectionMap.put(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX, i + "." + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX);
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_ARMOR + " AS a" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "a." +
				S.COLUMN_ARMOR_ID + " = " + "i." + S.COLUMN_ITEMS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	
/********************************* CARVE QUERIES ******************************************/
	
	/*
	 * Get all carves based on item
	 */
	public CarveCursor queryCarveItem(long id) {
		
		_Columns = null;
		_Selection = "c." + S.COLUMN_CARVES_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new CarveCursor(wrapJoinHelper(builderCarve()));
	}
	
	/*
	 * Get all carves based on what the query is for
	 */
	public CarveCursor queryCarveMonster(long id) {
		
		_Columns = null;
		_Selection = "c." + S.COLUMN_CARVES_MONSTER_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new CarveCursor(wrapJoinHelper(builderCarve()));
	}
	
	/*
	 * Get all carves based on what the query is for
	 */
	public CarveCursor queryCarveMonsterRank(long id, String rank) {
		
		_Columns = null;
		_Selection = "c." + S.COLUMN_CARVES_MONSTER_ID + " = ? " + "AND " + 
				"c." + S.COLUMN_CARVES_RANK + " = ? ";
		_SelectionArgs = new String[]{"" + id, rank};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new CarveCursor(wrapJoinHelper(builderCarve()));
	}

	/*
	 * Helper method to query for carve
	 */
	private SQLiteQueryBuilder builderCarve() {
//		SELECT c._id AS _id, c.item_id, c.monster_id, c.rank, 
//		c.location, c.num_carves, c.percentage, i.name AS iname, 
//		m.name AS mname
//		FROM carves AS c 
//		LEFT OUTER JOIN items AS i ON c.item_id = i._id
//		LEFT OUTER JOIN monsters AS m ON c.monster_id = m._id;

		String c = "c";
		String i = "i";
		String m = "m";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", c + "." + S.COLUMN_CARVES_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_CARVES_ITEM_ID, c + "." + S.COLUMN_CARVES_ITEM_ID);
		projectionMap.put(S.COLUMN_CARVES_MONSTER_ID, c + "." + S.COLUMN_CARVES_MONSTER_ID);
		projectionMap.put(S.COLUMN_CARVES_RANK, c + "." + S.COLUMN_CARVES_RANK);
		projectionMap.put(S.COLUMN_CARVES_LOCATION, c + "." + S.COLUMN_CARVES_LOCATION);
		projectionMap.put(S.COLUMN_CARVES_NUM_CARVES, c + "." + S.COLUMN_CARVES_NUM_CARVES);
		projectionMap.put(S.COLUMN_CARVES_PERCENTAGE, c + "." + S.COLUMN_CARVES_PERCENTAGE);
		
		projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
		projectionMap.put(m + S.COLUMN_MONSTERS_NAME, m + "." + S.COLUMN_MONSTERS_NAME + " AS " + m + S.COLUMN_MONSTERS_NAME);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_CARVES + " AS c" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "c." +
				S.COLUMN_CARVES_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_MONSTERS +
				" AS m " + " ON " + "c." + S.COLUMN_CARVES_MONSTER_ID + " = " + "m." + S.COLUMN_MONSTERS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}

/********************************* COMBINING QUERIES ******************************************/
	
	/*
	 * Get all combinings
	 */
	public CombiningCursor queryCombinings() {
		
		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new CombiningCursor(wrapJoinHelper(builderCursor()));
	}
	
	/*
	 * Get a specific combining
	 */
	public CombiningCursor queryCombining(long id) {
		
		_Columns = null;
		_Selection = "c._id" + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new CombiningCursor(wrapJoinHelper(builderCursor()));
	}	
	
	private SQLiteQueryBuilder builderCursor()  {
//		SELECT c._id AS _id, c.amount_made_min,  c.amount_made_max, c.percentage, 
//		crt._id AS crt__id, crt.name AS crt_name, crt.jpn_name AS crt_jpn_name, crt.type AS crt_type, crt.rarity AS crt_rarity, 
//		crt.carry_capacity AS crt_carry_capacity, crt.buy AS crt_buy, crt.sell AS crt_sell, crt.description AS crt_description, 
//		crt.icon_name AS crt_icon_name, crt.armor_dupe_name_fix AS crt_armor_dupe_name, 
//
//		mat1._id AS mat1__id, mat1.name AS mat1_name, mat1.jpn_name AS mat1_jpn_name, mat1.type AS mat1_type, mat1.rarity AS mat1_rarity, 
//		mat1.carry_capacity AS mat1_carry_capacity, mat1.buy AS mat1_buy, mat1.sell AS mat1_sell, mat1.description AS mat1_description, 
//		mat1.icon_name AS mat1_icon_name, mat1.armor_dupe_name_fix AS mat1_armor_dupe_name,
//
//
//		mat2._id AS mat2__id, mat2.name AS mat2_name, mat2.jpn_name AS mat2_jpn_name, mat2.type AS mat2_type, mat2.rarity AS mat2_rarity, 
//		mat2.carry_capacity AS mat2_carry_capacity, mat2.buy AS mat2_buy, mat2.sell AS mat2_sell, mat2.description AS mat2_description, 
//		mat2.icon_name AS mat2_icon_name, mat2.armor_dupe_name_fix AS mat2_armor_dupe_name
//
//		FROM combining AS c LEFT OUTER JOIN items AS crt ON c.created_item_id = crt._id
//		LEFT OUTER JOIN items AS mat1 ON c.item_1_id = mat1._id
//		LEFT OUTER JOIN items AS mat2 ON c.item_2_id = mat2._id;
		
		String comb = "c.";
		String[] items = new String[] {"crt", "mat1", "mat2"};

		HashMap<String, String> projectionMap = new HashMap<String, String>();
		projectionMap.put("_id", comb + S.COLUMN_ITEMS_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_COMBINING_AMOUNT_MADE_MIN, comb + S.COLUMN_COMBINING_AMOUNT_MADE_MIN);
		projectionMap.put(S.COLUMN_COMBINING_AMOUNT_MADE_MAX, comb + S.COLUMN_COMBINING_AMOUNT_MADE_MAX);
		projectionMap.put(S.COLUMN_COMBINING_PERCENTAGE, comb + S.COLUMN_COMBINING_PERCENTAGE);
		
		for (String i : items) {
			projectionMap.put(i + S.COLUMN_ITEMS_ID, i + "." + S.COLUMN_ITEMS_ID + " AS " + i + S.COLUMN_ITEMS_ID);
			
			projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
			projectionMap.put(i + S.COLUMN_ITEMS_JPN_NAME, i + "." + S.COLUMN_ITEMS_JPN_NAME + " AS " + i + S.COLUMN_ITEMS_JPN_NAME);
			projectionMap.put(i + S.COLUMN_ITEMS_TYPE, i + "." + S.COLUMN_ITEMS_TYPE + " AS " + i + S.COLUMN_ITEMS_TYPE);
			projectionMap.put(i + S.COLUMN_ITEMS_RARITY, i + "." + S.COLUMN_ITEMS_RARITY + " AS " + i + S.COLUMN_ITEMS_RARITY);
			projectionMap.put(i + S.COLUMN_ITEMS_CARRY_CAPACITY, i + "." + S.COLUMN_ITEMS_CARRY_CAPACITY + " AS " + i + S.COLUMN_ITEMS_CARRY_CAPACITY);
			projectionMap.put(i + S.COLUMN_ITEMS_BUY, i + "." + S.COLUMN_ITEMS_BUY + " AS " + i + S.COLUMN_ITEMS_BUY);
			projectionMap.put(i + S.COLUMN_ITEMS_SELL, i + "." + S.COLUMN_ITEMS_SELL + " AS " + i + S.COLUMN_ITEMS_SELL);
			projectionMap.put(i + S.COLUMN_ITEMS_DESCRIPTION, i + "." + S.COLUMN_ITEMS_DESCRIPTION + " AS " + i + S.COLUMN_ITEMS_DESCRIPTION);
			projectionMap.put(i + S.COLUMN_ITEMS_ICON_NAME, i + "." + S.COLUMN_ITEMS_ICON_NAME + " AS " + i + S.COLUMN_ITEMS_ICON_NAME);
			projectionMap.put(i + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX, i + "." + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX + " AS " + i + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX);
		}
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		 
		_QB.setTables(S.TABLE_COMBINING + " AS c" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS crt" + " ON " + "c." +
				S.COLUMN_COMBINING_CREATED_ITEM_ID + " = " + "crt." + S.COLUMN_ITEMS_ID +
				" LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS mat1" + " ON " + "c." +
				S.COLUMN_COMBINING_ITEM_1_ID + " = " + "mat1." + S.COLUMN_ITEMS_ID +
				" LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS mat2" + " ON " + "c." +
				S.COLUMN_COMBINING_ITEM_2_ID + " = " + "mat2." + S.COLUMN_ITEMS_ID);
				
		_QB.setProjectionMap(projectionMap);
		return _QB;
		
	}
	
/********************************* COMPONENT QUERIES ******************************************/
	
	/*
	 * Get all components for a created item
	 */
	public ComponentCursor queryComponentCreated(long id) {
		
		_Columns = null;
		_Selection = "c." + S.COLUMN_COMPONENTS_CREATED_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ComponentCursor(wrapJoinHelper(builderComponent()));
	}
	
	/*
	 * Get all components for a component item
	 */
	public ComponentCursor queryComponentComponent(long id) {
		
		_Columns = null;
		_Selection = "c." + S.COLUMN_COMPONENTS_COMPONENT_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ComponentCursor(wrapJoinHelper(builderComponent()));
	}

	/*
	 * Helper method to query for component
	 */
	private SQLiteQueryBuilder builderComponent() {
//		SELECT c._id AS _id, c.created_item_id, c.component_item_id,
//		c.quantity, c.type, cr.name AS crname, co.name AS coname
//		FROM components AS c
//		LEFT OUTER JOIN items AS cr ON c.created_item_id = cr._id
//		LEFT OUTER JOIN items AS co ON c.component_item_id = co._id;

		String c = "c";
		String cr = "cr";
		String co = "co";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", c + "." + S.COLUMN_COMPONENTS_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_COMPONENTS_CREATED_ITEM_ID, c + "." + S.COLUMN_COMPONENTS_CREATED_ITEM_ID);
		projectionMap.put(S.COLUMN_COMPONENTS_COMPONENT_ITEM_ID, c + "." + S.COLUMN_COMPONENTS_COMPONENT_ITEM_ID);
		projectionMap.put(S.COLUMN_COMPONENTS_QUANTITY, c + "." + S.COLUMN_COMPONENTS_QUANTITY);
		projectionMap.put(S.COLUMN_COMPONENTS_TYPE, c + "." + S.COLUMN_COMPONENTS_TYPE);

		projectionMap.put(cr + S.COLUMN_ITEMS_NAME, cr + "." + S.COLUMN_ITEMS_NAME + " AS " + cr + S.COLUMN_ITEMS_NAME);
		projectionMap.put(co + S.COLUMN_ITEMS_NAME, co + "." + S.COLUMN_ITEMS_NAME + " AS " + co + S.COLUMN_ITEMS_NAME);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_COMPONENTS + " AS c" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS cr" + " ON " + "c." +
				S.COLUMN_COMPONENTS_CREATED_ITEM_ID + " = " + "cr." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_ITEMS +
				" AS co " + " ON " + "c." + S.COLUMN_COMPONENTS_COMPONENT_ITEM_ID + " = " + "co." + S.COLUMN_ITEMS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}

/********************************* DECORATION QUERIES ******************************************/
	
	/*
	 * Get all decorations
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
	
/********************************* GATHERING QUERIES ******************************************/
	
	/*
	 * Get all gathering locations based on item
	 */
	public GatheringCursor queryGatheringItem(long id) {
		
		_Columns = null;
		_Selection = "g." + S.COLUMN_GATHERING_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new GatheringCursor(wrapJoinHelper(builderGathering()));
	}
	
	/*
	 * Get all gathering items based on location
	 */
	public GatheringCursor queryGatheringLocation(long id) {
		
		_Columns = null;
		_Selection = "g." + S.COLUMN_GATHERING_LOCATION_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new GatheringCursor(wrapJoinHelper(builderGathering()));
	}
	
	/*
	 * Get all gathering items based on location and rank
	 */
	public GatheringCursor queryGatheringLocationRank(long id, String rank) {
		
		_Columns = null;
		_Selection = "g." + S.COLUMN_GATHERING_LOCATION_ID + " = ? " + "AND " + 
				"g." + S.COLUMN_GATHERING_RANK + " = ? ";
		_SelectionArgs = new String[]{"" + id, rank};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new GatheringCursor(wrapJoinHelper(builderGathering()));
	}
	
	/*
	 * Helper method to query for Gathering
	 */
	private SQLiteQueryBuilder builderGathering() {
//		SELECT g._id AS _id, g.item_id, g.location_id, g.area,
//		g.site, g.site_set, g.site_set_percentage,
//		g.site_set_gathers_min, g.site_set_gathers_max, g.rank,
//		g.percentage, i.name AS iname, l.name AS lname
//		FROM gathering AS g
//		LEFT OUTER JOIN items AS i ON g.item_id = i._id
//		LEFT OUTER JOIN locations AS l on g.location_id = l._id;

		String g = "g";
		String i = "i";
		String l = "l";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", g + "." + S.COLUMN_GATHERING_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_GATHERING_ITEM_ID, g + "." + S.COLUMN_GATHERING_ITEM_ID);
		projectionMap.put(S.COLUMN_GATHERING_LOCATION_ID, g + "." + S.COLUMN_GATHERING_LOCATION_ID);
		projectionMap.put(S.COLUMN_GATHERING_AREA, g + "." + S.COLUMN_GATHERING_AREA);
		projectionMap.put(S.COLUMN_GATHERING_SITE, g + "." + S.COLUMN_GATHERING_SITE);
		projectionMap.put(S.COLUMN_GATHERING_SITE_SET, g + "." + S.COLUMN_GATHERING_SITE_SET);
		projectionMap.put(S.COLUMN_GATHERING_SITE_SET_PERCENTAGE, g + "." + S.COLUMN_GATHERING_SITE_SET_PERCENTAGE);
		projectionMap.put(S.COLUMN_GATHERING_SITE_SET_GATHERS_MIN, g + "." + S.COLUMN_GATHERING_SITE_SET_GATHERS_MIN);
		projectionMap.put(S.COLUMN_GATHERING_SITE_SET_GATHERS_MAX, g + "." + S.COLUMN_GATHERING_SITE_SET_GATHERS_MAX);
		projectionMap.put(S.COLUMN_GATHERING_RANK, g + "." + S.COLUMN_GATHERING_RANK);
		projectionMap.put(S.COLUMN_GATHERING_PERCENTAGE, g + "." + S.COLUMN_GATHERING_PERCENTAGE);
		
		projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
		projectionMap.put(l + S.COLUMN_LOCATIONS_NAME, l + "." + S.COLUMN_LOCATIONS_NAME + " AS " + l + S.COLUMN_LOCATIONS_NAME);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_GATHERING + " AS g" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "g." +
				S.COLUMN_GATHERING_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_LOCATIONS +
				" AS l " + " ON " + "g." + S.COLUMN_GATHERING_LOCATION_ID + " = " + "l." + S.COLUMN_LOCATIONS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	
/********************************* HUNTING FLEET QUERIES ******************************************/
	
	/*
	 * Get all hunting fleets
	 */
	public HuntingFleetCursor queryHuntingFleets() {

		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new HuntingFleetCursor(wrapJoinHelper(builderHuntingFleet()));
	}
	
	/*
	 * Get a specific hunting fleet
	 */
	public HuntingFleetCursor queryHuntingFleet(long id) {

		_Columns = null;
		_Selection = "h." + S.COLUMN_HUNTING_FLEET_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new HuntingFleetCursor(wrapJoinHelper(builderHuntingFleet()));
	}	
	
	/*
	 * Get a specific hunting fleet based on type
	 */
	public HuntingFleetCursor queryHuntingFleetType(String type) {

		_Columns = null;
		_Selection = "h." + S.COLUMN_HUNTING_FLEET_TYPE + " = ?";
		_SelectionArgs = new String[]{ type };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new HuntingFleetCursor(wrapJoinHelper(builderHuntingFleet()));
	}
	
	/*
	 * Get a specific hunting fleet based on location
	 */
	public HuntingFleetCursor queryHuntingFleetLocation(String location) {

		_Columns = null;
		_Selection = "h." + S.COLUMN_HUNTING_FLEET_LOCATION + " = ?";
		_SelectionArgs = new String[]{ location };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new HuntingFleetCursor(wrapJoinHelper(builderHuntingFleet()));
	}
	
	/*
	 * Helper method to query for hunting fleets
	 */
	private SQLiteQueryBuilder builderHuntingFleet() {
//		SELECT h._id AS _id, h.type AS htype, h.level, h.location, h.amount, h.percentage, h.rank,
//		h.item_id, i.name, i.jpn_name, i.type, i.rarity, i.carry_capacity, i.buy, i.sell,
//		i.description, i.icon_name, i.armor_dupe_name_fix
//		FROM hunting_fleet AS h LEFT OUTER JOIN items AS i ON h.item_id = i._id;

		String h = "h";
		String i = "i";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", h + "." + S.COLUMN_HUNTING_FLEET_ID + " AS " + "_id");
		projectionMap.put(h + S.COLUMN_HUNTING_FLEET_TYPE, h + "." + S.COLUMN_HUNTING_FLEET_TYPE + " AS " + h + S.COLUMN_HUNTING_FLEET_TYPE);
		projectionMap.put(S.COLUMN_HUNTING_FLEET_LEVEL, h + "." + S.COLUMN_HUNTING_FLEET_LEVEL);
		projectionMap.put(S.COLUMN_HUNTING_FLEET_LOCATION, h + "." + S.COLUMN_HUNTING_FLEET_LOCATION);
		projectionMap.put(S.COLUMN_HUNTING_FLEET_AMOUNT, h + "." + S.COLUMN_HUNTING_FLEET_AMOUNT);
		projectionMap.put(S.COLUMN_HUNTING_FLEET_PERCENTAGE, h + "." + S.COLUMN_HUNTING_FLEET_PERCENTAGE);
		projectionMap.put(S.COLUMN_HUNTING_FLEET_RANK, h + "." + S.COLUMN_HUNTING_FLEET_RANK);
		projectionMap.put(S.COLUMN_HUNTING_FLEET_ITEM_ID, h + "." + S.COLUMN_HUNTING_FLEET_ITEM_ID);
		
		projectionMap.put(S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME);
		projectionMap.put(S.COLUMN_ITEMS_JPN_NAME, i + "." + S.COLUMN_ITEMS_JPN_NAME);
		projectionMap.put(i + S.COLUMN_ITEMS_TYPE, i + "." + S.COLUMN_ITEMS_TYPE + " AS " + i + S.COLUMN_ITEMS_TYPE);
		projectionMap.put(S.COLUMN_ITEMS_RARITY, i + "." + S.COLUMN_ITEMS_RARITY);
		projectionMap.put(S.COLUMN_ITEMS_CARRY_CAPACITY, i + "." + S.COLUMN_ITEMS_CARRY_CAPACITY);
		projectionMap.put(S.COLUMN_ITEMS_BUY, i + "." + S.COLUMN_ITEMS_BUY);
		projectionMap.put(S.COLUMN_ITEMS_SELL, i + "." + S.COLUMN_ITEMS_SELL);
		projectionMap.put(S.COLUMN_ITEMS_DESCRIPTION, i + "." + S.COLUMN_ITEMS_DESCRIPTION);
		projectionMap.put(S.COLUMN_ITEMS_ICON_NAME, i + "." + S.COLUMN_ITEMS_ICON_NAME);
		projectionMap.put(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX, i + "." + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX);
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		 
		_QB.setTables(S.TABLE_HUNTING_FLEET + " AS h" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "h." +
				S.COLUMN_HUNTING_FLEET_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	
/********************************* HUNTING REWARD QUERIES ******************************************/
	
	/*
	 * Get all hunting reward monsters based on item
	 */
	public HuntingRewardCursor queryHuntingRewardItem(long id) {
		
		_Columns = null;
		_Selection = "h." + S.COLUMN_HUNTING_REWARDS_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new HuntingRewardCursor(wrapJoinHelper(builderHuntingReward()));
	}
	
	/*
	 * Get all hunting reward items based on monster
	 */
	public HuntingRewardCursor queryHuntingRewardMonster(long id) {
		
		_Columns = null;
		_Selection = "h." + S.COLUMN_HUNTING_REWARDS_MONSTER_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new HuntingRewardCursor(wrapJoinHelper(builderHuntingReward()));
	}
	
	/*
	 * Get all hunting reward items based on monster and rank
	 */
	public HuntingRewardCursor queryHuntingRewardMonsterRank(long id, String rank) {
		
		_Columns = null;
		_Selection = "h." + S.COLUMN_HUNTING_REWARDS_MONSTER_ID + " = ? " + "AND " + 
				"h." + S.COLUMN_HUNTING_REWARDS_RANK + " = ? ";
		_SelectionArgs = new String[]{"" + id, rank};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new HuntingRewardCursor(wrapJoinHelper(builderHuntingReward()));
	}
	
	/*
	 * Helper method to query for HuntingReward
	 */
	private SQLiteQueryBuilder builderHuntingReward() {
//		SELECT h._id AS _id, h.item_id, h.monster_id,
//		h.condition, h.rank, h.stack_size, h.percentage,
//		i.name AS iname, m.name AS mname
//		FROM hunting_rewards AS h
//		LEFT OUTER JOIN items AS i ON h.item_id = i._id
//		LEFT OUTER JOIN monsters AS m ON h.monster_id = m._id;

		String h = "h";
		String i = "i";
		String m = "m";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", h + "." + S.COLUMN_HUNTING_REWARDS_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_HUNTING_REWARDS_ITEM_ID, h + "." + S.COLUMN_HUNTING_REWARDS_ITEM_ID);
		projectionMap.put(S.COLUMN_HUNTING_REWARDS_MONSTER_ID, h + "." + S.COLUMN_HUNTING_REWARDS_MONSTER_ID);
		projectionMap.put(S.COLUMN_HUNTING_REWARDS_CONDITION, h + "." + S.COLUMN_HUNTING_REWARDS_CONDITION);
		projectionMap.put(S.COLUMN_HUNTING_REWARDS_RANK, h + "." + S.COLUMN_HUNTING_REWARDS_RANK);
		projectionMap.put(S.COLUMN_HUNTING_REWARDS_STACK_SIZE, h + "." + S.COLUMN_HUNTING_REWARDS_STACK_SIZE);
		projectionMap.put(S.COLUMN_HUNTING_REWARDS_PERCENTAGE, h + "." + S.COLUMN_HUNTING_REWARDS_PERCENTAGE);
		
		projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
		projectionMap.put(m + S.COLUMN_MONSTERS_NAME, m + "." + S.COLUMN_MONSTERS_NAME + " AS " + m + S.COLUMN_MONSTERS_NAME);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_HUNTING_REWARDS + " AS h" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "h." +
				S.COLUMN_HUNTING_REWARDS_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_MONSTERS +
				" AS m " + " ON " + "h." + S.COLUMN_HUNTING_REWARDS_MONSTER_ID + " = " + "m." + S.COLUMN_MONSTERS_ID);

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
		_Table = S.TABLE_ITEMS;
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
	
/********************************* ITEM TO SKILL TREE QUERIES ******************************************/
	
	/*
	 * Get all skills based on item
	 */
	public ItemToSkillTreeCursor queryItemToSkillTreeItem(long id) {
		
		_Columns = null;
		_Selection = "itst." + S.COLUMN_ITEM_TO_SKILL_TREE_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ItemToSkillTreeCursor(wrapJoinHelper(builderItemToSkillTree()));
	}
	
	/*
	 * Get all items based on skill tree
	 */
	public ItemToSkillTreeCursor queryItemToSkillTreeSkillTree(long id) {
		
		_Columns = null;
		_Selection = "itst." + S.COLUMN_ITEM_TO_SKILL_TREE_SKILL_TREE_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new ItemToSkillTreeCursor(wrapJoinHelper(builderItemToSkillTree()));
	}
	
	/*
	 * Helper method to query for ItemToSkillTree
	 */
	private SQLiteQueryBuilder builderItemToSkillTree() {
//		SELECT itst._id AS _id, itst.item_id, itst.skill_tree_id,
//		itst.point_value, i.name AS iname, s.name AS sname
//		FROM item_to_skill_tree AS itst
//		LEFT OUTER JOIN items AS i ON itst.item_id = i._id
//		LEFT OUTER JOIN skill_trees AS s ON itst.skill_tree_id = s._id;

		String itst = "itst";
		String i = "i";
		String s = "s";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", itst + "." + S.COLUMN_ITEM_TO_SKILL_TREE_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_ITEM_TO_SKILL_TREE_ITEM_ID, itst + "." + S.COLUMN_ITEM_TO_SKILL_TREE_ITEM_ID);
		projectionMap.put(S.COLUMN_ITEM_TO_SKILL_TREE_SKILL_TREE_ID, itst + "." + S.COLUMN_ITEM_TO_SKILL_TREE_SKILL_TREE_ID);
		projectionMap.put(S.COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE, itst + "." + S.COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE);
		
		projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
		projectionMap.put(s + S.COLUMN_SKILL_TREES_NAME, s + "." + S.COLUMN_SKILL_TREES_NAME + " AS " + s + S.COLUMN_SKILL_TREES_NAME);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_ITEM_TO_SKILL_TREE + " AS itst" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "itst." +
				S.COLUMN_ITEM_TO_SKILL_TREE_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_SKILL_TREES +
				" AS s " + " ON " + "itst." + S.COLUMN_ITEM_TO_SKILL_TREE_SKILL_TREE_ID + " = " + "s." + S.COLUMN_SKILL_TREES_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
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
	
/********************************* MOGA WOODS REWARD QUERIES ******************************************/
	
	/*
	 * Get all moga woods reward monsters based on item
	 */
	public MogaWoodsRewardCursor queryMogaWoodsRewardItem(long id) {
		
		_Columns = null;
		_Selection = "mwr." + S.COLUMN_MOGA_WOODS_REWARDS_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MogaWoodsRewardCursor(wrapJoinHelper(builderMogaWoodsReward()));
	}
	
	/*
	 * Get all moga woods reward items based on monster
	 */
	public MogaWoodsRewardCursor queryMogaWoodsRewardMonster(long id) {
		
		_Columns = null;
		_Selection = "mwr." + S.COLUMN_MOGA_WOODS_REWARDS_MONSTER_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MogaWoodsRewardCursor(wrapJoinHelper(builderMogaWoodsReward()));
	}
	
	/*
	 * Get all moga woods reward items based on monster and time
	 */
	public MogaWoodsRewardCursor queryMogaWoodsRewardMonsterTime(long id, String time) {
		
		_Columns = null;
		_Selection = "mwr." + S.COLUMN_MOGA_WOODS_REWARDS_MONSTER_ID + " = ? " + "AND " + 
				"mwr." + S.COLUMN_MOGA_WOODS_REWARDS_TIME + " = ? ";
		_SelectionArgs = new String[]{"" + id, time};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MogaWoodsRewardCursor(wrapJoinHelper(builderMogaWoodsReward()));
	}
	
	/*
	 * Helper method to query for MogaWoods
	 */
	private SQLiteQueryBuilder builderMogaWoodsReward() {
//		SELECT mwr._id AS _id, mwr.monster_id, mwr.item_id,
//		mwr.time, mwr.commodity_stars, mwr.kill_percentage,
//		mwr.capture_percentage, 
//		i.name AS iname, m.name AS mname 
//		FROM moga_woods_rewards AS mwr
//		LEFT OUTER JOIN monsters AS m ON mwr.monster_id = m._id 
//		LEFT OUTER JOIN items AS i ON mwr.item_id = i._id;

		String mwr = "mwr";
		String i = "i";
		String m = "m";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_MOGA_WOODS_REWARDS_ITEM_ID, mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_ITEM_ID);
		projectionMap.put(S.COLUMN_MOGA_WOODS_REWARDS_MONSTER_ID, mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_MONSTER_ID);
		projectionMap.put(S.COLUMN_MOGA_WOODS_REWARDS_TIME, mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_TIME);
		projectionMap.put(S.COLUMN_MOGA_WOODS_REWARDS_COMMODITY_STARS, mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_COMMODITY_STARS);
		projectionMap.put(S.COLUMN_MOGA_WOODS_REWARDS_KILL_PERCENTAGE, mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_KILL_PERCENTAGE);
		projectionMap.put(S.COLUMN_MOGA_WOODS_REWARDS_CAPTURE_PERCENTAGE, mwr + "." + S.COLUMN_MOGA_WOODS_REWARDS_CAPTURE_PERCENTAGE);
		
		projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
		projectionMap.put(m + S.COLUMN_MONSTERS_NAME, m + "." + S.COLUMN_MONSTERS_NAME + " AS " + m + S.COLUMN_MONSTERS_NAME);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_MOGA_WOODS_REWARDS + " AS mwr" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "mwr." +
				S.COLUMN_MOGA_WOODS_REWARDS_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_MONSTERS +
				" AS m " + " ON " + "mwr." + S.COLUMN_MOGA_WOODS_REWARDS_MONSTER_ID + " = " + "m." + S.COLUMN_MONSTERS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
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
		_Selection = S.COLUMN_MONSTERS_TRAIT + " = '' ";
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
		_Selection = S.COLUMN_MONSTERS_CLASS + " = ?" + " AND " + S.COLUMN_MONSTERS_TRAIT + " = '' ";
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
		_Selection = S.COLUMN_MONSTERS_CLASS + " = ?" + " AND " + S.COLUMN_MONSTERS_TRAIT + " = '' ";
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
	
	/*
	 * Get all traits from same monsters
	 */
	public MonsterCursor queryMonsterTrait(long id) {
		// "SELECT * FROM monsters WHERE _id = ? AND trait != ''"
		
		_Distinct = true;
		_Table = S.TABLE_MONSTERS;
		_Columns = null;
		_Selection = S.COLUMN_MONSTERS_ID + " = ?" + " AND " + S.COLUMN_MONSTERS_TRAIT + " != '' ";
		_SelectionArgs = new String[] {"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MonsterCursor(wrapHelper());
	}
	
/********************************* MONSTER DAMAGE QUERIES ******************************************/
	
	/*
	 * Get all monster damage for a monster
	 */
	public MonsterDamageCursor queryMonsterDamage(long id) {
		// "SELECT * FROM monster_damage WHERE monster_id = id"
		
		_Distinct = false;
		_Table = S.TABLE_MONSTER_DAMAGE;
		_Columns = null;
		_Selection = S.COLUMN_MONSTER_DAMAGE_MONSTER_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MonsterDamageCursor(wrapHelper());
	}	
	
/********************************* MONSTER TO QUEST QUERIES ******************************************/
	
	/*
	 * Get all quests based on monster
	 */
	public MonsterToQuestCursor queryMonsterToQuestMonster(long id) {
		
		_Columns = null;
		_Selection = "mtq." + S.COLUMN_MONSTER_TO_QUEST_MONSTER_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MonsterToQuestCursor(wrapJoinHelper(builderMonsterToQuest()));
	}
	
	/*
	 * Get all monsters based on quest
	 */
	public MonsterToQuestCursor queryMonsterToQuestQuest(long id) {
		
		_Columns = null;
		_Selection = "mtq." + S.COLUMN_MONSTER_TO_QUEST_QUEST_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new MonsterToQuestCursor(wrapJoinHelper(builderMonsterToQuest()));
	}
	
	/*
	 * Helper method to query for MonsterToQuest
	 */
	private SQLiteQueryBuilder builderMonsterToQuest() {
//		SELECT mtq._id AS _id, mtq.monster_id, mtq.quest_id,
//		mtq.unstable, m.name AS mname, q.name AS qname,
//		q.hub, q.stars
//		FROM monster_to_quest AS mtq
//		LEFT OUTER JOIN monsters AS m ON mtq.monster_id = m._id
//		LEFT OUTER JOIN quests AS q ON mtq.quest_id = q._id;

		String mtq = "mtq";
		String m = "m";
		String q = "q";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", mtq + "." + S.COLUMN_MONSTER_TO_QUEST_ID + " AS " + "_id");
		
		projectionMap.put(S.COLUMN_MONSTER_TO_QUEST_MONSTER_ID, mtq + "." + S.COLUMN_MONSTER_TO_QUEST_MONSTER_ID);
		projectionMap.put(S.COLUMN_MONSTER_TO_QUEST_QUEST_ID, mtq + "." + S.COLUMN_MONSTER_TO_QUEST_QUEST_ID);
		projectionMap.put(S.COLUMN_MONSTER_TO_QUEST_UNSTABLE, mtq + "." + S.COLUMN_MONSTER_TO_QUEST_UNSTABLE);
		
		projectionMap.put(m + S.COLUMN_MONSTERS_NAME, m + "." + S.COLUMN_MONSTERS_NAME + " AS " + m + S.COLUMN_MONSTERS_NAME);
		projectionMap.put(q + S.COLUMN_QUESTS_NAME, q + "." + S.COLUMN_QUESTS_NAME + " AS " + q + S.COLUMN_QUESTS_NAME);
		projectionMap.put(S.COLUMN_QUESTS_HUB, q + "." + S.COLUMN_QUESTS_HUB);
		projectionMap.put(S.COLUMN_QUESTS_STARS, q + "." + S.COLUMN_QUESTS_STARS);
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_MONSTER_TO_QUEST + " AS mtq" + " LEFT OUTER JOIN " + S.TABLE_MONSTERS + " AS m" + " ON " + "mtq." +
				S.COLUMN_MONSTER_TO_QUEST_MONSTER_ID + " = " + "m." + S.COLUMN_MONSTERS_ID + " LEFT OUTER JOIN " + S.TABLE_QUESTS +
				" AS q " + " ON " + "mtq." + S.COLUMN_MONSTER_TO_QUEST_QUEST_ID + " = " + "q." + S.COLUMN_QUESTS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
		
/********************************* QUEST QUERIES ******************************************/
	
	/*
	 * Get all quests
	 */
	public QuestCursor queryQuests() {

		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new QuestCursor(wrapJoinHelper(builderQuest()));
	}
	
	/*
	 * Get a specific quest
	 */
	public QuestCursor queryQuest(long id) {

		_Columns = null;
		_Selection = "q." + S.COLUMN_QUESTS_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new QuestCursor(wrapJoinHelper(builderQuest()));
	}	
	
	/*
	 * Get a specific quest based on hub
	 */
	public QuestCursor queryQuestHub(String hub) {

		_Columns = null;
		_Selection = "q." + S.COLUMN_QUESTS_HUB + " = ?";
		_SelectionArgs = new String[]{ hub };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new QuestCursor(wrapJoinHelper(builderQuest()));
	}
	
	/*
	 * Get a specific quest based on hub and stars
	 */
	public QuestCursor queryQuestHubStar(String hub, String stars) {

		_Columns = null;
		_Selection = "q." + S.COLUMN_QUESTS_HUB + " = ?" + " AND " +
					"q." + S.COLUMN_QUESTS_STARS + " = ?";
		_SelectionArgs = new String[]{ hub, stars };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new QuestCursor(wrapJoinHelper(builderQuest()));
	}

	/*
	 * Helper method to query for quests
	 */
	private SQLiteQueryBuilder builderQuest() {
//		SELECT q._id AS _id, q.name AS qname, q.goal, q.hub, q.type, q.stars, q.location_id, q.time_limit, 
//		q.fee, q.reward, q.hrp,	l.name AS lname, l.map
//		FROM quests AS q LEFT OUTER JOIN locations AS l ON q.location_id = l._id;

		String q = "q";
		String l = "l";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", q + "." + S.COLUMN_QUESTS_ID + " AS " + "_id");
		projectionMap.put(q + S.COLUMN_QUESTS_NAME, q + "." + S.COLUMN_QUESTS_NAME + " AS " + q + S.COLUMN_QUESTS_NAME);
		projectionMap.put(S.COLUMN_QUESTS_GOAL, q + "." + S.COLUMN_QUESTS_GOAL);
		projectionMap.put(S.COLUMN_QUESTS_HUB, q + "." + S.COLUMN_QUESTS_HUB);
		projectionMap.put(S.COLUMN_QUESTS_TYPE, q + "." + S.COLUMN_QUESTS_TYPE);
		projectionMap.put(S.COLUMN_QUESTS_STARS, q + "." + S.COLUMN_QUESTS_STARS);
		projectionMap.put(S.COLUMN_QUESTS_LOCATION_ID, q + "." + S.COLUMN_QUESTS_LOCATION_ID);
		projectionMap.put(S.COLUMN_QUESTS_TIME_LIMIT, q + "." + S.COLUMN_QUESTS_TIME_LIMIT);
		projectionMap.put(S.COLUMN_QUESTS_FEE, q + "." + S.COLUMN_QUESTS_FEE);
		projectionMap.put(S.COLUMN_QUESTS_REWARD, q + "." + S.COLUMN_QUESTS_REWARD);
		projectionMap.put(S.COLUMN_QUESTS_HRP, q + "." + S.COLUMN_QUESTS_HRP);
		
		projectionMap.put(l + S.COLUMN_LOCATIONS_NAME, l + "." + S.COLUMN_LOCATIONS_NAME + " AS " + l + S.COLUMN_LOCATIONS_NAME);
		projectionMap.put(S.COLUMN_LOCATIONS_MAP, l + "." + S.COLUMN_LOCATIONS_MAP);
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		 
		_QB.setTables(S.TABLE_QUESTS + " AS q" + " LEFT OUTER JOIN " + S.TABLE_LOCATIONS + " AS l" + " ON " + "q." +
				S.COLUMN_QUESTS_LOCATION_ID + " = " + "l." + S.COLUMN_LOCATIONS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	
/********************************* QUEST REWARD QUERIES ******************************************/
	
	/*
	 * Get all quest reward quests based on item
	 */
	public QuestRewardCursor queryQuestRewardItem(long id) {
		
		_Columns = null;
		_Selection = "qr." + S.COLUMN_QUEST_REWARDS_ITEM_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new QuestRewardCursor(wrapJoinHelper(builderQuestReward()));
	}
	
	/*
	 * Get all quest reward items based on quest
	 */
	public QuestRewardCursor queryQuestRewardQuest(long id) {
		
		_Columns = null;
		_Selection = "qr." + S.COLUMN_QUEST_REWARDS_QUEST_ID + " = ? ";
		_SelectionArgs = new String[]{"" + id};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new QuestRewardCursor(wrapJoinHelper(builderQuestReward()));
	}
	
	/*
	 * Helper method to query for QuestReward
	 */
	private SQLiteQueryBuilder builderQuestReward() {
//		SELECT qr._id AS _id, qr.quest_id, qr.item_id, 
//		qr.reward_slot, qr.percentage, qr.stack_size,
//		q.name AS qname, q.hub, q.stars, i.name AS iname
//		FROM quest_rewards AS qr
//		LEFT OUTER JOIN quests AS q ON qr.quest_id = q._id
//		LEFT OUTER JOIN items AS i ON qr.item_id = i._id;

		String qr = "qr";
		String i = "i";
		String q = "q";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", qr + "." + S.COLUMN_QUEST_REWARDS_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_QUEST_REWARDS_ITEM_ID, qr + "." + S.COLUMN_QUEST_REWARDS_ITEM_ID);
		projectionMap.put(S.COLUMN_QUEST_REWARDS_QUEST_ID, qr + "." + S.COLUMN_QUEST_REWARDS_QUEST_ID);
		projectionMap.put(S.COLUMN_QUEST_REWARDS_REWARD_SLOT, qr + "." + S.COLUMN_QUEST_REWARDS_REWARD_SLOT);
		projectionMap.put(S.COLUMN_QUEST_REWARDS_PERCENTAGE, qr + "." + S.COLUMN_QUEST_REWARDS_PERCENTAGE);
		projectionMap.put(S.COLUMN_QUEST_REWARDS_STACK_SIZE, qr + "." + S.COLUMN_QUEST_REWARDS_STACK_SIZE);
		
		projectionMap.put(i + S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME + " AS " + i + S.COLUMN_ITEMS_NAME);
		projectionMap.put(q + S.COLUMN_QUESTS_NAME, q + "." + S.COLUMN_QUESTS_NAME + " AS " + q + S.COLUMN_QUESTS_NAME);
		projectionMap.put(S.COLUMN_QUESTS_HUB, q + "." + S.COLUMN_QUESTS_HUB);
		projectionMap.put(S.COLUMN_QUESTS_STARS, q + "." + S.COLUMN_QUESTS_STARS);

		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_QUEST_REWARDS + " AS qr" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "qr." +
				S.COLUMN_QUEST_REWARDS_ITEM_ID + " = " + "i." + S.COLUMN_ITEMS_ID + " LEFT OUTER JOIN " + S.TABLE_QUESTS +
				" AS q " + " ON " + "qr." + S.COLUMN_QUEST_REWARDS_QUEST_ID + " = " + "q." + S.COLUMN_QUESTS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	
/********************************* SKILL QUERIES ******************************************/
	
	/*
	 * Get all skills for a skill tree
	 */
	public SkillCursor querySkill(long id) {
		// "SELECT * FROM skills WHERE skill_tree_id = id"
		
		_Distinct = false;
		_Table = S.TABLE_SKILLS;
		_Columns = null;
		_Selection = S.COLUMN_SKILLS_SKILL_TREE_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new SkillCursor(wrapHelper());
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
	
/********************************* WEAPON QUERIES ******************************************/
	
	/*
	 * Get all weapon
	 */
	public WeaponCursor queryWeapon() {

		_Columns = null;
		_Selection = null;
		_SelectionArgs = null;
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;

		return new WeaponCursor(wrapJoinHelper(builderWeapon()));
	}
	
	/*
	 * Get a specific weapon
	 */
	public WeaponCursor queryWeapon(long id) {

		_Columns = null;
		_Selection = "a." + S.COLUMN_WEAPONS_ID + " = ?";
		_SelectionArgs = new String[]{ String.valueOf(id) };
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = "1";
		
		return new WeaponCursor(wrapJoinHelper(builderWeapon()));
	}	
	
	/*
	 * Get a specific weapon based on weapon type
	 */
	public WeaponCursor queryWeaponType(String type) {

		_Columns = null;
		_Selection = "w." + S.COLUMN_WEAPONS_WTYPE + " = ? ";
		_SelectionArgs = new String[]{type};
		_GroupBy = null;
		_Having = null;
		_OrderBy = null;
		_Limit = null;
		
		return new WeaponCursor(wrapJoinHelper(builderWeapon()));
	}

	/*
	 * Helper method to query for weapon
	 */
	private SQLiteQueryBuilder builderWeapon() {
//		SELECT w._id AS _id, w.wtype, w.creation_cost, w.upgrade_cost, w.attack, w.max_attack,
//		w.elemental_attack, w.awakened_elemental_attack, w.defense, w.sharpness, w.affinity,
//		w.horn_notes, w.shelling_type, w.charge_levels, w.allowed_coatings, w.recoil, w.reload_speed,
//		w.rapid_fire, w.normal_shots, w.status_shots, w.elemental_shots, w.tool_shots, w.num_slots,
//		w.sharpness_file, 
//		i.name, i.jpn_name, i.type, i.rarity, i.carry_capacity, i.buy, i.sell, i.description,
//		i.icon_name, i.armor_dupe_name_fix
//		FROM weapons AS w LEFT OUTER JOIN	items AS i ON w._id = i._id;

		String w = "w";
		String i = "i";
		
		HashMap<String, String> projectionMap = new HashMap<String, String>();
		
		projectionMap.put("_id", w + "." + S.COLUMN_WEAPONS_ID + " AS " + "_id");
		projectionMap.put(S.COLUMN_WEAPONS_WTYPE, w + "." + S.COLUMN_WEAPONS_WTYPE);
		projectionMap.put(S.COLUMN_WEAPONS_CREATION_COST, w + "." + S.COLUMN_WEAPONS_CREATION_COST);
		projectionMap.put(S.COLUMN_WEAPONS_UPGRADE_COST, w + "." + S.COLUMN_WEAPONS_UPGRADE_COST);
		projectionMap.put(S.COLUMN_WEAPONS_ATTACK, w + "." + S.COLUMN_WEAPONS_ATTACK);
		projectionMap.put(S.COLUMN_WEAPONS_MAX_ATTACK, w + "." + S.COLUMN_WEAPONS_MAX_ATTACK);
		projectionMap.put(S.COLUMN_WEAPONS_ELEMENTAL_ATTACK, w + "." + S.COLUMN_WEAPONS_ELEMENTAL_ATTACK);
		projectionMap.put(S.COLUMN_WEAPONS_AWAKENED_ELEMENTAL_ATTACK, w + "." + S.COLUMN_WEAPONS_AWAKENED_ELEMENTAL_ATTACK);
		projectionMap.put(S.COLUMN_WEAPONS_DEFENSE, w + "." + S.COLUMN_WEAPONS_DEFENSE);
		projectionMap.put(S.COLUMN_WEAPONS_SHARPNESS, w + "." + S.COLUMN_WEAPONS_SHARPNESS);
		projectionMap.put(S.COLUMN_WEAPONS_AFFINITY, w + "." + S.COLUMN_WEAPONS_AFFINITY);
		projectionMap.put(S.COLUMN_WEAPONS_HORN_NOTES, w + "." + S.COLUMN_WEAPONS_HORN_NOTES);
		projectionMap.put(S.COLUMN_WEAPONS_SHELLING_TYPE, w + "." + S.COLUMN_WEAPONS_SHELLING_TYPE);
		projectionMap.put(S.COLUMN_WEAPONS_PHIAL, w + "." + S.COLUMN_WEAPONS_PHIAL);
		projectionMap.put(S.COLUMN_WEAPONS_CHARGES, w + "." + S.COLUMN_WEAPONS_CHARGES);
		projectionMap.put(S.COLUMN_WEAPONS_COATINGS, w + "." + S.COLUMN_WEAPONS_COATINGS);
		projectionMap.put(S.COLUMN_WEAPONS_RECOIL, w + "." + S.COLUMN_WEAPONS_RECOIL);
		projectionMap.put(S.COLUMN_WEAPONS_RELOAD_SPEED, w + "." + S.COLUMN_WEAPONS_RELOAD_SPEED);
		projectionMap.put(S.COLUMN_WEAPONS_RAPID_FIRE, w + "." + S.COLUMN_WEAPONS_RAPID_FIRE);
		projectionMap.put(S.COLUMN_WEAPONS_DEVIATION, w + "." + S.COLUMN_WEAPONS_DEVIATION);
		projectionMap.put(S.COLUMN_WEAPONS_AMMO, w + "." + S.COLUMN_WEAPONS_AMMO);
		projectionMap.put(S.COLUMN_WEAPONS_NUM_SLOTS, w + "." + S.COLUMN_WEAPONS_NUM_SLOTS);
		projectionMap.put(S.COLUMN_WEAPONS_SHARPNESS_FILE, w + "." + S.COLUMN_WEAPONS_SHARPNESS_FILE);

		projectionMap.put(S.COLUMN_ITEMS_NAME, i + "." + S.COLUMN_ITEMS_NAME);
		projectionMap.put(S.COLUMN_ITEMS_JPN_NAME, i + "." + S.COLUMN_ITEMS_JPN_NAME);
		projectionMap.put(S.COLUMN_ITEMS_TYPE, i + "." + S.COLUMN_ITEMS_TYPE);
		projectionMap.put(S.COLUMN_ITEMS_RARITY, i + "." + S.COLUMN_ITEMS_RARITY);
		projectionMap.put(S.COLUMN_ITEMS_CARRY_CAPACITY, i + "." + S.COLUMN_ITEMS_CARRY_CAPACITY);
		projectionMap.put(S.COLUMN_ITEMS_BUY, i + "." + S.COLUMN_ITEMS_BUY);
		projectionMap.put(S.COLUMN_ITEMS_SELL, i + "." + S.COLUMN_ITEMS_SELL);
		projectionMap.put(S.COLUMN_ITEMS_DESCRIPTION, i + "." + S.COLUMN_ITEMS_DESCRIPTION);
		projectionMap.put(S.COLUMN_ITEMS_ICON_NAME, i + "." + S.COLUMN_ITEMS_ICON_NAME);
		projectionMap.put(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX, i + "." + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX);
		
		//Create new querybuilder
		SQLiteQueryBuilder _QB = new SQLiteQueryBuilder();
		
		_QB.setTables(S.TABLE_WEAPONS + " AS w" + " LEFT OUTER JOIN " + S.TABLE_ITEMS + " AS i" + " ON " + "w." +
				S.COLUMN_WEAPONS_ID + " = " + "i." + S.COLUMN_ITEMS_ID);

		_QB.setProjectionMap(projectionMap);
		return _QB;
	}
	

}
