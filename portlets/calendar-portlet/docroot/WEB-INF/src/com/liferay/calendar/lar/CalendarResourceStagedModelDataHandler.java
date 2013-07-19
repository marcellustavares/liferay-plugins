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

package com.liferay.calendar.lar;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Andrea Di Giorgi
 */
public class CalendarResourceStagedModelDataHandler
	extends BaseStagedModelDataHandler<CalendarResource> {

	public static final String[] CLASS_NAMES =
		{CalendarResource.class.getName()};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(CalendarResource calendarResource) {
		return calendarResource.getNameCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			CalendarResource calendarResource)
		throws Exception {

		Element calendarResourceElement =
			portletDataContext.getExportDataElement(calendarResource);

		for (Calendar calendar : calendarResource.getCalendars()) {
			StagedModelDataHandlerUtil.exportStagedModel(
				portletDataContext, calendar);

			portletDataContext.addReferenceElement(
				calendarResource, calendarResourceElement, calendar,
				PortletDataContext.REFERENCE_TYPE_STRONG, false);
		}

		portletDataContext.addClassedModel(
			calendarResourceElement,
			ExportImportPathUtil.getModelPath(calendarResource),
			calendarResource, CalendarPortletDataHandler.NAMESPACE);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			CalendarResource calendarResource)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarResource.getUserUuid());

		List<Element> calendarElements =
			portletDataContext.getReferenceDataElements(
				calendarResource, Calendar.class);

		for (Element calendarElement : calendarElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, calendarElement);
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarResource, CalendarPortletDataHandler.NAMESPACE);

		long classPK = 0;

		if (calendarResource.getClassNameId() ==
				PortalUtil.getClassNameId(Group.class)) {

			classPK = portletDataContext.getScopeGroupId();
		}

		CalendarResource importedCalendarResource = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarResource existingCalendarResource =
				CalendarResourceLocalServiceUtil.
					fetchCalendarResourceByUuidAndGroupId(
						calendarResource.getUuid(),
						portletDataContext.getScopeGroupId());

			if (existingCalendarResource == null) {
				serviceContext.setUuid(calendarResource.getUuid());

				importedCalendarResource =
					CalendarResourceLocalServiceUtil.addCalendarResource(
						userId, portletDataContext.getScopeGroupId(),
						calendarResource.getClassNameId(), classPK,
						calendarResource.getClassUuid(),
						calendarResource.getCode(),
						calendarResource.getNameMap(),
						calendarResource.getDescriptionMap(),
						calendarResource.isActive(), serviceContext);
			}
			else {
				importedCalendarResource =
					CalendarResourceLocalServiceUtil.updateCalendarResource(
						existingCalendarResource.getCalendarResourceId(),
						calendarResource.getNameMap(),
						calendarResource.getDescriptionMap(),
						calendarResource.isActive(), serviceContext);
			}
		}
		else {
			importedCalendarResource =
				CalendarResourceLocalServiceUtil.addCalendarResource(
					userId, portletDataContext.getScopeGroupId(),
					calendarResource.getClassNameId(), classPK,
					calendarResource.getClassUuid(), calendarResource.getCode(),
					calendarResource.getNameMap(),
					calendarResource.getDescriptionMap(),
					calendarResource.isActive(), serviceContext);
		}

		updateCalendarDependencies(
			portletDataContext, calendarResource, importedCalendarResource);

		portletDataContext.importClassedModel(
			calendarResource, importedCalendarResource,
			CalendarPortletDataHandler.NAMESPACE);
	}

	protected void updateCalendarDependencies(
			PortletDataContext portletDataContext,
			CalendarResource calendarResource,
			CalendarResource importedCalendarResource)
		throws PortalException, SystemException {

		Element calendarResourceElement =
			portletDataContext.getImportDataStagedModelElement(
				calendarResource);

		Map<Long, Long> calendarIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Calendar.class);

		Element referencesElement = calendarResourceElement.element(
			"references");

		List<Element> referenceElements = referencesElement.elements();

		for (int i = 0; i < referenceElements.size(); i++) {
			Element calendarElement = referenceElements.get(i);

			long calendarId = GetterUtil.getLong(
				calendarElement.attributeValue("class-pk"));

			Calendar calendar = CalendarLocalServiceUtil.getCalendar(
				MapUtil.getLong(calendarIds, calendarId));

			calendar.setCalendarResourceId(
				importedCalendarResource.getCalendarResourceId());

			CalendarLocalServiceUtil.updateCalendar(calendar);
		}
	}

}