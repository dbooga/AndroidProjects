package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.object.Decoration;

/**
 * A convenience class to wrap a cursor that returns rows from the "decorations"
 * table. The {@link getDecoration()} method will give you a Decoration instance
 * representing the current row.
 */
public class DecorationCursor extends CursorWrapper {

	public DecorationCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Decoration object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Decoration getDecoration() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		Decoration decoration = new Decoration();

		long decorationId = getLong(getColumnIndex("_id"));
		decoration.setId(decorationId);
		String name = getString(getColumnIndex("item_name"));
		decoration.setName(name);
		String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		decoration.setJpnName(jpnName);
		String type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		decoration.setType(type);
		int rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		decoration.setRarity(rarity);
		int carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		decoration.setCarryCapacity(carry_capacity);
		int buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		decoration.setBuy(buy);
		int sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		decoration.setSell(sell);
		String description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		decoration.setDescription(description);
		String fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		decoration.setFileLocation(fileLocation);
		String armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		decoration.setArmorDupeNameFix(armor_dupe_name_fix);		
		
		int num_slots = getInt(getColumnIndex(S.COLUMN_DECORATIONS_NUM_SLOTS));
		decoration.setNumSlots(num_slots);
		
		long skill_1_id = getLong(getColumnIndex("skill_1_id"));
		decoration.setSkill1Id(skill_1_id);
		String skill_1_name = getString(getColumnIndex("skill_1_name"));
		decoration.setSkill1Name(skill_1_name);
		int skill_1_point = getInt(getColumnIndex("skill_1_point_value"));
		decoration.setSkill1Point(skill_1_point);
		
		long skill_2_id = getLong(getColumnIndex("skill_2_id"));
		decoration.setSkill1Id(skill_2_id);
		String skill_2_name = getString(getColumnIndex("skill_2_name"));
		decoration.setSkill2Name(skill_2_name);
		int skill_2_point = getInt(getColumnIndex("skill_2_point_value"));
		decoration.setSkill1Point(skill_2_point);

		return decoration;
	}
	
}