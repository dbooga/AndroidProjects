package com.daviancorp.android.data.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.object.Carve;
import com.daviancorp.android.data.object.Item;
import com.daviancorp.android.data.object.Monster;

/**
 * A convenience class to wrap a cursor that returns rows from the "carve"
 * table. The {@link getCarve()} method will give you a Carve instance
 * representing the current row.
 */
public class CarveCursor extends CursorWrapper {

	public CarveCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Carve object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Carve getCarve() {
		if (isBeforeFirst() || isAfterLast())
			return null;

		Carve carve = new Carve();

		long id = getLong(getColumnIndex(S.COLUMN_CARVES_ID));
		String rank = getString(getColumnIndex(S.COLUMN_CARVES_RANK));
		String location = getString(getColumnIndex(S.COLUMN_CARVES_LOCATION));
		int num_carves = getInt(getColumnIndex(S.COLUMN_CARVES_NUM_CARVES));
		int percentage = getInt(getColumnIndex(S.COLUMN_CARVES_PERCENTAGE));

		carve.setId(id);
		carve.setRank(rank);
		carve.setLocation(location);
		carve.setNumCarves(num_carves);
		carve.setPercentage(percentage);

		Item item = new Item();

		long itemId = getLong(getColumnIndex(S.COLUMN_CARVES_ITEM_ID));
		String itemName = getString(getColumnIndex("i" + S.COLUMN_ITEMS_NAME));
		// String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		// String type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		// int rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		// int carry_capacity =
		// getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		// int buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		// int sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		// String description =
		// getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		 String fileLocation =
		 getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		// String armor_dupe_name_fix =
		// getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));

		item.setId(itemId);
		item.setName(itemName);
		// item.setJpnName(jpnName);
		// item.setType(type);
		// item.setRarity(rarity);
		// item.setCarryCapacity(carry_capacity);
		// item.setBuy(buy);
		// item.setSell(sell);
		// item.setDescription(description);
		 item.setFileLocation(fileLocation);
		// item.setArmorDupeNameFix(armor_dupe_name_fix);

		carve.setItem(item);

		Monster monster = new Monster();

		long monsterId = getLong(getColumnIndex(S.COLUMN_CARVES_MONSTER_ID));
		String monsterName = getString(getColumnIndex("m" + S.COLUMN_MONSTERS_NAME));
		// String monsterClass =
		// getString(getColumnIndex(S.COLUMN_MONSTERS_CLASS));
		// String trait = getString(getColumnIndex(S.COLUMN_MONSTERS_TRAIT));
		// String file_location =
		// getString(getColumnIndex(S.COLUMN_MONSTERS_FILE_LOCATION));

		monster.setId(monsterId);
		monster.setName(monsterName);
		// monster.setMonsterClass(monsterClass);
		// monster.setTrait(trait);
		// monster.setFileLocation(file_location);

		carve.setMonster(monster);

		return carve;
	}

}