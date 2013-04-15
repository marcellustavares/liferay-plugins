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
		StringBundler sb = new StringBundler(15);

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
		sb.append(", field=");
		sb.append(field);
		sb.append(", content=");
		sb.append(content);
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

		if (field == null) {
			calendarNotificationTemplateImpl.setField(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setField(field);
		}

		if (content == null) {
			calendarNotificationTemplateImpl.setContent(StringPool.BLANK);
		}
		else {
			calendarNotificationTemplateImpl.setContent(content);
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
		field = objectInput.readUTF();
		content = objectInput.readUTF();
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

		if (field == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(field);
		}

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}
	}

	public String uuid;
	public long calendarNotificationTemplateId;
	public long calendarId;
	public String notificationType;
	public String notificationTemplateType;
	public String field;
	public String content;
}