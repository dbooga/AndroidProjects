package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.HuntingFleet;
import com.daviancorp.android.data.Item;

/**
 * A convenience class to wrap a cursor that returns rows from the "hunting_fleet"
 * table. The {@link getHuntingFleet()} method will give you a HuntingFleet instance
 * representing the current row.
 */
public class HuntingFleetCursor extends CursorWrapper {

	public HuntingFleetCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a HuntingFleet object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public HuntingFleet getHuntingFleet() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		HuntingFleet huntingFleet = new HuntingFleet();
		Item item = new Item();
		
		long huntingFleetID = getLong(getColumnIndex(S.COLUMN_HUNTING_FLEET_ID));
		huntingFleet.setId(huntingFleetID);
		String huntingFleetType = getString(getColumnIndex("h" + S.COLUMN_HUNTING_FLEET_TYPE));
		huntingFleet.setType(huntingFleetType);
		int level = getInt(getColumnIndex(S.COLUMN_HUNTING_FLEET_LEVEL));
		huntingFleet.setLevel(level);
		String location = getString(getColumnIndex(S.COLUMN_HUNTING_FLEET_LOCATION));
		huntingFleet.setLocation(location);
		long itemId = getLong(getColumnIndex(S.COLUMN_HUNTING_FLEET_ITEM_ID));
		item.setId(itemId);
		int amount = getInt(getColumnIndex(S.COLUMN_HUNTING_FLEET_AMOUNT));
		huntingFleet.setAmount(amount);
		int percentage = getInt(getColumnIndex(S.COLUMN_HUNTING_FLEET_PERCENTAGE));
		huntingFleet.setPercentage(percentage);

		
		String itemName = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		item.setName(itemName);
		String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		item.setJpnName(jpnName);
		String itemType = getString(getColumnIndex("i" + S.COLUMN_ITEMS_TYPE));
		item.setType(itemType);
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
		
		huntingFleet.setItem(item);
		
		return huntingFleet;
	}

}