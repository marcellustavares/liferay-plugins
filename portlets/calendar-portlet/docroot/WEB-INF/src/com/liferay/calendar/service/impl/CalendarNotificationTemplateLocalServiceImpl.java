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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.notification.NotificationField;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the calendar notification template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.calendar.service.CalendarNotificationTemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl
 * @see com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil
 */
public class CalendarNotificationTemplateLocalServiceImpl
	extends CalendarNotificationTemplateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil} to access the calendar notification template local service.
	 */
	
	public CalendarNotificationTemplate getCalendarNotificationTemplate(
			long calendarId, NotificationType notificationType,
			NotificationTemplateType templateType,
			NotificationField field) 
		throws SystemException {
		
		return calendarNotificationTemplatePersistence.fetchByC_NT_NTT_F(
				calendarId, notificationType.getValue(),
				templateType.getValue(), field.getValue());
	}
	
	public CalendarNotificationTemplate addCalendarNotificationTemplate(
			long calendarId, NotificationType notificationType,
			NotificationTemplateType templateType,
			NotificationField field, String content) 
		throws SystemException {
		
		long templateId = counterLocalService.increment();
		CalendarNotificationTemplate template = 
				calendarNotificationTemplatePersistence.create(templateId);
		
		template.setCalendarId(calendarId);
		template.setNotificationType(notificationType.getValue());
		template.setNotificationTemplateType(templateType.getValue());
		template.setField(field.getValue());
		template.setContent(content);
		
		
		return calendarNotificationTemplatePersistence.update(template);
	}
}