package com.daviancorp.android.data.database;

import java.util.ArrayList;

import android.content.Context;

import com.daviancorp.android.data.object.*;

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
	
	public ArrayList<Armor> queryArmorArrayType(String type) {
		ArrayList<Armor> armors = new ArrayList<Armor>();
		ArmorCursor cursor = mHelper.queryArmorType(type);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			armors.add(cursor.getArmor());
			cursor.moveToNext();
		}
		cursor.close();
		return armors;
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
	
/********************************* CARVE QUERIES ******************************************/
	public CarveCursor queryCarveItem(long id) {
		return mHelper.queryCarveItem(id);
	}

	public CarveCursor queryCarveMonster(long id) {
		return mHelper.queryCarveMonster(id);
	}
	
	public CarveCursor queryCarveMonsterRank(long id, String rank) {
		return mHelper.queryCarveMonsterRank(id, rank);
	}
	
	public ArrayList<Carve> queryCarveArrayItem(long id) {
		ArrayList<Carve> carves = new ArrayList<Carve>();
		CarveCursor cursor = mHelper.queryCarveItem(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			carves.add(cursor.getCarve());
			cursor.moveToNext();
		}
		cursor.close();
		return carves;
	}
	
	public ArrayList<Carve> queryCarveArrayMonster(long id) {
		ArrayList<Carve> carves = new ArrayList<Carve>();
		CarveCursor cursor = mHelper.queryCarveMonster(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			carves.add(cursor.getCarve());
			cursor.moveToNext();
		}
		cursor.close();
		return carves;
	}
	
	public ArrayList<Carve> queryCarveArrayMonsterRank(long id, String rank) {
		ArrayList<Carve> carves = new ArrayList<Carve>();
		CarveCursor cursor = mHelper.queryCarveMonsterRank(id, rank);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			carves.add(cursor.getCarve());
			cursor.moveToNext();
		}
		cursor.close();
		return carves;
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
	
/********************************* COMPONENT QUERIES ******************************************/
	public ComponentCursor queryComponentCreated(long id) {
		return mHelper.queryComponentCreated(id);
	}

	public ComponentCursor queryComponentComponent(long id) {
		return mHelper.queryComponentComponent(id);
	}

	
	public ArrayList<Component> queryComponentArrayCreated(long id) {
		ArrayList<Component> components = new ArrayList<Component>();
		ComponentCursor cursor = mHelper.queryComponentCreated(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			components.add(cursor.getComponent());
			cursor.moveToNext();
		}
		cursor.close();
		return components;
	}
	
	public ArrayList<Component> queryComponentArrayComponent(long id) {
		ArrayList<Component> components = new ArrayList<Component>();
		ComponentCursor cursor = mHelper.queryComponentComponent(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			components.add(cursor.getComponent());
			cursor.moveToNext();
		}
		cursor.close();
		return components;
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
	
/********************************* HUNTING REWARD QUERIES ******************************************/
	public HuntingRewardCursor queryHuntingRewardItem(long id) {
		return mHelper.queryHuntingRewardItem(id);
	}

	public HuntingRewardCursor queryHuntingRewardMonster(long id) {
		return mHelper.queryHuntingRewardMonster(id);
	}
	
	public HuntingRewardCursor queryHuntingRewardMonsterRank(long id, String rank) {
		return mHelper.queryHuntingRewardMonsterRank(id, rank);
	}
	
	public ArrayList<HuntingReward> queryHuntingRewardArrayItem(long id) {
		ArrayList<HuntingReward> rewards = new ArrayList<HuntingReward>();
		HuntingRewardCursor cursor = mHelper.queryHuntingRewardItem(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getHuntingReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
	public ArrayList<HuntingReward> queryHuntingRewardArrayMonster(long id) {
		ArrayList<HuntingReward> rewards = new ArrayList<HuntingReward>();
		HuntingRewardCursor cursor = mHelper.queryHuntingRewardMonster(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getHuntingReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
	public ArrayList<HuntingReward> queryHuntingRewardArrayMonsterRank(long id, String rank) {
		ArrayList<HuntingReward> rewards = new ArrayList<HuntingReward>();
		HuntingRewardCursor cursor = mHelper.queryHuntingRewardMonsterRank(id, rank);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getHuntingReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
/********************************* GATHERING QUERIES ******************************************/
	public GatheringCursor queryGatheringItem(long id) {
		return mHelper.queryGatheringItem(id);
	}

	public GatheringCursor queryGatheringLocation(long id) {
		return mHelper.queryGatheringLocation(id);
	}
	
	public GatheringCursor queryGatheringLocationRank(long id, String rank) {
		return mHelper.queryGatheringLocationRank(id, rank);
	}
	
	public ArrayList<Gathering> queryGatheringArrayItem(long id) {
		ArrayList<Gathering> gatherings = new ArrayList<Gathering>();
		GatheringCursor cursor = mHelper.queryGatheringItem(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			gatherings.add(cursor.getGathering());
			cursor.moveToNext();
		}
		cursor.close();
		return gatherings;
	}
	
	public ArrayList<Gathering> queryGatheringArrayLocation(long id) {
		ArrayList<Gathering> gatherings = new ArrayList<Gathering>();
		GatheringCursor cursor = mHelper.queryGatheringLocation(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			gatherings.add(cursor.getGathering());
			cursor.moveToNext();
		}
		cursor.close();
		return gatherings;
	}
	
	public ArrayList<Gathering> queryGatheringArrayLocationRank(long id, String rank) {
		ArrayList<Gathering> gatherings = new ArrayList<Gathering>();
		GatheringCursor cursor = mHelper.queryGatheringLocationRank(id, rank);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			gatherings.add(cursor.getGathering());
			cursor.moveToNext();
		}
		cursor.close();
		return gatherings;
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
	
/********************************* ITEM TO SKILL TREE QUERIES ******************************************/
	public ItemToSkillTreeCursor queryItemToSkillTreeItem(long id) {
		return mHelper.queryItemToSkillTreeItem(id);
	}

	public ItemToSkillTreeCursor queryItemToSkillTreeSkillTree(long id) {
		return mHelper.queryItemToSkillTreeSkillTree(id);
	}
	
	public ArrayList<ItemToSkillTree> queryItemToSkillTreeArrayItem(long id) {
		ArrayList<ItemToSkillTree> itst = new ArrayList<ItemToSkillTree>();
		ItemToSkillTreeCursor cursor = mHelper.queryItemToSkillTreeItem(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			itst.add(cursor.getItemToSkillTree());
			cursor.moveToNext();
		}
		cursor.close();
		return itst;
	}
	
	public ArrayList<ItemToSkillTree> queryItemToSkillTreeArraySkillTree(long id) {
		ArrayList<ItemToSkillTree> itst = new ArrayList<ItemToSkillTree>();
		ItemToSkillTreeCursor cursor = mHelper.queryItemToSkillTreeSkillTree(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			itst.add(cursor.getItemToSkillTree());
			cursor.moveToNext();
		}
		cursor.close();
		return itst;
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
	
/********************************* MOGA WOODS REWARD QUERIES ******************************************/
	public MogaWoodsRewardCursor queryMogaWoodsRewardItem(long id) {
		return mHelper.queryMogaWoodsRewardItem(id);
	}

	public MogaWoodsRewardCursor queryMogaWoodsRewardMonster(long id) {
		return mHelper.queryMogaWoodsRewardMonster(id);
	}
	
	public MogaWoodsRewardCursor queryMogaWoodsRewardMonsterTime(long id, String time) {
		return mHelper.queryMogaWoodsRewardMonsterTime(id, time);
	}
	
	public ArrayList<MogaWoodsReward> queryMogaWoodsRewardArrayItem(long id) {
		ArrayList<MogaWoodsReward> rewards = new ArrayList<MogaWoodsReward>();
		MogaWoodsRewardCursor cursor = mHelper.queryMogaWoodsRewardItem(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getMogaWoodsReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
	public ArrayList<MogaWoodsReward> queryMogaWoodsRewardArrayMonster(long id) {
		ArrayList<MogaWoodsReward> rewards = new ArrayList<MogaWoodsReward>();
		MogaWoodsRewardCursor cursor = mHelper.queryMogaWoodsRewardMonster(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getMogaWoodsReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
	public ArrayList<MogaWoodsReward> queryHuntingRewardArrayMonsterTime(long id, String time) {
		ArrayList<MogaWoodsReward> rewards = new ArrayList<MogaWoodsReward>();
		MogaWoodsRewardCursor cursor = mHelper.queryMogaWoodsRewardMonsterTime(id, time);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getMogaWoodsReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
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
	
	public ArrayList<Monster> getMonsterTraitArray(long id) {
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		MonsterCursor cursor = mHelper.queryMonsterTrait(id);
		cursor.moveToFirst();
		
		if (!cursor.isAfterLast())
			monsters.add(cursor.getMonster());
		cursor.close();
		return monsters;
	}
	
/********************************* MONSTER DAMAGE QUERIES ******************************************/	
	
	public MonsterDamageCursor queryMonsterDamage(long id) {
		return mHelper.queryMonsterDamage(id);
	}
	
	public ArrayList<MonsterDamage> queryMonsterDamageArray(long id) {
		ArrayList<MonsterDamage> damages = new ArrayList<MonsterDamage>();
		MonsterDamageCursor cursor = mHelper.queryMonsterDamage(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			damages.add(cursor.getMonsterDamage());
			cursor.moveToNext();
		}
		cursor.close();
		return damages;
	}	
	
/********************************* MONSTER TO QUEST QUERIES ******************************************/
	public MonsterToQuestCursor queryMonsterToQuestMonster(long id) {
		return mHelper.queryMonsterToQuestMonster(id);
	}

	public MonsterToQuestCursor queryMonsterToQuestQuest(long id) {
		return mHelper.queryMonsterToQuestQuest(id);
	}
	
	public ArrayList<MonsterToQuest> queryMonsterToQuestArrayMonster(long id) {
		ArrayList<MonsterToQuest> mtq = new ArrayList<MonsterToQuest>();
		MonsterToQuestCursor cursor = mHelper.queryMonsterToQuestMonster(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			mtq.add(cursor.getMonsterToQuest());
			cursor.moveToNext();
		}
		cursor.close();
		return mtq;
	}
	
	public ArrayList<MonsterToQuest> queryMonsterToQuestArrayQuest(long id) {
		ArrayList<MonsterToQuest> mtq = new ArrayList<MonsterToQuest>();
		MonsterToQuestCursor cursor = mHelper.queryMonsterToQuestQuest(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			mtq.add(cursor.getMonsterToQuest());
			cursor.moveToNext();
		}
		cursor.close();
		return mtq;
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
	
/********************************* QUEST REWARD QUERIES ******************************************/
	public QuestRewardCursor queryQuestRewardItem(long id) {
		return mHelper.queryQuestRewardItem(id);
	}

	public QuestRewardCursor queryQuestRewardQuest(long id) {
		return mHelper.queryQuestRewardQuest(id);
	}
	
	public ArrayList<QuestReward> queryQuestRewardArrayItem(long id) {
		ArrayList<QuestReward> rewards = new ArrayList<QuestReward>();
		QuestRewardCursor cursor = mHelper.queryQuestRewardItem(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getQuestReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
	public ArrayList<QuestReward> queryQuestRewardArrayQuest(long id) {
		ArrayList<QuestReward> rewards = new ArrayList<QuestReward>();
		QuestRewardCursor cursor = mHelper.queryQuestRewardQuest(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			rewards.add(cursor.getQuestReward());
			cursor.moveToNext();
		}
		cursor.close();
		return rewards;
	}
	
/********************************* SKILL QUERIES ******************************************/	
	
	public SkillCursor querySkill(long id) {
		return mHelper.querySkill(id);
	}
	
	public ArrayList<Skill> querySkillArray(long id) {
		ArrayList<Skill> skills = new ArrayList<Skill>();
		SkillCursor cursor = mHelper.querySkill(id);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			skills.add(cursor.getSkill());
			cursor.moveToNext();
		}
		cursor.close();
		return skills;
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
