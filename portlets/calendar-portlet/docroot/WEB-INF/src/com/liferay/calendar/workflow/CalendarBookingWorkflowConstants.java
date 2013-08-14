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

package com.liferay.calendar.workflow;

import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Michael C. Han
 * @author Eduardo Lundgren
 */
public class CalendarBookingWorkflowConstants extends WorkflowConstants {

	public static final String ACTION_ACCEPT = "accept";

	public static final String ACTION_DECLINE = "decline";

	public static final String ACTION_MAYBE = "maybe";

	public static final String LABEL_ACCEPTED = "accepted";

	public static final String LABEL_DECLINED = "declined";

	public static final String LABEL_MAYBE = "maybe";

	public static final int STATUS_MAYBE = 9;

	public static int getLabelStatus(String label) {
		if (label.equals(LABEL_ACCEPTED)) {
			return STATUS_APPROVED;
		}
		else if (label.equals(LABEL_DECLINED)) {
			return STATUS_DENIED;
		}
		else if (label.equals(LABEL_MAYBE)) {
			return STATUS_MAYBE;
		}
		else {
			return WorkflowConstants.getLabelStatus(label);
		}
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_APPROVED) {
			return LABEL_ACCEPTED;
		}
		else if (status == STATUS_DENIED) {
			return LABEL_DECLINED;
		}
		else if (status == STATUS_MAYBE) {
			return LABEL_MAYBE;
		}
		else {
			return WorkflowConstants.getStatusLabel(status);
		}
	}

}