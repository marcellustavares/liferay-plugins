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

/**
 * @author Eduardo Lundgren
 */
public enum NotificationField {

	SUBJECT("subject"), BODY("body");

	public static NotificationField parse(String value) {
		if (SUBJECT.getValue().equals(value)) {
			return SUBJECT;
		} else if (BODY.getValue().equals(value)) {
			return BODY;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private NotificationField(String value) {
		_value = value;
	}

	private String _value;

}