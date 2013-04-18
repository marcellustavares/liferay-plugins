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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Eduardo Lundgren
 * @generated
 */
public class CalendarNotificationTemplateSoap implements Serializable {
	public static CalendarNotificationTemplateSoap toSoapModel(
		CalendarNotificationTemplate model) {
		CalendarNotificationTemplateSoap soapModel = new CalendarNotificationTemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCalendarNotificationTemplateId(model.getCalendarNotificationTemplateId());
		soapModel.setCalendarId(model.getCalendarId());
		soapModel.setNotificationType(model.getNotificationType());
		soapModel.setNotificationTemplateType(model.getNotificationTemplateType());
		soapModel.setBody(model.getBody());
		soapModel.setSubject(model.getSubject());
		soapModel.setNotificationSettings(model.getNotificationSettings());

		return soapModel;
	}

	public static CalendarNotificationTemplateSoap[] toSoapModels(
		CalendarNotificationTemplate[] models) {
		CalendarNotificationTemplateSoap[] soapModels = new CalendarNotificationTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalendarNotificationTemplateSoap[][] toSoapModels(
		CalendarNotificationTemplate[][] models) {
		CalendarNotificationTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalendarNotificationTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalendarNotificationTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalendarNotificationTemplateSoap[] toSoapModels(
		List<CalendarNotificationTemplate> models) {
		List<CalendarNotificationTemplateSoap> soapModels = new ArrayList<CalendarNotificationTemplateSoap>(models.size());

		for (CalendarNotificationTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalendarNotificationTemplateSoap[soapModels.size()]);
	}

	public CalendarNotificationTemplateSoap() {
	}

	public long getPrimaryKey() {
		return _calendarNotificationTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setCalendarNotificationTemplateId(pk);
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

	private String _uuid;
	private long _calendarNotificationTemplateId;
	private long _calendarId;
	private String _notificationType;
	private String _notificationTemplateType;
	private String _body;
	private String _subject;
	private String _notificationSettings;
}