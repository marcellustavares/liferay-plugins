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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.impl.CalendarBookingImpl;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.util.CalendarFactoryImpl;
import com.liferay.util.service.ServiceProps;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Adam Brandizzi
 */
@PrepareOnlyThisForTest( {
	ConfigurationFactoryUtil.class, PortletClassLoaderUtil.class,
	ServiceProps.class
})
@RunWith(PowerMockRunner.class)
public class RecurrenceUtilTest {

	@Before
	public void setUp() {
		PowerMockito.mockStatic(
			ConfigurationFactoryUtil.class, PortletClassLoaderUtil.class,
			ServiceProps.class);

		new CalendarFactoryUtil().setCalendarFactory(new CalendarFactoryImpl());
	}

	@Test
	public void testExpandCalendarBooking() {
		CalendarBooking calendarBooking = new CalendarBookingImpl();

		int instanceCount = 3;

		long startTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		long endTime = getTime(_YEAR, _MONTH, _DAY, _HOUR + 2);
		long intervalStartTime = startTime;
		long intervalEndTime = getTime(_YEAR, _MONTH, _DAY + instanceCount, 23);

		calendarBooking.setStartTime(startTime);
		calendarBooking.setEndTime(endTime);
		calendarBooking.setRecurrence(
			"RRULE:FREQ=DAILY;COUNT=" + instanceCount + ";INTERVAL=1");

		List<CalendarBooking> instances = RecurrenceUtil.expandCalendarBooking(
			calendarBooking, intervalStartTime, intervalEndTime, instanceCount);

		Assert.assertEquals(instanceCount, instances.size());

		CalendarBooking instance = instances.get(0);
		long expectedStartTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		Assert.assertEquals(expectedStartTime, instance.getStartTime());

		instance = instances.get(1);
		expectedStartTime = getTime(_YEAR, _MONTH, _DAY + 1, _HOUR);
		Assert.assertEquals(expectedStartTime, instance.getStartTime());

		instance = instances.get(2);
		expectedStartTime = getTime(_YEAR, _MONTH, _DAY + 2, _HOUR);
		Assert.assertEquals(expectedStartTime, instance.getStartTime());
	}

	@Test
	public void testExpandCalendarBookingSetInstanceIndex() {
		CalendarBooking calendarBooking = new CalendarBookingImpl();

		int instanceCount = 5;

		long startTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		long endTime = getTime(_YEAR, _MONTH, _DAY, _HOUR + 2);
		long intervalStartTime = startTime;
		long intervalEndTime = getTime(_YEAR, _MONTH, _DAY + instanceCount, 23);

		calendarBooking.setStartTime(startTime);
		calendarBooking.setEndTime(endTime);
		calendarBooking.setRecurrence(
			"RRULE:FREQ=DAILY;COUNT=" + instanceCount + ";INTERVAL=1");

		List<CalendarBooking> instances = RecurrenceUtil.expandCalendarBooking(
			calendarBooking, intervalStartTime, intervalEndTime, instanceCount);

		Assert.assertEquals(instanceCount, instances.size());

		CalendarBooking instance = instances.get(0);
		Assert.assertEquals(0, instance.getInstanceIndex());

		instance = instances.get(1);
		Assert.assertEquals(1, instance.getInstanceIndex());

		instance = instances.get(2);
		Assert.assertEquals(2, instance.getInstanceIndex());

		instance = instances.get(3);
		Assert.assertEquals(3, instance.getInstanceIndex());

		instance = instances.get(4);
		Assert.assertEquals(4, instance.getInstanceIndex());
	}

	@Test
	public void testExpandCalendarBookingSetInstanceIndexWithoutFirst() {
		CalendarBooking calendarBooking = new CalendarBookingImpl();

		int instanceCount = 5;
		int intervalShift = 2;

		long startTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		long endTime = getTime(_YEAR, _MONTH, _DAY, _HOUR + 2);

		long intervalStartTime = getTime(
			_YEAR, _MONTH, _DAY + intervalShift, 0);
		long intervalEndTime = getTime(_YEAR, _MONTH, _DAY + instanceCount, 23);

		calendarBooking.setStartTime(startTime);
		calendarBooking.setEndTime(endTime);
		calendarBooking.setRecurrence(
			"RRULE:FREQ=DAILY;COUNT=" + instanceCount + ";INTERVAL=1");

		List<CalendarBooking> instances = RecurrenceUtil.expandCalendarBooking(
			calendarBooking, intervalStartTime, intervalEndTime, instanceCount);

		Assert.assertEquals(instanceCount - intervalShift, instances.size());

		CalendarBooking instance = instances.get(0);
		Assert.assertEquals(2, instance.getInstanceIndex());

		instance = instances.get(1);
		Assert.assertEquals(3, instance.getInstanceIndex());

		instance = instances.get(2);
		Assert.assertEquals(4, instance.getInstanceIndex());
	}

	@Test
	public void testExpandCalendarBookingWithoutRecurrence() {
		CalendarBooking calendarBooking = new CalendarBookingImpl();

		int dummyCount = 5;

		long startTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		long endTime = getTime(_YEAR, _MONTH, _DAY, _HOUR + 2);
		long intervalStartTime = startTime;
		long intervalEndTime = getTime(_YEAR, _MONTH, _DAY + dummyCount, 23);

		calendarBooking.setStartTime(startTime);
		calendarBooking.setEndTime(endTime);

		List<CalendarBooking> instances = RecurrenceUtil.expandCalendarBooking(
			calendarBooking, intervalStartTime, intervalEndTime, dummyCount);

		Assert.assertEquals(1, instances.size());

		CalendarBooking instance = instances.get(0);
		long expectedStartTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		Assert.assertEquals(expectedStartTime, instance.getStartTime());
	}

	@Test
	public void testGetCalendarBookingInstance() {
		CalendarBooking calendarBooking = new CalendarBookingImpl();

		int instanceCount = 5;
		int instanceIndex = 3;

		long startTime = getTime(_YEAR, _MONTH, _DAY, _HOUR);
		long endTime = getTime(_YEAR, _MONTH, _DAY, _HOUR + 2);

		calendarBooking.setStartTime(startTime);
		calendarBooking.setEndTime(endTime);
		calendarBooking.setRecurrence(
			"RRULE:FREQ=DAILY;COUNT=" + instanceCount + ";INTERVAL=1");

		CalendarBooking instance = RecurrenceUtil.getCalendarBookingInstance(
			calendarBooking, instanceIndex);

		long expectedStartTime = getTime(
			_YEAR, _MONTH, _DAY + instanceIndex, _HOUR);

		Assert.assertEquals(instanceIndex, instance.getInstanceIndex());
		Assert.assertEquals(expectedStartTime, instance.getStartTime());
	}

	private static long getTime(int year, int month, int day, int hour) {
		Calendar calendar = CalendarFactoryUtil.getCalendar(
			year, month, day, hour, 0);

		return calendar.getTimeInMillis();
	}

	private static final int _DAY = 18;

	private static final int _HOUR = 10;

	private static final int _MONTH = 5;

	private static final int _YEAR = 2014;

}