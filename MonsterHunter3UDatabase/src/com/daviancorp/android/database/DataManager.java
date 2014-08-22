package com.daviancorp.android.database;

import java.util.ArrayList;

import android.content.Context;

import com.daviancorp.android.data.*;

public class DataManager {
	private static final String TAG = "DataManager";

	
	private static DataManager sDataManager;
	private Context mAppContext;
	private MonsterHunterDatabaseHelper mHelper;
	
	private DataManager(Context appContext) {
		mAppContext = appContext;
		mHelper = new MonsterHunterDatabaseHelper(mAppContext);
	}
	
	public static DataManager get(Context c) {
		if (sDataManager == null) {
			// Use the application context to avoid leaking activities
			sDataManager = new DataManager(c.getApplicationContext());
		}
		return sDataManager;
	}
	
/********************************* ARMOR QUERIES ******************************************/	
	
	public ArmorCursor queryArmor() {
		return mHelper.queryArmor();
	}
	
	public Armor getArmor(long id) {
		Armor armor = null;
		ArmorCursor cursor = mHelper.queryArmor(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			armor = cursor.getArmor();
		cursor.close();
		return armor;
	}
	
	public ArmorCursor queryArmorType(String type) {
		return mHelper.queryArmorType(type);
	}

	public ArmorCursor queryArmorSlot(String slot) {
		return mHelper.queryArmorSlot(slot);
	}

	public ArmorCursor queryArmorTypeSlot(String type, String slot) {
		return mHelper.queryArmorTypeSlot(type, slot);
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
	
/********************************* HUNTING FLEET QUERIES ******************************************/	
	
	public HuntingFleetCursor queryHuntingFleets() {
		return mHelper.queryHuntingFleets();
	}
	
	public HuntingFleet getHuntingFleet(long id) {
		HuntingFleet huntingFleet = null;
		HuntingFleetCursor cursor = mHelper.queryHuntingFleet(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			huntingFleet = cursor.getHuntingFleet();
		cursor.close();
		return huntingFleet;
	}
	
	public HuntingFleetCursor queryHuntingFleetType(String type) {
		return mHelper.queryHuntingFleetType(type);
	}

	public HuntingFleetCursor queryHuntingFleetLocation(String location) {
		return mHelper.queryHuntingFleetLocation(location);
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
	
	public ArrayList<Quest> queryQuestArrayHub(String hub) {
		ArrayList<Quest> quests = new ArrayList<Quest>();
		QuestCursor cursor = mHelper.queryQuestHub(hub);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			quests.add(cursor.getQuest());
			cursor.moveToNext();
		}
		cursor.close();
		return quests;
	}
	
	public QuestCursor queryQuestHub(String hub) {
		return mHelper.queryQuestHub(hub);
	}

	public QuestCursor queryQuestHubStar(String hub, String stars) {
		return mHelper.queryQuestHubStar(hub, stars);
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
	
	
/********************************* WEAPON QUERIES ******************************************/	
	
	public WeaponCursor queryWeapon() {
		return mHelper.queryWeapon();
	}
	
	public Weapon getWeapon(long id) {
		Weapon weapon = null;
		WeaponCursor cursor = mHelper.queryWeapon(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			weapon = cursor.getWeapon();
		cursor.close();
		return weapon;
	}
	
	public WeaponCursor queryWeaponType(String type) {
		return mHelper.queryWeaponType(type);
	}
		
}
