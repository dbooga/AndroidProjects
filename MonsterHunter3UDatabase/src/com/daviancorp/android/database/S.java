package com.daviancorp.android.database;


public class S {
	
	// Armor
	static final String TABLE_ARMOR = "armor";
	static final String COLUMN_ARMOR_ID = "_id";
	static final String COLUMN_ARMOR_SLOT = "slot";
	static final String COLUMN_ARMOR_DEFENSE = "defense";
	static final String COLUMN_ARMOR_MAX_DEFENSE = "max_defense";
	static final String COLUMN_ARMOR_FIRE_RES = "fire_res";
	static final String COLUMN_ARMOR_THUNDER_RES = "thunder_res";
	static final String COLUMN_ARMOR_DRAGON_RES = "dragon_res";
	static final String COLUMN_ARMOR_WATER_RES = "water_res";
	static final String COLUMN_ARMOR_ICE_RES = "ice_res";
	static final String COLUMN_ARMOR_GENDER = "gender";
	static final String COLUMN_ARMOR_HUNTER_TYPE = "hunter_type";
	static final String COLUMN_ARMOR_NUM_SLOTS = "num_slots";
	
	// Carves
	static final String TABLE_CARVES = "carves";
	static final String COLUMN_CARVES_ID = "_id";
	static final String COLUMN_CARVES_ITEM_ID = "item_id";
	static final String COLUMN_CARVES_MONSTER_ID = "monster_id";
	static final String COLUMN_CARVES_RANK = "rank";
	static final String COLUMN_CARVES_LOCATION = "location";
	static final String COLUMN_CARVES_NUM_CARVES = "num_carves";
	static final String COLUMN_CARVES_PERCENTAGE = "percentage";
	
	// Charms
	static final String TABLE_CHARMS = "charms";
	static final String COLUMN_CHARMS_ID = "_id";
	static final String COLUMN_CHARMS_NUM_SLOTS = "num_slots";
	static final String COLUMN_CHARMS_SKILL_TREE_1_ID = "skill_tree_1_id";
	static final String COLUMN_CHARMS_SKILL_TREE_1_AMOUNT = "skill_tree_1_amount";
	static final String COLUMN_CHARMS_SKILL_TREE_2_ID = "skill_tree_2_id";
	static final String COLUMN_CHARMS_SKILL_TREE_2_AMOUNT = "skill_tree_2_amount";
	
	// Combining
	static final String TABLE_COMBINING = "combining";
	static final String COLUMN_COMBINING_ID = "_id";
	static final String COLUMN_COMBINING_CREATED_ITEM_ID = "created_item_id";
	static final String COLUMN_COMBINING_ITEM_1_ID = "item_1_id";
	static final String COLUMN_COMBINING_ITEM_2_ID = "item_2_id";
	static final String COLUMN_COMBINING_AMOUNT_MADE_MIN = "amount_made_min";
	static final String COLUMN_COMBINING_AMOUNT_MADE_MAX = "amount_made_max";
	static final String COLUMN_COMBINING_PERCENTAGE = "percentage";
	
	// Components
	static final String TABLE_COMPONENTS = "components";
	static final String COLUMN_COMPONENTS_ID = "_id";
	static final String COLUMN_COMPONENTS_CREATED_ITEM_ID = "created_item_id";
	static final String COLUMN_COMPONENTS_COMPONENT_ITEM_ID = "component_item_id";
	static final String COLUMN_COMPONENTS_QUANTITY = "quantity";
	static final String COLUMN_COMPONENTS_TYPE = "type";
	
	// Decorations
	static final String TABLE_DECORATIONS = "decorations";
	static final String COLUMN_DECORATIONS_ID = "_id";
	static final String COLUMN_DECORATIONS_NUM_SLOTS = "num_slots";
	
	// Gathering
	static final String TABLE_GATHERING = "gathering";
	static final String COLUMN_GATHERING_ID = "_id";
	static final String COLUMN_GATHERING_ITEM_ID = "item_id";
	static final String COLUMN_GATHERING_LOCATION_ID = "location_id";
	static final String COLUMN_GATHERING_AREA = "area";
	static final String COLUMN_GATHERING_SITE = "site";
	static final String COLUMN_GATHERING_SITE_SET = "site_set";
	static final String COLUMN_GATHERING_SITE_SET_PERCENTAGE = "site_set_percentage";
	static final String COLUMN_GATHERING_SITE_SET_GATHERS_MIN = "site_set_gathers_min";
	static final String COLUMN_GATHERING_SITE_SET_GATHERS_MAX = "site_set_gathers_max";
	static final String COLUMN_GATHERING_RANK = "rank";
	static final String COLUMN_GATHERING_PERCENTAGE = "percentage";
	
	// Hunting Fleet
	static final String TABLE_HUNTING_FLEET = "hunting_fleet";
	static final String COLUMN_HUNTING_FLEET_ID = "_id";
	static final String COLUMN_HUNTING_FLEET_TYPE = "type";
	static final String COLUMN_HUNTING_FLEET_LEVEL = "level";
	static final String COLUMN_HUNTING_FLEET_LOCATION = "location";
	static final String COLUMN_HUNTING_FLEET_ITEM_ID = "item_id";
	static final String COLUMN_HUNTING_FLEET_AMOUNT = "amount";
	static final String COLUMN_HUNTING_FLEET_PERCENTAGE = "percentage";
	static final String COLUMN_HUNTING_FLEET_RANK = "rank";
	
	// Hunting Rewards
	static final String TABLE_HUNTING_REWARDS = "hunting_rewards";
	static final String COLUMN_HUNTING_REWARDS_ID = "_id";
	static final String COLUMN_HUNTING_REWARDS_ITEM_ID = "item_id";
	static final String COLUMN_HUNTING_REWARDS_CONDITION = "condition";
	static final String COLUMN_HUNTING_REWARDS_MONSTER_ID = "monster_id";
	static final String COLUMN_HUNTING_REWARDS_RANK = "rank";
	static final String COLUMN_HUNTING_REWARDS_STACK_SIZE = "stack_size";
	static final String COLUMN_HUNTING_REWARDS_PERCENTAGE = "percentage";
	
	// Items
	static final String TABLE_ITEMS = "items";
	static final String COLUMN_ITEMS_ID = "_id";
	static final String COLUMN_ITEMS_NAME = "name";
	static final String COLUMN_ITEMS_JPN_NAME = "jpn_name";
	static final String COLUMN_ITEMS_TYPE = "type";
	static final String COLUMN_ITEMS_RARITY = "rarity";
	static final String COLUMN_ITEMS_CARRY_CAPACITY = "carry_capacity";
	static final String COLUMN_ITEMS_BUY = "buy";
	static final String COLUMN_ITEMS_SELL = "sell";
	static final String COLUMN_ITEMS_DESCRIPTION = "description";
	static final String COLUMN_ITEMS_ICON_NAME = "icon_name";
	static final String COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX = "armor_dupe_name_fix";
	
	// Item to Skill Tree
	static final String TABLE_ITEM_TO_SKILL_TREE = "item_to_skill_tree";
	static final String COLUMN_ITEM_TO_SKILL_TREE_ID = "_id";
	static final String COLUMN_ITEM_TO_SKILL_TREE_ITEM_ID = "item_id";
	static final String COLUMN_ITEM_TO_SKILL_TREE_SKILL_TREE_ID = "skill_tree_id";
	static final String COLUMN_ITEM_TO_SKILL_TREE_POINT_VALUE = "point_value";
	
	// Locations
	static final String TABLE_LOCATIONS = "locations";
	static final String COLUMN_LOCATIONS_ID = "_id";
	static final String COLUMN_LOCATIONS_NAME = "name";
	static final String COLUMN_LOCATIONS_MAP = "map";
	
	// Moga Woods Rewards
	static final String TABLE_MOGA_WOODS_REWARDS = "moga_woods_rewards";
	static final String COLUMN_MOGA_WOODS_REWARDS_ID = "_id";
	static final String COLUMN_MOGA_WOODS_REWARDS_MONSTER_ID = "monster_id";
	static final String COLUMN_MOGA_WOODS_REWARDS_TIME = "time";
	static final String COLUMN_MOGA_WOODS_REWARDS_ITEM_ID = "item_id";
	static final String COLUMN_MOGA_WOODS_REWARDS_COMMODITY_STARS = "commodity_stars";
	static final String COLUMN_MOGA_WOODS_REWARDS_KILL_PERCENTAGE = "kill_percentage";
	static final String COLUMN_MOGA_WOODS_REWARDS_CAPTURE_PERCENTAGE = "capture_percentage";
	
	// Monster
	static final String TABLE_MONSTERS = "monsters";
	static final String COLUMN_MONSTERS_ID = "_id";
	static final String COLUMN_MONSTERS_NAME = "name";
	static final String COLUMN_MONSTERS_CLASS = "class";
	static final String COLUMN_MONSTERS_TRAIT = "trait";
	static final String COLUMN_MONSTERS_FILE_LOCATION = "icon_name"; 
	
	// Monster to Quest
	static final String TABLE_MONSTER_TO_QUEST = "monster_to_quest";
	static final String COLUMN_MONSTER_TO_QUEST_ID = "_id";
	static final String COLUMN_MONSTER_TO_QUEST_MONSTER_ID = "monster_id";
	static final String COLUMN_MONSTER_TO_QUEST_QUEST_ID = "quest_id";
	static final String COLUMN_MONSTER_TO_QUEST_UNSTABLE = "unstable"; 
	
	// Planting
	static final String TABLE_PLANTING = "planting";
	static final String COLUMN_PLANTING_ID = "_id";
	static final String COLUMN_PLANTING_PLANTED_ITEM_ID = "planted_item_id";
	static final String COLUMN_PLANTING_RECEIVED_ITEM_ID = "received_item_id";
	static final String COLUMN_PLANTING_STACK_SIZE = "stack_size";
	static final String COLUMN_PLANTING_PERCENTAGE = "percentage";
	static final String COLUMN_PLANTING_POOL_TYPE = "pool_type";
	
	// Quests
	static final String TABLE_QUESTS = "quests";
	static final String COLUMN_QUESTS_ID = "_id";
	static final String COLUMN_QUESTS_NAME = "name";
	static final String COLUMN_QUESTS_GOAL = "goal";
	static final String COLUMN_QUESTS_HUB = "hub";
	static final String COLUMN_QUESTS_TYPE = "type";
	static final String COLUMN_QUESTS_STARS = "stars";
	static final String COLUMN_QUESTS_LOCATION_ID = "location_id";
	static final String COLUMN_QUESTS_TIME_LIMIT = "time_limit";
	static final String COLUMN_QUESTS_FEE = "fee";
	static final String COLUMN_QUESTS_REWARD = "reward";
	static final String COLUMN_QUESTS_HRP = "hrp";
	
	// Quest Rewards
	static final String TABLE_QUEST_REWARDS = "quest_rewards";
	static final String COLUMN_QUEST_REWARDS_ID = "_id";
	static final String COLUMN_QUEST_REWARDS_QUEST_ID = "quest_id";
	static final String COLUMN_QUEST_REWARDS_ITEM_ID = "item_id";
	static final String COLUMN_QUEST_REWARDS_REWARD_SLOT = "reward_slot";
	static final String COLUMN_QUEST_REWARDS_PERCENTAGE = "percentage";
	static final String COLUMN_QUEST_REWARDS_STACK_SIZE = "stack_size";
	
	// Skills
	static final String TABLE_SKILLS = "skills";
	static final String COLUMN_SKILLS_ID = "_id";
	static final String COLUMN_SKILLS_SKILL_TREE_ID = "skill_tree_id";
	static final String COLUMN_SKILLS_REQUIRED_SKILL_TREE_POINTS = "required_skill_tree_points";
	static final String COLUMN_SKILLS_NAME = "name";
	static final String COLUMN_SKILLS_JPN_NAME = "jpn_name";
	static final String COLUMN_SKILLS_DESCRIPTION = "description";
	
	// Skill Trees
	static final String TABLE_SKILL_TREES = "skill_trees";
	static final String COLUMN_SKILL_TREES_ID = "_id";
	static final String COLUMN_SKILL_TREES_NAME = "name";
	static final String COLUMN_SKILL_TREES_JPN_NAME = "jpn_name";
	
	// Trading
	static final String TABLE_TRADING = "trading";
	static final String COLUMN_TRADING_ID = "_id";
	static final String COLUMN_TRADING_LOCATION_ID = "location_id";
	static final String COLUMN_TRADING_OFFER_ITEM_ID = "offer_item_id";
	static final String COLUMN_TRADING_RECEIVE_ITEM_ID = "receive_item_id";
	static final String COLUMN_TRADING_PERCENTAGE = "percentage";
	
	// Weapons
	static final String TABLE_WEAPONS = "weapons";
	static final String COLUMN_WEAPONS_ID = "_id";
	static final String COLUMN_WEAPONS_WTYPE = "wtype";
	static final String COLUMN_WEAPONS_CREATION_COST = "creation_cost";
	static final String COLUMN_WEAPONS_UPGRADE_COST = "upgrade_cost";
	static final String COLUMN_WEAPONS_ATTACK = "attack";
	static final String COLUMN_WEAPONS_MAX_ATTACK = "max_attack";
	static final String COLUMN_WEAPONS_ELEMENTAL_ATTACK = "elemental_attack";
	static final String COLUMN_WEAPONS_AWAKENED_ELEMENTAL_ATTACK = "awakened_elemental_attack";
	static final String COLUMN_WEAPONS_DEFENSE = "defense";
	static final String COLUMN_WEAPONS_SHARPNESS = "sharpness";
	static final String COLUMN_WEAPONS_AFFINITY = "affinity";
	static final String COLUMN_WEAPONS_HORN_NOTES = "horn_notes";
	static final String COLUMN_WEAPONS_SHELLING_TYPE = "shelling_type";
	static final String COLUMN_WEAPONS_CHARGE_LEVELS = "charge_levels";
	static final String COLUMN_WEAPONS_ALLOWED_COATINGS = "allowed_coatings";
	static final String COLUMN_WEAPONS_RECOIL = "recoil";
	static final String COLUMN_WEAPONS_RELOAD_SPEED = "reload_speed";
	static final String COLUMN_WEAPONS_RAPID_FIRE = "rapid_fire";
	static final String COLUMN_WEAPONS_NORMAL_SHOTS = "normal_shots";
	static final String COLUMN_WEAPONS_STATUS_SHOTS = "status_shots";
	static final String COLUMN_WEAPONS_ELEMENTAL_SHOTS = "elemental_shots";
	static final String COLUMN_WEAPONS_TOOL_SHOTS = "tool_shots";
	static final String COLUMN_WEAPONS_NUM_SLOTS = "num_slots";
	static final String COLUMN_WEAPONS_SHARPNESS_FILE = "sharpness_file";
	

}
