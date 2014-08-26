package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.object.Location;

/**
 * A convenience class to wrap a cursor that returns rows from the "locations"
 * table. The {@link getLocation()} method will give you a Location instance
 * representing the current row.
 */
public class LocationCursor extends CursorWrapper {

	public LocationCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Location object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Location getLocation() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		Location location = new Location();

		long locationId = getLong(getColumnIndex(S.COLUMN_LOCATIONS_ID));
		location.setId(locationId);
		String name = getString(getColumnIndex(S.COLUMN_LOCATIONS_NAME));
		location.setName(name);
		String fileLocation = getString(getColumnIndex(S.COLUMN_LOCATIONS_MAP));
		location.setFileLocation(fileLocation);

		return location;
	}
}