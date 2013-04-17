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

package com.liferay.calendar.model.impl;

import com.liferay.calendar.model.CalendarNotificationTemplate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CalendarNotificationTemplate in entity cache.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplate
 * @generated
 */
public class CalendarNotificationTemplateCacheModel implements CacheModel<CalendarNotificationTemplate>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", calendarNotificationTemplateId=");
		sb.append(calendarNotificationTemplateId);
		sb.append(", calendarId=");
		sb.append(calendarId);
		sb.append(", notificationType=");
		sb.append(notificationType);
		sb.append(", notificationTemplateType=");
		sb.append(notificationTemplateType);
		sb.append(", body=");
		sb.append(body);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", notificationSettings=");
		sb.append(notificationSettings);
		sb.append("}");

		return sb.toString();
	}

	public CalendarNotificationTemplate toEntityModel() {
		CalendarNotificationTemplateImpl calendarNotificationTemplateImpl = new CalendarNotificationTemplateImpl();

		if (uuid == null) {
			calendarNotificationTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setUuid(uuid);
		}

		calendarNotificationTemplateImpl.setCalendarNotificationTemplateId(calendarNotificationTemplateId);
		calendarNotificationTemplateImpl.setCalendarId(calendarId);

		if (notificationType == null) {
			calendarNotificationTemplateImpl.setNotificationType(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setNotificationType(notificationType);
		}

		if (notificationTemplateType == null) {
			calendarNotificationTemplateImpl.setNotificationTemplateType(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setNotificationTemplateType(notificationTemplateType);
		}

		if (body == null) {
			calendarNotificationTemplateImpl.setBody(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setBody(body);
		}

		if (subject == null) {
			calendarNotificationTemplateImpl.setSubject(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setSubject(subject);
		}

		if (notificationSettings == null) {
			calendarNotificationTemplateImpl.setNotificationSettings(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setNotificationSettings(notificationSettings);
		}

		calendarNotificationTemplateImpl.resetOriginalValues();

		return calendarNotificationTemplateImpl;
	}

	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		calendarNotificationTemplateId = objectInput.readLong();
		calendarId = objectInput.readLong();
		notificationType = objectInput.readUTF();
		notificationTemplateType = objectInput.readUTF();
		body = objectInput.readUTF();
		subject = objectInput.readUTF();
		notificationSettings = objectInput.readUTF();
	}

	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(calendarNotificationTemplateId);
		objectOutput.writeLong(calendarId);

		if (notificationType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationType);
		}

		if (notificationTemplateType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationTemplateType);
		}

		if (body == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(body);
		}

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (notificationSettings == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationSettings);
		}
	}

	public String uuid;
	public long calendarNotificationTemplateId;
	public long calendarId;
	public String notificationType;
	public String notificationTemplateType;
	public String body;
	public String subject;
	public String notificationSettings;
}