package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Combining;

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

//		long locationId = getLong(getColumnIndex(S.COLUMN_LOCATIONS_ID));
//		location.setId(locationId);
//		String name = getString(getColumnIndex(S.COLUMN_LOCATIONS_NAME));
//		location.setName(name);
//		String fileLocation = getString(getColumnIndex(S.COLUMN_LOCATIONS_MAP));
//		location.setFileLocation(fileLocation);


		return combining;
	}
}