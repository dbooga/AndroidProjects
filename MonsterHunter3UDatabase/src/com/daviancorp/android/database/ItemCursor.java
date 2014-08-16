package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Item;

/**
 * A convenience class to wrap a cursor that returns rows from the "items"
 * table. The {@link getItem()} method will give you an Item instance
 * representing the current row.
 */
public class ItemCursor extends CursorWrapper {

	public ItemCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns an Item object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Item getItem() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		Item item = new Item();

		long itemId = getLong(getColumnIndex(S.COLUMN_ITEMS_ID));
		item.setId(itemId);
		String name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		item.setName(name);
		String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		item.setJpnName(jpnName);
		String type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		item.setType(type);
		int rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		item.setRarity(rarity);
		int carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		item.setCarryCapacity(carry_capacity);
		int buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		item.setBuy(buy);
		int sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		item.setSell(sell);
		String description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		item.setDescription(description);
		String fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		item.setFileLocation(fileLocation);
		String armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		item.setArmorDupeNameFix(armor_dupe_name_fix);

		return item;
	}
}
