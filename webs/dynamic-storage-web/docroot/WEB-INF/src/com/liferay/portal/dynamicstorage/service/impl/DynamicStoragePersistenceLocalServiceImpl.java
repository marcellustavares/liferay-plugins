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

package com.liferay.portal.dynamicstorage.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity;
import com.liferay.portal.dynamicstorage.service.base.DynamicStoragePersistenceLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;

import java.io.Serializable;

import java.util.Iterator;

/**
 * @author Marcellus Tavares
 */
@ProviderType
public class DynamicStoragePersistenceLocalServiceImpl
	extends DynamicStoragePersistenceLocalServiceBaseImpl {

	public long create(long userId, long appId, String entityName, String data)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		DynamicStorageAppEntity dynamicStorageAppEntity =
			dynamicStorageAppEntityLocalService.fectAppEntity(
				appId, entityName);

		Fields fields = getFields(
			dynamicStorageAppEntity.getDDMStructureId(), data);

		ServiceContext serviceContext = createServiceContext(user.getUserId());

		return StorageEngineUtil.create(
			user.getCompanyId(), dynamicStorageAppEntity.getDDMStructureId(),
			fields, serviceContext);
	}

	public void delete(long entityId) throws PortalException {
		StorageEngineUtil.deleteByClass(entityId);
	}

	protected ServiceContext createServiceContext(long userId) {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUserId(userId);
		serviceContext.setScopeGroupId(0);

		return serviceContext;
	}

	protected Fields getFields(long ddmStructureId, String data)
		throws PortalException {

		DDMStructure ddmStructure = ddmStructureLocalService.getDDMStructure(
			ddmStructureId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(data);

		Fields fields = new Fields();

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			Serializable value = getValue(
				ddmStructure, key, jsonObject.getString(key));

			Field field = new Field(ddmStructureId, key, value);

			fields.put(field);
		}

		return fields;
	}

	protected Serializable getValue(
			DDMStructure ddmStructure, String key, String value)
		throws PortalException {

		String fieldType = ddmStructure.getFieldType(key);

		return FieldConstants.getSerializable(fieldType, value);
	}

}