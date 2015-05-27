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
import com.liferay.calendar.util.CalendarImporterTestHelper;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
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
		_user = UserTestUtil.addUser();

		_userId = _user.getUserId();
		_userName = _user.getFullName();

		_group = GroupTestUtil.addGroup();

		_groupId = _group.getGroupId();
		_companyId = _group.getCompanyId();
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(_group);
		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testImportCalEvent() throws Exception {
		String uuid = PortalUUIDUtil.generate();

		Timestamp createDate = new Timestamp(randomTimeInMillis());

		String title = RandomTestUtil.randomString();
		String description = RandomTestUtil.randomString();
		String location = RandomTestUtil.randomString();

		Timestamp startDate = new Timestamp(randomTimeInMillis());

		int durationHour = RandomTestUtil.randomInt(0, 23);
		int durationMinute = RandomTestUtil.randomInt(0, 59);

		_calendarImporterTestHelper.addCalEvent(
			uuid, increment(), _groupId, _companyId, _userId, _userName,
			createDate, createDate, title, description, location, startDate,
			null, durationHour, durationMinute, false, true, "anniversary",
			false, null, 1, 900000, 300000);

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

		long eventId = increment();

		Timestamp createDate = new Timestamp(randomTimeInMillis());

		_calendarImporterTestHelper.addCalEvent(
			uuid, eventId, _groupId, _companyId, _userId, _userName, createDate,
			createDate, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new Timestamp(randomTimeInMillis()), null,
			RandomTestUtil.randomInt(0, 23), RandomTestUtil.randomInt(0, 59),
			false, true, "anniversary", false, null, 1, 900000, 300000);

		String subject = RandomTestUtil.randomString();
		String body = RandomTestUtil.randomString();

		addMBMessage(increment(), createDate, eventId, subject, body);

		CalendarImporterLocalServiceUtil.importCalEvents();

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				uuid, _groupId);

		MBDiscussion discussion = MBDiscussionLocalServiceUtil.fetchDiscussion(
			CalendarBooking.class.getName(),
			calendarBooking.getCalendarBookingId());

		Assert.assertNotNull(discussion);
		Assert.assertEquals(
			CalendarBooking.class.getName(), discussion.getClassName());
		Assert.assertEquals(
			calendarBooking.getCalendarBookingId(), discussion.getClassPK());

		List<MBMessage> messages = MBMessageLocalServiceUtil.getThreadMessages(
			discussion.getThreadId(), WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(1, messages.size());

		MBMessage message = messages.get(0);

		Assert.assertEquals(
			CalendarBooking.class.getName(), message.getClassName());
		Assert.assertEquals(
			calendarBooking.getCalendarBookingId(), message.getClassPK());
		Assert.assertEquals(subject, message.getSubject());
		Assert.assertEquals(body, message.getBody());
	}

	@Test
	public void testImportCommentRating() throws Exception {
		String uuid = PortalUUIDUtil.generate();

		long eventId = increment();

		Timestamp createDate = new Timestamp(randomTimeInMillis());

		_calendarImporterTestHelper.addCalEvent(
			uuid, eventId, _groupId, _companyId, _userId, _userName, createDate,
			createDate, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new Timestamp(randomTimeInMillis()), null,
			RandomTestUtil.randomInt(0, 23), RandomTestUtil.randomInt(0, 59),
			false, true, "anniversary", false, null, 1, 900000, 300000);

		long messageId = increment();

		addMBMessage(
			messageId, createDate, eventId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString());

		double score = RandomTestUtil.randomDouble();

		_calendarImporterTestHelper.addRatingsEntry(
			increment(), _companyId, _userId, _userName, createDate,
			PortalUtil.getClassNameId(MBDiscussion.class.getName()), messageId,
			score);

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
			_user.getUserId(), MBDiscussion.class.getName(),
			message.getMessageId());

		Assert.assertNotNull(ratingsEntry);
		Assert.assertEquals(score, ratingsEntry.getScore(), 1e-5);
	}

	protected void addMBMessage(
			long messageId, Timestamp createDate, long eventId, String subject,
			String body)
		throws Exception {

		long calEventClassNameId = PortalUtil.getClassNameId(
			"com.liferay.portlet.calendar.model.CalEvent");

		long threadId = increment();

		_calendarImporterTestHelper.addMBThread(
			threadId, _groupId, _companyId, _userId, _userName, createDate,
			messageId);

		_calendarImporterTestHelper.addMBDiscussion(
			increment(), _groupId, _companyId, _userId, _userName, createDate,
			calEventClassNameId, eventId, threadId);

		_calendarImporterTestHelper.addMBMessage(
			messageId, _groupId, _companyId, _userId, _userName, createDate,
			calEventClassNameId, eventId, threadId, subject, body);
	}

	protected long increment() {
		return CounterLocalServiceUtil.increment();
	}

	protected long randomTimeInMillis() {
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

	private CalendarImporterTestHelper _calendarImporterTestHelper =
		new CalendarImporterTestHelper();
	private long _companyId;
	private Group _group;
	private long _groupId;
	private User _user;
	private long _userId;
	private String _userName;

}