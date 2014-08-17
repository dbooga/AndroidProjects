package com.daviancorp.android.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.daviancorp.android.data.Location;
import com.daviancorp.android.data.Quest;

/**
 * A convenience class to wrap a cursor that returns rows from the "quests"
 * table. The {@link getQuest()} method will give you a Quest instance
 * representing the current row.
 */
public class QuestCursor extends CursorWrapper {

	public QuestCursor(Cursor c) {
		super(c);
	}

	/**
	 * Returns a Quest object configured for the current row, or null if the
	 * current row is invalid.
	 */
	public Quest getQuest() {
		if (isBeforeFirst() || isAfterLast())
			return null;
		
		Quest quest = new Quest();
		Location location = new Location();

		long questId = getLong(getColumnIndex(S.COLUMN_QUESTS_ID));
		quest.setId(questId);
		String name = getString(getColumnIndex("q" + S.COLUMN_QUESTS_NAME));
		quest.setName(name);
		String goal = getString(getColumnIndex(S.COLUMN_QUESTS_GOAL));
		quest.setGoal(goal);
		String hub = getString(getColumnIndex(S.COLUMN_QUESTS_HUB));
		quest.setHub(hub);
		String type = getString(getColumnIndex(S.COLUMN_QUESTS_TYPE));
		quest.setType(type);
		String stars = getString(getColumnIndex(S.COLUMN_QUESTS_STARS));
		quest.setStars(stars);
		long locationId = getLong(getColumnIndex(S.COLUMN_QUESTS_LOCATION_ID));
		location.setId(locationId);
		int timeLimit = getInt(getColumnIndex(S.COLUMN_QUESTS_TIME_LIMIT));
		quest.setTimeLimit(timeLimit);
		int fee = getInt(getColumnIndex(S.COLUMN_QUESTS_FEE));
		quest.setFee(fee);
		int reward = getInt(getColumnIndex(S.COLUMN_QUESTS_REWARD));
		quest.setReward(reward);
		int hrp = getInt(getColumnIndex(S.COLUMN_QUESTS_HRP));
		quest.setHrp(hrp);
		
		String locName = getString(getColumnIndex("l" + S.COLUMN_LOCATIONS_NAME));
		location.setName(locName);
		String fileLocation = getString(getColumnIndex(S.COLUMN_LOCATIONS_MAP));
		location.setFileLocation(fileLocation);
		
		return quest;
	}

}