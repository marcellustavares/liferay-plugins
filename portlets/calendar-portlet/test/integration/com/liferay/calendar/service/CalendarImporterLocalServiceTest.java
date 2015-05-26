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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBDiscussionLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class CalendarImporterLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_connection = DataAccess.getUpgradeOptimizedConnection();

		_calEventClassNameId = PortalUtil.getClassNameId(_CAL_EVENT_CLASS_NAME);
		_calendarBookingClassNameId = PortalUtil.getClassNameId(
			CalendarBooking.class);
		_mbDiscussionClassNameId = PortalUtil.getClassNameId(
			_MB_DISCUSSION_CLASS_NAME);

		_user = UserTestUtil.addUser();
		_userId = _user.getUserId();
		_userName = _user.getFullName();

		_group = GroupTestUtil.addGroup();
		_groupId = _group.getGroupId();
		_companyId = _group.getCompanyId();
	}

	@After
	public void tearDown() throws Exception {
		DataAccess.cleanUp(_connection);

		GroupLocalServiceUtil.deleteGroup(_group);

		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testImportCalEvent() throws Exception {
		String uuid = PortalUUIDUtil.generate();
		long eventId = CounterLocalServiceUtil.increment(_CAL_EVENT_CLASS_NAME);
		Timestamp createDate = new Timestamp(_randomTime());

		String title = RandomTestUtil.randomString();
		String description = RandomTestUtil.randomString();
		String location = RandomTestUtil.randomString();

		Timestamp startDate = new Timestamp(_randomTime());
		int durationHour = RandomTestUtil.randomInt(0, 23);
		int durationMinute = RandomTestUtil.randomInt(0, 59);

		_insertCalEvent(
			uuid, eventId, _groupId, _companyId, _userId, _userName, createDate,
			createDate, title, description, location, startDate, null,
			durationHour, durationMinute, false, true, "anniversary", false,
			null, 1, 900000, 300000);

		CalendarImporterLocalServiceUtil.importCalEvents();

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				uuid, _group.getGroupId());

		Assert.assertNotNull(calendarBooking);
		Assert.assertEquals(title, calendarBooking.getTitleCurrentValue());

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			startDate.getTime());

		Assert.assertEquals(
			calendar.getTimeInMillis(), calendarBooking.getStartTime());

		calendar.add(Calendar.HOUR, durationHour);
		calendar.add(Calendar.MINUTE, durationMinute);

		Assert.assertEquals(
			calendar.getTimeInMillis(), calendarBooking.getEndTime());
	}

	@Test
	public void testImportComment() throws Exception {
		String uuid = PortalUUIDUtil.generate();
		long eventId = CounterLocalServiceUtil.increment(_CAL_EVENT_CLASS_NAME);
		Timestamp createDate = new Timestamp(_randomTime());

		long messageId = CounterLocalServiceUtil.increment(
			_MB_MESSAGE_CLASS_NAME);
		String subject = RandomTestUtil.randomString();
		String body = RandomTestUtil.randomString();

		_insertCalEvent(
			uuid, eventId, _groupId, _companyId, _userId, _userName, createDate,
			createDate, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new Timestamp(_randomTime()), null, RandomTestUtil.randomInt(0, 23),
			RandomTestUtil.randomInt(0, 59), false, true, "anniversary", false,
			null, 1, 900000, 300000);
		_insertMBMessage(messageId, eventId, subject, body, createDate);

		CalendarImporterLocalServiceUtil.importCalEvents();

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				uuid, _groupId);
		MBDiscussion discussion = MBDiscussionLocalServiceUtil.fetchDiscussion(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		Assert.assertNotNull(discussion);
		Assert.assertEquals(
			_calendarBookingClassNameId, discussion.getClassNameId());
		Assert.assertEquals(
			calendarBooking.getCalendarBookingId(), discussion.getClassPK());

		List<MBMessage> messages = MBMessageLocalServiceUtil.getThreadMessages(
			discussion.getThreadId(), WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(1, messages.size());

		MBMessage message = messages.get(0);

		Assert.assertEquals(
			_calendarBookingClassNameId, message.getClassNameId());
		Assert.assertEquals(
			calendarBooking.getCalendarBookingId(), message.getClassPK());
		Assert.assertEquals(subject, message.getSubject());
		Assert.assertEquals(body, message.getBody());
	}

	@Test
	public void testImportCommentRating() throws Exception {
		String uuid = PortalUUIDUtil.generate();
		long eventId = CounterLocalServiceUtil.increment(_CAL_EVENT_CLASS_NAME);
		Timestamp createDate = new Timestamp(_randomTime());

		long messageId = CounterLocalServiceUtil.increment(
			_MB_MESSAGE_CLASS_NAME);

		double score = RandomTestUtil.randomDouble();

		_insertCalEvent(
			uuid, eventId, _groupId, _companyId, _userId, _userName, createDate,
			createDate, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new Timestamp(_randomTime()), null, RandomTestUtil.randomInt(0, 23),
			RandomTestUtil.randomInt(0, 59), false, true, "anniversary", false,
			null, 1, 900000, 300000);
		_insertMBMessage(
			messageId, eventId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), createDate);
		_insertRatingsEntry(
			_mbDiscussionClassNameId, messageId, score, createDate);

		CalendarImporterLocalServiceUtil.importCalEvents();

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				uuid, _group.getGroupId());
		MBDiscussion discussion = MBDiscussionLocalServiceUtil.fetchDiscussion(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());
		List<MBMessage> messages = MBMessageLocalServiceUtil.getThreadMessages(
			discussion.getThreadId(), WorkflowConstants.STATUS_APPROVED);
		MBMessage message = messages.get(0);

		RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.getEntry(
			_user.getUserId(), _MB_DISCUSSION_CLASS_NAME,
			message.getMessageId());

		Assert.assertNotNull(ratingsEntry);
		Assert.assertEquals(score, ratingsEntry.getScore(), 1e-5);
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

	private void _insertMBDiscussion(
			long discussionId, long threadId, long eventId,
			Timestamp createDate)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("insert into MBDiscussion (discussionId, classNameId, ");
		sb.append("classPK, threadId, uuid_, groupId, companyId, userId, ");
		sb.append("userName, createDate, modifiedDate) values (?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?, ?, ?)");

		PreparedStatement ps = _connection.prepareStatement(sb.toString());

		ps.setLong(1, discussionId);
		ps.setLong(2, _calEventClassNameId);
		ps.setLong(3, eventId);
		ps.setLong(4, threadId);
		ps.setString(5, PortalUUIDUtil.generate());
		ps.setLong(6, _groupId);
		ps.setLong(7, _companyId);
		ps.setLong(8, _userId);
		ps.setString(9, _userName);
		ps.setTimestamp(10, createDate);
		ps.setTimestamp(11, createDate);

		ps.executeUpdate();
	}

	private void _insertMBMessage(
			long messageId, long eventId, String subject, String body,
			Timestamp createDate)
		throws Exception {

		long discussionId = CounterLocalServiceUtil.increment(
			_MB_DISCUSSION_CLASS_NAME);
		long threadId = CounterLocalServiceUtil.increment(
			_MB_THREAD_CLASS_NAME);

		_insertMBThread(threadId, messageId, createDate);
		_insertMBDiscussion(discussionId, threadId, eventId, createDate);

		StringBundler sb = new StringBundler(6);

		sb.append("insert into MBMessage (uuid_, messageId, groupId, ");
		sb.append("companyId, userId, userName, createDate, modifiedDate, ");
		sb.append("classNameId, classPK, categoryId, threadId, ");
		sb.append("rootMessageId, parentMessageId, subject, body, format, ");
		sb.append("anonymous, priority, allowPingbacks, answer, status, ");
		sb.append("statusByUserId, statusByUserName, statusDate) values (?, ");
		sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?)");

		PreparedStatement ps = _connection.prepareStatement(sb.toString());

		ps.setString(1, PortalUUIDUtil.generate());
		ps.setLong(2, messageId);
		ps.setLong(3, _groupId);
		ps.setLong(4, _companyId);
		ps.setLong(5, _userId);
		ps.setString(6, _userName);
		ps.setTimestamp(7, createDate);
		ps.setTimestamp(8, createDate);
		ps.setLong(9, _calEventClassNameId);
		ps.setLong(10, eventId);
		ps.setLong(11, -1);
		ps.setLong(12, threadId);
		ps.setLong(13, messageId);
		ps.setLong(14, 0);
		ps.setString(15, subject);
		ps.setString(16, body);
		ps.setString(17, "bbcode");
		ps.setBoolean(18, false);
		ps.setInt(19, 0);
		ps.setBoolean(20, false);
		ps.setBoolean(21, false);
		ps.setInt(22, WorkflowConstants.STATUS_APPROVED);
		ps.setLong(23, _userId);
		ps.setString(24, _userName);
		ps.setTimestamp(25, createDate);

		ps.executeUpdate();
	}

	private void _insertMBThread(
			long threadId, long messageId, Timestamp createDate)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("insert into MBThread (uuid_, threadId, groupId, ");
		sb.append("companyId, categoryId, rootMessageId, rootMessageUserId, ");
		sb.append("messageCount, viewCount, lastPostByUserId, lastPostDate, ");
		sb.append("priority, question, status, statusByUserId, ");
		sb.append("statusByUserName, statusDate) values (?, ?, ?, ?, ?, ?, ");
		sb.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		PreparedStatement ps = _connection.prepareStatement(sb.toString());

		ps.setString(1, PortalUUIDUtil.generate());
		ps.setLong(2, threadId);
		ps.setLong(3, _groupId);
		ps.setLong(4, _companyId);
		ps.setLong(5, -1);
		ps.setLong(6, messageId);
		ps.setLong(7, _userId);
		ps.setInt(8, 1);
		ps.setInt(9, 0);
		ps.setLong(10, _userId);
		ps.setTimestamp(11, createDate);
		ps.setInt(12, 0);
		ps.setBoolean(13, false);
		ps.setInt(14, WorkflowConstants.STATUS_APPROVED);
		ps.setLong(15, _userId);
		ps.setString(16, _userName);
		ps.setTimestamp(17, createDate);

		ps.executeUpdate();
	}

	private void _insertRatingsEntry(
			long classNameId, long classPK, double score, Timestamp createDate)
		throws Exception {

		long ratingsEntryId = CounterLocalServiceUtil.increment(
			_RATINGS_ENTRY_CLASS_NAME);

		StringBundler sb = new StringBundler(6);

		sb.append("insert into RatingsEntry (uuid_, entryId, companyId, ");
		sb.append("userId, userName, createDate, modifiedDate, classNameId, ");
		sb.append("classPK, score) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		PreparedStatement ps = _connection.prepareStatement(sb.toString());

		ps.setString(1, PortalUUIDUtil.generate());
		ps.setLong(2, ratingsEntryId);
		ps.setLong(3, _companyId);
		ps.setLong(4, _userId);
		ps.setString(5, _userName);
		ps.setTimestamp(6, createDate);
		ps.setTimestamp(7, createDate);
		ps.setLong(8, classNameId);
		ps.setLong(9, classPK);
		ps.setDouble(10, score);

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

	private static final String _CAL_EVENT_CLASS_NAME =
		"com.liferay.portlet.calendar.model.CalEvent";

	private static final String _MB_DISCUSSION_CLASS_NAME =
		"com.liferay.portlet.messageboards.model.MBDiscussion";

	private static final String _MB_MESSAGE_CLASS_NAME =
		"com.liferay.portlet.messageboards.model.MBMessage";

	private static final String _MB_THREAD_CLASS_NAME =
		"com.liferay.portlet.messageboards.model.MBThread";

	private static final String _RATINGS_ENTRY_CLASS_NAME =
		"com.liferay.portlet.ratings.model.RatingsEntry";

	private static final TimeZone _UTC_TIME_ZONE = TimeZoneUtil.getTimeZone(
		StringPool.UTC);

	private long _calendarBookingClassNameId;
	private long _calEventClassNameId;
	private long _companyId;
	private Connection _connection;
	private Group _group;
	private long _groupId;
	private long _mbDiscussionClassNameId;
	private User _user;
	private long _userId;
	private String _userName;

}