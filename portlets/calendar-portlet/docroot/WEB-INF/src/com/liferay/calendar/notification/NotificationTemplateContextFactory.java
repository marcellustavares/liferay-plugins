/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.notification;

import java.text.Format;

import javax.portlet.PortletConfig;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.util.NotificationUtil;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Eduardo Lundgren
 */
public class NotificationTemplateContextFactory {

	public static NotificationTemplateContext getInstance(
			CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(calendarBooking.getUserId());

		return getInstance(calendarBooking, user);
	}

	public static NotificationTemplateContext getInstance(
			CalendarBooking calendarBooking, User user)
		throws PortalException, SystemException {

		Calendar calendar = getRootCalendar(calendarBooking);

		NotificationTemplateContext notificationTemplateContext =
			new NotificationTemplateContext();

		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			user.getLocale(), user.getTimeZone());

		long endTime = calendarBooking.getEndTime();

		notificationTemplateContext.setAttribute(
			"endTime", dateFormatDateTime.format(endTime));

		String fromAddress = 
				NotificationUtil.getNotificationSenderEmailAddress(calendar);
		String fromName = NotificationUtil.getNotificationSenderName(calendar);

		notificationTemplateContext.setAttribute("fromAddress", fromAddress);
		notificationTemplateContext.setAttribute("fromName", fromName);

		notificationTemplateContext.setAttribute(
			"location", calendarBooking.getLocation());

		Company company = CompanyLocalServiceUtil.getCompany(
			calendarBooking.getCompanyId());

		notificationTemplateContext.setAttribute(
			"portalUrl", company.getPortalURL(calendarBooking.getGroupId()));

		notificationTemplateContext.setAttribute(
			"portletName",
			LanguageUtil.get(
				getPortletConfig(), user.getLocale(),
				"javax.portlet.title.".concat(PortletKeys.CALENDAR)));

		long startTime = calendarBooking.getStartTime();

		notificationTemplateContext.setAttribute(
			"startTime", dateFormatDateTime.format(startTime));

		notificationTemplateContext.setAttribute(
			"title", calendarBooking.getTitle(user.getLocale()));
		notificationTemplateContext.setAttribute(
			"toAddress", user.getEmailAddress());
		notificationTemplateContext.setAttribute("toName", user.getFullName());

		notificationTemplateContext.setCompanyId(
			calendarBooking.getCompanyId());
		notificationTemplateContext.setGroupId(calendarBooking.getGroupId());
		notificationTemplateContext.setCalendarId(calendar.getCalendarId());

		return notificationTemplateContext;
	}

	private static Calendar getRootCalendar(CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		long currertId = calendarBooking.getCalendarBookingId();
		long parentId = calendarBooking.getParentCalendarBookingId();
		Calendar calendar;

		if (currertId == parentId || parentId == 0) {
			calendar = calendarBooking.getCalendar();
		} else {
			CalendarBooking parent = calendarBooking.getParentCalendarBooking();

			calendar = getRootCalendar(parent);
		}

		return calendar;
	}

	public static PortletConfig getPortletConfig() {
		return _portletConfig;
	}

	public static void setPortletConfig(PortletConfig portletConfig) {
		_portletConfig = portletConfig;
	}

	private static PortletConfig _portletConfig;

}