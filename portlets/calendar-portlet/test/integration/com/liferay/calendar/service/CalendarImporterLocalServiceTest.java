/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.calendar.service;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class CalendarImporterLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_connection = DataAccess.getUpgradeOptimizedConnection();
	}

	@After
	public void tearDown() throws Exception {
		DataAccess.cleanUp(_connection);
	}

	private void _insertCalEvent(
			String uuid, long eventId, long groupId, long companyId,
			long userId, String userName, Timestamp createDate,
			Timestamp modifiedDate, String title, String description,
			String location, Timestamp startDate, Timestamp endDate,
			int durationHour, int durationMinute, boolean allDay,
			boolean timeZoneSensitive, String type, boolean repeating,
			String recurrence, int remindBy, int firstReminder,
			int secondReminder)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("insert into CalEvent (uuid_, eventId, groupId, companyId, ");
		sb.append("userId, userName, createDate, modifiedDate, title, ");
		sb.append("description, location, startDate, endDate, durationHour, ");
		sb.append("durationMinute, allDay, timeZoneSensitive, type_, ");
		sb.append("repeating, recurrence, remindBy, firstReminder, ");
		sb.append("secondReminder) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		PreparedStatement ps = _connection.prepareStatement(sb.toString());

		ps.setString(1, uuid);
		ps.setLong(2, eventId);
		ps.setLong(3, groupId);
		ps.setLong(4, companyId);
		ps.setLong(5, userId);
		ps.setString(6, userName);
		ps.setTimestamp(7, createDate);
		ps.setTimestamp(8, modifiedDate);
		ps.setString(9, title);
		ps.setString(10, description);
		ps.setString(11, location);
		ps.setTimestamp(12, startDate);
		ps.setTimestamp(13, endDate);
		ps.setInt(14, durationHour);
		ps.setInt(15, durationMinute);
		ps.setBoolean(16, allDay);
		ps.setBoolean(17, timeZoneSensitive);
		ps.setString(18, type);
		ps.setBoolean(19, repeating);
		ps.setString(20, recurrence);
		ps.setInt(21, remindBy);
		ps.setInt(22, firstReminder);
		ps.setInt(23, secondReminder);

		ps.executeUpdate();
	}

	private long _randomTime() {
		int day = RandomTestUtil.randomInt(1, 31);
		int hour = RandomTestUtil.randomInt(0, 23);
		int minute = RandomTestUtil.randomInt(0, 59);
		int month = RandomTestUtil.randomInt(
			Calendar.JANUARY, Calendar.DECEMBER);
		int year = RandomTestUtil.randomInt(1970, 2050);

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			year, month, day, hour, minute, 0, 0, _UTC_TIME_ZONE);

		return calendar.getTimeInMillis();
	}

	private static final TimeZone _UTC_TIME_ZONE = TimeZoneUtil.getTimeZone(
		StringPool.UTC);

	private Connection _connection;

}