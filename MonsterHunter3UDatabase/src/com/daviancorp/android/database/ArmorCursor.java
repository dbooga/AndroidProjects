package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.object.Armor;

/**
 * A convenience class to wrap a cursor that returns rows from the "armor"
 * table. The {@link getArmor()} method will give you a Armor instance
 * representing the current row.
 */
public class ArmorCursor extends CursorWrapper {

	public ArmorCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Armor object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Armor getArmor() {
		if (isBeforeFirst() || isAfterLast())
			return null;

		Armor armor = new Armor();

		String slot = getString(getColumnIndex(S.COLUMN_ARMOR_SLOT));
		armor.setSlot(slot);
		int defense = getInt(getColumnIndex(S.COLUMN_ARMOR_DEFENSE));
		armor.setDefense(defense);
		int max_defense = getInt(getColumnIndex(S.COLUMN_ARMOR_MAX_DEFENSE));
		armor.setMaxDefense(max_defense);
		int fire_res = getInt(getColumnIndex(S.COLUMN_ARMOR_FIRE_RES));
		armor.setFireRes(fire_res);
		int thunder_res = getInt(getColumnIndex(S.COLUMN_ARMOR_THUNDER_RES));
		armor.setThunderRes(thunder_res);
		int dragon_res = getInt(getColumnIndex(S.COLUMN_ARMOR_DRAGON_RES));
		armor.setDragonRes(dragon_res);
		int water_res = getInt(getColumnIndex(S.COLUMN_ARMOR_WATER_RES));
		armor.setWaterRes(water_res);
		int ice_res = getInt(getColumnIndex(S.COLUMN_ARMOR_ICE_RES));
		armor.setIceRes(ice_res);
		String gender = getString(getColumnIndex(S.COLUMN_ARMOR_GENDER));
		armor.setGender(gender);
		String hunter_type = getString(getColumnIndex(S.COLUMN_ARMOR_HUNTER_TYPE));
		armor.setHunterType(hunter_type);
		int num_slots = getInt(getColumnIndex(S.COLUMN_ARMOR_NUM_SLOTS));
		armor.setNumSlots(num_slots);

		long itemId = getLong(getColumnIndex(S.COLUMN_ITEMS_ID));
		armor.setId(itemId);
		String name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		armor.setName(name);
		String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		armor.setJpnName(jpnName);
		String type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		armor.setType(type);
		int rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		armor.setRarity(rarity);
		int carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		armor.setCarryCapacity(carry_capacity);
		int buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		armor.setBuy(buy);
		int sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		armor.setSell(sell);
		String description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		armor.setDescription(description);
		String fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		armor.setFileLocation(fileLocation);
		String armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		armor.setArmorDupeNameFix(armor_dupe_name_fix);

		return armor;
	}

}