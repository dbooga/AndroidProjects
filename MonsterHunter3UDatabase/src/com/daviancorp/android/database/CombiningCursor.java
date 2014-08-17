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
		
		String created = "crt";
		String mat1 = "mat1";
		String mat2 = "mat2";
		
		Combining combining = new Combining();

		long combiningId = getLong(getColumnIndex(S.COLUMN_COMBINING_ID));
		combining.setId(combiningId);
		int amount_made_min = getInt(getColumnIndex(S.COLUMN_COMBINING_AMOUNT_MADE_MIN));
		combining.setAmountMadeMin(amount_made_min);
		int amount_made_max = getInt(getColumnIndex(S.COLUMN_COMBINING_AMOUNT_MADE_MAX));
		combining.setAmountMadeMax(amount_made_max);
		int percentage = getInt(getColumnIndex(S.COLUMN_COMBINING_PERCENTAGE));
		combining.setPercentage(percentage);
		
		Item created_item = itemHelper(created);	
		Item item1 = itemHelper(mat1);	
		Item item2 = itemHelper(mat2);

		combining.setCreatedItem(created_item);
		combining.setItem1(item1);
		combining.setItem2(item2);

		return combining;
	}
	
	private Item itemHelper(String prefix) {
		Item item = new Item();

		long item_id = getLong(getColumnIndex(prefix + S.COLUMN_ITEMS_ID));
		item.setId(item_id);
		String item_name = getString(getColumnIndex(prefix + S.COLUMN_ITEMS_NAME));
		item.setName(item_name);
		String item_jpnName = getString(getColumnIndex(prefix + S.COLUMN_ITEMS_JPN_NAME));
		item.setJpnName(item_jpnName);
		String item_type = getString(getColumnIndex(prefix + S.COLUMN_ITEMS_TYPE));
		item.setType(item_type);
		int item_rarity = getInt(getColumnIndex(prefix + S.COLUMN_ITEMS_RARITY));
		item.setRarity(item_rarity);
		int item_carry_capacity = getInt(getColumnIndex(prefix + S.COLUMN_ITEMS_CARRY_CAPACITY));
		item.setCarryCapacity(item_carry_capacity);
		int item_buy = getInt(getColumnIndex(prefix + S.COLUMN_ITEMS_BUY));
		item.setBuy(item_buy);
		int item_sell = getInt(getColumnIndex(prefix + S.COLUMN_ITEMS_SELL));
		item.setSell(item_sell);
		String item_description = getString(getColumnIndex(prefix + S.COLUMN_ITEMS_DESCRIPTION));
		item.setDescription(item_description);
		String item_fileLocation = getString(getColumnIndex(prefix + S.COLUMN_ITEMS_ICON_NAME));
		item.setFileLocation(item_fileLocation);
		String item_armor_dupe_name_fix = getString(getColumnIndex(prefix + S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		item.setArmorDupeNameFix(item_armor_dupe_name_fix);
		
		return item;
	}
}