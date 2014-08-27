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
	public static final String FROM_ITEM = "item";
	public static final String FROM_MONSTER = "monster";

	public CarveCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Carve object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Carve getCarve(String from) {
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

		if(from.equals(FROM_MONSTER)) {
			Item item = new Item();
			
			long itemId = getLong(getColumnIndex(S.COLUMN_CARVES_ITEM_ID));
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
			
			carve.setItem(item);
		}
		else if (from.equals(FROM_ITEM)) {
			Monster monster = new Monster();

			long monsterId = getLong(getColumnIndex(S.COLUMN_CARVES_MONSTER_ID));
			monster.setId(monsterId);
			String name = getString(getColumnIndex(S.COLUMN_MONSTERS_NAME));
			monster.setName(name);
			String monsterClass = getString(getColumnIndex(S.COLUMN_MONSTERS_CLASS));
			monster.setMonsterClass(monsterClass);
			String trait = getString(getColumnIndex(S.COLUMN_MONSTERS_TRAIT));
			monster.setTrait(trait);
			String file_location = getString(getColumnIndex(S.COLUMN_MONSTERS_FILE_LOCATION)); 
			monster.setFileLocation(file_location); 
			
			carve.setMonster(monster);
		}
		
		return carve;
	}

}