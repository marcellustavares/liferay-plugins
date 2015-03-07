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

package com.liferay.calendar.hook.upgrade.v1_0_1;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class UpgradeCalendarBookingTest {

	@Test
	public void testDoUpgradeCreatesVEventId() throws Exception {
		UpgradeCalendarBooking upgradeCalendarBooking =
			new UpgradeCalendarBooking();

		if (upgradeCalendarBooking.tableHasColumn(
				"CalendarBooking", "vEventUid")) {

			upgradeCalendarBooking.runSQL(
				"drop index IX_8B23DA0E on CalendarBooking");
			upgradeCalendarBooking.runSQL(
				"alter table CalendarBooking drop vEventUid");
		}

		upgradeCalendarBooking.doUpgrade();

		Assert.assertTrue(
			upgradeCalendarBooking.tableHasColumn(
				"CalendarBooking", "vEventUid"));
	}

}