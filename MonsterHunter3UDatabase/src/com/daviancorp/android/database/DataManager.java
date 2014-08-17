package com.daviancorp.android.database;

import android.content.Context;
import android.util.Log;

import com.daviancorp.android.data.Combining;
import com.daviancorp.android.data.Decoration;
import com.daviancorp.android.data.Item;
import com.daviancorp.android.data.Location;
import com.daviancorp.android.data.Monster;
import com.daviancorp.android.data.Quest;
import com.daviancorp.android.data.SkillTree;

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
	
/********************************* COMBINING QUERIES ******************************************/
	public CombiningCursor queryCombinings() {
		return mHelper.queryCombinings();
	}
	
	public Combining getCombining(long id) {
		Combining combining = null;
		CombiningCursor cursor = mHelper.queryCombining(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			combining = cursor.getCombining();
		cursor.close();
		return combining;
	}
	
	
	
/********************************* DECORATION QUERIES ******************************************/
	public DecorationCursor queryDecorations() {
		return mHelper.queryDecorations();
	}
	
	public Decoration getDecoration(long id) {
		Decoration decoration = null;
		DecorationCursor cursor = mHelper.queryDecoration(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			decoration = cursor.getDecoration();
		cursor.close();
		return decoration;
	}
	
/********************************* ITEM QUERIES ******************************************/
	public ItemCursor queryItems() {
		return mHelper.queryItems();
	}
	
	public Item getItem(long id) {
		Item item = null;
		ItemCursor cursor = mHelper.queryItem(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			item = cursor.getItem();
		cursor.close();
		return item;
	}
	
/********************************* LOCATION QUERIES ******************************************/
	
	public LocationCursor queryLocations() {
		return mHelper.queryLocations();
	}
	
	public Location getLocation(long id) {
		Location location = null;
		LocationCursor cursor = mHelper.queryLocation(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			location = cursor.getLocation();
		cursor.close();
		return location;
	}
	
/********************************* MONSTER QUERIES ******************************************/
	
	public MonsterCursor queryMonsters() {
		return mHelper.queryMonsters();
	}
	
	public MonsterCursor querySmallMonsters() {
		return mHelper.querySmallMonsters();
	}
	
	public MonsterCursor queryLargeMonsters() {
		return mHelper.queryLargeMonsters();
	}
	
	public Monster getMonster(long id) {
		Monster monster = null;
		MonsterCursor cursor = mHelper.queryMonster(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			monster = cursor.getMonster();
		cursor.close();
		return monster;
	}
	
/********************************* QUEST QUERIES ******************************************/	
	
	public QuestCursor queryQuests() {
		return mHelper.queryQuests();
	}
	
	public Quest getQuest(long id) {
		Quest quest = null;
		QuestCursor cursor = mHelper.queryQuest(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			quest = cursor.getQuest();
		cursor.close();
		return quest;
	}
	
/********************************* SKILL TREE QUERIES ******************************************/	
	
	public SkillTreeCursor querySkillTrees() {
		return mHelper.querySkillTrees();
	}
	
	public SkillTree getSkillTree(long id) {
		SkillTree skillTree = null;
		SkillTreeCursor cursor = mHelper.querySkillTree(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			skillTree = cursor.getSkillTree();
		cursor.close();
		return skillTree;
	}
	
}
