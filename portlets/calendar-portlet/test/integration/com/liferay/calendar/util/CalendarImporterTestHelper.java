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

package com.liferay.calendar.util;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * @author Adam Brandizzi
 * @author Marcellus Tavares
 */
public class CalendarImporterTestHelper {

	public void addCalEvent(
			String uuid, long eventId, long groupId, long companyId,
			long userId, String userName, Timestamp createDate,
			Timestamp modifiedDate, String title, String description,
			String location, Timestamp startDate, Timestamp endDate,
			int durationHour, int durationMinute, boolean allDay,
			boolean timeZoneSensitive, String type, boolean repeating,
			String recurrence, int remindBy, int firstReminder,
			int secondReminder)
		throws Exception {

		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(8);

			sb.append("insert into CalEvent (uuid_, eventId, groupId, ");
			sb.append("companyId, userId, userName, createDate, ");
			sb.append("modifiedDate, title, description, location, ");
			sb.append("startDate, endDate, durationHour, durationMinute, ");
			sb.append("allDay, timeZoneSensitive, type_, repeating, ");
			sb.append("recurrence, remindBy, firstReminder, secondReminder) ");
			sb.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement ps = con.prepareStatement(sb.toString());

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
		finally {
			DataAccess.cleanUp(con);
		}
	}

	public void addMBDiscussion(
			long discussionId, long groupId, long companyId, long userId,
			String userName, Timestamp createDate, long classNameId,
			long classPK, long threadId)
		throws Exception {

		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("insert into MBDiscussion (uuid_, discussionId, ");
			sb.append("groupId, companyId, userId, userName, createDate, ");
			sb.append("modifiedDate, classNameId, classPK, threadId) values ");
			sb.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement ps = con.prepareStatement(sb.toString());

			ps.setString(1, PortalUUIDUtil.generate());
			ps.setLong(2, discussionId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setTimestamp(7, createDate);
			ps.setTimestamp(8, createDate);
			ps.setLong(9, classNameId);
			ps.setLong(10, classPK);
			ps.setLong(11, threadId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	public void addMBMessage(
			long messageId, long groupId, long companyId, long userId,
			String userName, Timestamp createDate, long classNameId,
			long classPK, long threadId, long rootMessageId,
			long parentMessageId, String subject, String body)
		throws Exception {

		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(8);

			sb.append("insert into MBMessage (uuid_, messageId, groupId, ");
			sb.append("companyId, userId, userName, createDate, ");
			sb.append("modifiedDate, classNameId, classPK, categoryId, ");
			sb.append("threadId, rootMessageId, parentMessageId, subject, ");
			sb.append("body, format, anonymous, priority, allowPingbacks, ");
			sb.append("answer, status, statusByUserId, statusByUserName, ");
			sb.append("statusDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement ps = con.prepareStatement(sb.toString());

			ps.setString(1, PortalUUIDUtil.generate());
			ps.setLong(2, messageId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setTimestamp(7, createDate);
			ps.setTimestamp(8, createDate);
			ps.setLong(9, classNameId);
			ps.setLong(10, classPK);
			ps.setLong(11, -1);
			ps.setLong(12, threadId);
			ps.setLong(13, rootMessageId);
			ps.setLong(14, parentMessageId);
			ps.setString(15, subject);
			ps.setString(16, body);
			ps.setString(17, "bbcode");
			ps.setBoolean(18, false);
			ps.setInt(19, 0);
			ps.setBoolean(20, false);
			ps.setBoolean(21, false);
			ps.setInt(22, WorkflowConstants.STATUS_APPROVED);
			ps.setLong(23, userId);
			ps.setString(24, userName);
			ps.setTimestamp(25, createDate);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	public void addMBThread(
			long threadId, long groupId, long companyId, long userId,
			String userName, Timestamp createDate, long rootMessageId)
		throws Exception {

		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(7);

			sb.append("insert into MBThread (uuid_, threadId, groupId, ");
			sb.append("companyId, categoryId, rootMessageId, ");
			sb.append("rootMessageUserId, messageCount, viewCount, ");
			sb.append("lastPostByUserId, lastPostDate, priority, question, ");
			sb.append("status, statusByUserId, statusByUserName, statusDate) ");
			sb.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sb.append("?, ?)");

			PreparedStatement ps = con.prepareStatement(sb.toString());

			ps.setString(1, PortalUUIDUtil.generate());
			ps.setLong(2, threadId);
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, -1);
			ps.setLong(6, rootMessageId);
			ps.setLong(7, userId);
			ps.setInt(8, 1);
			ps.setInt(9, 0);
			ps.setLong(10, userId);
			ps.setTimestamp(11, createDate);
			ps.setInt(12, 0);
			ps.setBoolean(13, false);
			ps.setInt(14, WorkflowConstants.STATUS_APPROVED);
			ps.setLong(15, userId);
			ps.setString(16, userName);
			ps.setTimestamp(17, createDate);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

	public void addRatingsEntry(
			long ratingsEntryId, long companyId, long userId, String userName,
			Timestamp createDate, long classNameId, long classPK, double score)
		throws Exception {

		Connection con = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("insert into RatingsEntry (uuid_, entryId, companyId, ");
			sb.append("userId, userName, createDate, modifiedDate, ");
			sb.append("classNameId, classPK, score) values (?, ?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?)");

			PreparedStatement ps = con.prepareStatement(sb.toString());

			ps.setString(1, PortalUUIDUtil.generate());
			ps.setLong(2, ratingsEntryId);
			ps.setLong(3, companyId);
			ps.setLong(4, userId);
			ps.setString(5, userName);
			ps.setTimestamp(6, createDate);
			ps.setTimestamp(7, createDate);
			ps.setLong(8, classNameId);
			ps.setLong(9, classPK);
			ps.setDouble(10, score);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

}