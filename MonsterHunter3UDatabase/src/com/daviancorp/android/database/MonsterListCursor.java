package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Monster;

/**
 * A convenience class to wrap a cursor that returns rows from the "monsters"
 * table. The {@link getMonsterList()} method will give you a Monster instance
 * representing the current row.
 */
public class MonsterListCursor extends CursorWrapper {

	public MonsterListCursor(Cursor c) {
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

		long monsterId = getLong(getColumnIndex(S.COLUMN_MONSTER_ID));
		monster.setId(monsterId);
		String name = getString(getColumnIndex(S.COLUMN_MONSTER_NAME));
		monster.setName(name);
		String monsterClass = getString(getColumnIndex(S.COLUMN_MONSTER_CLASS));
		monster.setMonsterClass(monsterClass);
		String trait = getString(getColumnIndex(S.COLUMN_MONSTER_TRAIT));
		monster.setTrait(trait);

		return monster;
	}
}