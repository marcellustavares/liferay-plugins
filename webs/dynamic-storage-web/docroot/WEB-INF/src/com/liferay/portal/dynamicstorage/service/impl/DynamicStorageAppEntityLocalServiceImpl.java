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
import com.liferay.portal.dynamicstorage.service.base.DynamicStorageAppEntityLocalServiceBaseImpl;

/**
 * @author Marcellus Tavares
 */
@ProviderType
public class DynamicStorageAppEntityLocalServiceImpl
	extends DynamicStorageAppEntityLocalServiceBaseImpl {

	public DynamicStorageAppEntity addAppEntity(
		long appId, String entityName, long ddmStructureId) {

		long appEntityId = counterLocalService.increment();

		DynamicStorageAppEntity dynamicStorageAppEntity =
			dynamicStorageAppEntityPersistence.create(appEntityId);

		dynamicStorageAppEntity.setAppId(appId);
		dynamicStorageAppEntity.setEntityName(entityName);
		dynamicStorageAppEntity.setDDMStructureId(ddmStructureId);

		return dynamicStorageAppEntityPersistence.update(
			dynamicStorageAppEntity);
	}

	public DynamicStorageAppEntity fectAppEntity(
		long appId, String entityName) {

		return dynamicStorageAppEntityPersistence.fetchByA_E(appId, entityName);
	}

}