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

package com.liferay.calendar.model;

import com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class CalendarNotificationTemplateClp extends BaseModelImpl<CalendarNotificationTemplate>
	implements CalendarNotificationTemplate {
	public CalendarNotificationTemplateClp() {
	}

	public Class<?> getModelClass() {
		return CalendarNotificationTemplate.class;
	}

	public String getModelClassName() {
		return CalendarNotificationTemplate.class.getName();
	}

	public long getPrimaryKey() {
		return _calendarNotificationTemplateId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCalendarNotificationTemplateId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _calendarNotificationTemplateId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarNotificationTemplateId",
			getCalendarNotificationTemplateId());
		attributes.put("calendarId", getCalendarId());
		attributes.put("notificationType", getNotificationType());
		attributes.put("notificationTemplateType", getNotificationTemplateType());
		attributes.put("body", getBody());
		attributes.put("subject", getSubject());
		attributes.put("notificationSettings", getNotificationSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long calendarNotificationTemplateId = (Long)attributes.get(
				"calendarNotificationTemplateId");

		if (calendarNotificationTemplateId != null) {
			setCalendarNotificationTemplateId(calendarNotificationTemplateId);
		}

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
		}

		String notificationType = (String)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String notificationTemplateType = (String)attributes.get(
				"notificationTemplateType");

		if (notificationTemplateType != null) {
			setNotificationTemplateType(notificationTemplateType);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String notificationSettings = (String)attributes.get(
				"notificationSettings");

		if (notificationSettings != null) {
			setNotificationSettings(notificationSettings);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCalendarNotificationTemplateId() {
		return _calendarNotificationTemplateId;
	}

	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId) {
		_calendarNotificationTemplateId = calendarNotificationTemplateId;
	}

	public long getCalendarId() {
		return _calendarId;
	}

	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;
	}

	public String getNotificationType() {
		return _notificationType;
	}

	public void setNotificationType(String notificationType) {
		_notificationType = notificationType;
	}

	public String getNotificationTemplateType() {
		return _notificationTemplateType;
	}

	public void setNotificationTemplateType(String notificationTemplateType) {
		_notificationTemplateType = notificationTemplateType;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getNotificationSettings() {
		return _notificationSettings;
	}

	public void setNotificationSettings(String notificationSettings) {
		_notificationSettings = notificationSettings;
	}

	public java.util.Properties getNotificationSettingsProperties() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getCalendarNotificationTemplateRemoteModel() {
		return _calendarNotificationTemplateRemoteModel;
	}

	public void setCalendarNotificationTemplateRemoteModel(
		BaseModel<?> calendarNotificationTemplateRemoteModel) {
		_calendarNotificationTemplateRemoteModel = calendarNotificationTemplateRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			CalendarNotificationTemplateLocalServiceUtil.addCalendarNotificationTemplate(this);
		}
		else {
			CalendarNotificationTemplateLocalServiceUtil.updateCalendarNotificationTemplate(this);
		}
	}

	@Override
	public CalendarNotificationTemplate toEscapedModel() {
		return (CalendarNotificationTemplate)ProxyUtil.newProxyInstance(CalendarNotificationTemplate.class.getClassLoader(),
			new Class[] { CalendarNotificationTemplate.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CalendarNotificationTemplateClp clone = new CalendarNotificationTemplateClp();

		clone.setUuid(getUuid());
		clone.setCalendarNotificationTemplateId(getCalendarNotificationTemplateId());
		clone.setCalendarId(getCalendarId());
		clone.setNotificationType(getNotificationType());
		clone.setNotificationTemplateType(getNotificationTemplateType());
		clone.setBody(getBody());
		clone.setSubject(getSubject());
		clone.setNotificationSettings(getNotificationSettings());

		return clone;
	}

	public int compareTo(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		long primaryKey = calendarNotificationTemplate.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		CalendarNotificationTemplateClp calendarNotificationTemplate = null;

		try {
			calendarNotificationTemplate = (CalendarNotificationTemplateClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = calendarNotificationTemplate.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarNotificationTemplateId=");
		sb.append(getCalendarNotificationTemplateId());
		sb.append(", calendarId=");
		sb.append(getCalendarId());
		sb.append(", notificationType=");
		sb.append(getNotificationType());
		sb.append(", notificationTemplateType=");
		sb.append(getNotificationTemplateType());
		sb.append(", body=");
		sb.append(getBody());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", notificationSettings=");
		sb.append(getNotificationSettings());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarNotificationTemplate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarNotificationTemplateId</column-name><column-value><![CDATA[");
		sb.append(getCalendarNotificationTemplateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarId</column-name><column-value><![CDATA[");
		sb.append(getCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationType</column-name><column-value><![CDATA[");
		sb.append(getNotificationType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationTemplateType</column-name><column-value><![CDATA[");
		sb.append(getNotificationTemplateType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationSettings</column-name><column-value><![CDATA[");
		sb.append(getNotificationSettings());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _calendarNotificationTemplateId;
	private long _calendarId;
	private String _notificationType;
	private String _notificationTemplateType;
	private String _body;
	private String _subject;
	private String _notificationSettings;
	private BaseModel<?> _calendarNotificationTemplateRemoteModel;
}