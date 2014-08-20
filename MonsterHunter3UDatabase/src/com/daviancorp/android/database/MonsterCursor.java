package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Monster;

/**
 * A convenience class to wrap a cursor that returns rows from the "monsters"
 * table. The {@link getMonster()} method will give you a Monster instance
 * representing the current row.
 */
public class MonsterCursor extends CursorWrapper {

	public MonsterCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Monster object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Monster getMonster() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		Monster monster = new Monster();

		long monsterId = getLong(getColumnIndex(S.COLUMN_MONSTERS_ID));
		monster.setId(monsterId);
		String name = getString(getColumnIndex(S.COLUMN_MONSTERS_NAME));
		monster.setName(name);
		String monsterClass = getString(getColumnIndex(S.COLUMN_MONSTERS_CLASS));
		monster.setMonsterClass(monsterClass);
		String trait = getString(getColumnIndex(S.COLUMN_MONSTERS_TRAIT));
		monster.setTrait(trait);
		String file_location = getString(getColumnIndex(S.COLUMN_MONSTERS_FILE_LOCATION));
		monster.setFileLocation(file_location);

		return monster;
	}
}