package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Decoration;

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

		long decorationId = getLong(getColumnIndex(S.COLUMN_DECORATIONS_ID));
		decoration.setId(decorationId);
		String name = getString(getColumnIndex(S.COLUMN_ITEMS_NAME));
		decoration.setName(name);
		int num_slots = getInt(getColumnIndex());
		decoration.setNumSlots(num_slots);
		int price = getInt(getColumnIndex());
		decoration.setPrice(price);
		String skill_1 = getString(getColumnIndex());
		decoration.setSkill1(skill_1);
		int skill_1_point = getString(getColumnIndex());
		decoration.setSkill1Point(skill_1_point);
		String skill_2 = getString(getColumnIndex());
		decoration.setSkill2(skill_2);
		int skill_2_point = getString(getColumnIndex());
		decoration.setSkill1Point(skill_2_point);

		return decoration;
	}
	
}