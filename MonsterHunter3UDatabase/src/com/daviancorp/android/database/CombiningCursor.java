package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Combining;
import com.daviancorp.android.data.Item;

/**
 * A convenience class to wrap a cursor that returns rows from the "combining"
 * table. The {@link getCombining()} method will give you a Combining instance
 * representing the current row.
 */
public class CombiningCursor extends CursorWrapper {

	public CombiningCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Combining objects configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Combining getCombining() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		Combining combining = new Combining();

		long combiningId = getLong(getColumnIndex(S.COLUMN_COMBINING_ID));
		combining.setId(combiningId);
		int amount_made_min = getInt(getColumnIndex(S.COLUMN_COMBINING_AMOUNT_MADE_MIN));
		combining.setAmountMadeMin(amount_made_min);
		int amount_made_max = getInt(getColumnIndex(S.COLUMN_COMBINING_AMOUNT_MADE_MAX));
		combining.setAmountMadeMax(amount_made_max);
		int percentage = getInt(getColumnIndex(S.COLUMN_COMBINING_PERCENTAGE));
		combining.setPercentage(percentage);
		
		
		Item created_item = new Item();

		long created_itemId = getLong(getColumnIndex(S.COLUMN_ITEMS_ID));
		created_item.setId(created_itemId);
		String created_name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		created_item.setName(created_name);
		String created_jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		created_item.setJpnName(created_jpnName);
		String created_type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		created_item.setType(created_type);
		int created_rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		created_item.setRarity(created_rarity);
		int created_carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		created_item.setCarryCapacity(created_carry_capacity);
		int created_buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		created_item.setBuy(created_buy);
		int created_sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		created_item.setSell(created_sell);
		String created_description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		created_item.setDescription(created_description);
		String created_fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		created_item.setFileLocation(created_fileLocation);
		String created_armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		created_item.setArmorDupeNameFix(created_armor_dupe_name_fix);

		
		Item item1 = new Item();

		long item1_id = getLong(getColumnIndex(S.COLUMN_ITEMS_ID));
		item1.setId(item1_id);
		String item1_name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		item1.setName(item1_name);
		String item1_jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		item1.setJpnName(item1_jpnName);
		String item1_type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		item1.setType(item1_type);
		int item1_rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		item1.setRarity(item1_rarity);
		int item1_carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		item1.setCarryCapacity(item1_carry_capacity);
		int item1_buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		item1.setBuy(item1_buy);
		int item1_sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		item1.setSell(item1_sell);
		String item1_description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		item1.setDescription(item1_description);
		String item1_fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		item1.setFileLocation(item1_fileLocation);
		String item1_armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		item1.setArmorDupeNameFix(item1_armor_dupe_name_fix);

		
		Item item2 = new Item();

		long item2_id = getLong(getColumnIndex(S.COLUMN_ITEMS_ID));
		item2.setId(item2_id);
		String item2_name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		item2.setName(item2_name);
		String item2_jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		item2.setJpnName(item2_jpnName);
		String item2_type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		item2.setType(item2_type);
		int item2_rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		item2.setRarity(item2_rarity);
		int item2_carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		item2.setCarryCapacity(item2_carry_capacity);
		int item2_buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		item2.setBuy(item2_buy);
		int item2_sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		item2.setSell(item2_sell);
		String item2_description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		item2.setDescription(item2_description);
		String item2_fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		item2.setFileLocation(item2_fileLocation);
		String item2_armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		item2.setArmorDupeNameFix(item2_armor_dupe_name_fix);

		
//		long locationId = getLong(getColumnIndex(S.COLUMN_LOCATIONS_ID));
//		location.setId(locationId);
//		String name = getString(getColumnIndex(S.COLUMN_LOCATIONS_NAME));
//		location.setName(name);
//		String fileLocation = getString(getColumnIndex(S.COLUMN_LOCATIONS_MAP));
//		location.setFileLocation(fileLocation);


		return combining;
	}
}