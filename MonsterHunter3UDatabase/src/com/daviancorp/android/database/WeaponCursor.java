package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.object.Weapon;

/**
 * A convenience class to wrap a cursor that returns rows from the "weapon"
 * table. The {@link getWeapon()} method will give you a Weapon instance
 * representing the current row.
 */
public class WeaponCursor extends CursorWrapper {

	public WeaponCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Weapon object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Weapon getWeapon() {
		if (isBeforeFirst() || isAfterLast())
			return null;

		Weapon weapon = new Weapon();

		String wtype = getString(getColumnIndex(S.COLUMN_WEAPONS_WTYPE));
		weapon.setWtype(wtype);
		int creation_cost = getInt(getColumnIndex(S.COLUMN_WEAPONS_CREATION_COST));
		weapon.setCreationCost(creation_cost);
		int upgrade_cost = getInt(getColumnIndex(S.COLUMN_WEAPONS_UPGRADE_COST));
		weapon.setUpgradeCost(upgrade_cost);
		int attack = getInt(getColumnIndex(S.COLUMN_WEAPONS_ATTACK));
		weapon.setAttack(attack);
		int max_attack = getInt(getColumnIndex(S.COLUMN_WEAPONS_MAX_ATTACK));
		weapon.setMaxAttack(max_attack);
		String elemental_attack = getString(getColumnIndex(S.COLUMN_WEAPONS_ELEMENTAL_ATTACK));
		weapon.setElementalAttack(elemental_attack);
		String awakened_elemental_attack = getString(getColumnIndex(S.COLUMN_WEAPONS_AWAKENED_ELEMENTAL_ATTACK));
		weapon.setAwakenedElementalAttack(awakened_elemental_attack);
		int defense = getInt(getColumnIndex(S.COLUMN_WEAPONS_DEFENSE));
		weapon.setDefense(defense);
		String sharpness = getString(getColumnIndex(S.COLUMN_WEAPONS_SHARPNESS));
		weapon.setSharpness(sharpness);
		int affinity = getInt(getColumnIndex(S.COLUMN_WEAPONS_AFFINITY));
		weapon.setAffinity(affinity);
		String horn_notes = getString(getColumnIndex(S.COLUMN_WEAPONS_HORN_NOTES));
		weapon.setHornNotes(horn_notes);
		String shelling_type = getString(getColumnIndex(S.COLUMN_WEAPONS_SHELLING_TYPE));
		weapon.setShellingType(shelling_type);
		String charge_levels = getString(getColumnIndex(S.COLUMN_WEAPONS_CHARGE_LEVELS));
		weapon.setChargeLevels(charge_levels);
		String allowed_coatings = getString(getColumnIndex(S.COLUMN_WEAPONS_ALLOWED_COATINGS));
		weapon.setAllowedCoatings(allowed_coatings);
		String recoil = getString(getColumnIndex(S.COLUMN_WEAPONS_RECOIL));
		weapon.setRecoil(recoil);
		String reload_speed = getString(getColumnIndex(S.COLUMN_WEAPONS_RELOAD_SPEED));
		weapon.setReloadSpeed(reload_speed);
		String rapid_fire = getString(getColumnIndex(S.COLUMN_WEAPONS_RAPID_FIRE));
		weapon.setRapidFire(rapid_fire);
		String normal_shots = getString(getColumnIndex(S.COLUMN_WEAPONS_NORMAL_SHOTS));
		weapon.setNormalShots(normal_shots);
		String status_shots = getString(getColumnIndex(S.COLUMN_WEAPONS_STATUS_SHOTS));
		weapon.setStatusShots(status_shots);
		String elemental_shots = getString(getColumnIndex(S.COLUMN_WEAPONS_ELEMENTAL_SHOTS));
		weapon.setElementalShots(elemental_shots);
		String tool_shots = getString(getColumnIndex(S.COLUMN_WEAPONS_TOOL_SHOTS));
		weapon.setToolShots(tool_shots);
		int num_slots = getInt(getColumnIndex(S.COLUMN_WEAPONS_NUM_SLOTS));
		weapon.setNumSlots(num_slots);
		String sharpness_file = getString(getColumnIndex(S.COLUMN_WEAPONS_SHARPNESS_FILE));
		weapon.setSharpnessFile(sharpness_file);

		long itemId = getLong(getColumnIndex(S.COLUMN_ITEMS_ID));
		weapon.setId(itemId);
		String name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		weapon.setName(name);
		String jpnName = getString(getColumnIndex(S.COLUMN_ITEMS_JPN_NAME));
		weapon.setJpnName(jpnName);
		String type = getString(getColumnIndex(S.COLUMN_ITEMS_TYPE));
		weapon.setType(type);
		int rarity = getInt(getColumnIndex(S.COLUMN_ITEMS_RARITY));
		weapon.setRarity(rarity);
		int carry_capacity = getInt(getColumnIndex(S.COLUMN_ITEMS_CARRY_CAPACITY));
		weapon.setCarryCapacity(carry_capacity);
		int buy = getInt(getColumnIndex(S.COLUMN_ITEMS_BUY));
		weapon.setBuy(buy);
		int sell = getInt(getColumnIndex(S.COLUMN_ITEMS_SELL));
		weapon.setSell(sell);
		String description = getString(getColumnIndex(S.COLUMN_ITEMS_DESCRIPTION));
		weapon.setDescription(description);
		String fileLocation = getString(getColumnIndex(S.COLUMN_ITEMS_ICON_NAME));
		weapon.setFileLocation(fileLocation);
		String armor_dupe_name_fix = getString(getColumnIndex(S.COLUMN_ITEMS_ARMOR_DUPE_NAME_FIX));
		weapon.setArmorDupeNameFix(armor_dupe_name_fix);

		return weapon;
	}

}