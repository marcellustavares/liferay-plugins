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

package com.liferay.portal.dynamicstorage.service.persistence;

import com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity;
import com.liferay.portal.dynamicstorage.service.DynamicStorageAppEntityLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;

/**
 * @author Marcellus Tavares
 * @deprecated As of 7.0.0, replaced by {@link DynamicStorageAppEntityLocalServiceUtil#getActionableDynamicQuery()}
 * @generated
 */
@Deprecated
public abstract class DynamicStorageAppEntityActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public DynamicStorageAppEntityActionableDynamicQuery() {
		setBaseLocalService(DynamicStorageAppEntityLocalServiceUtil.getService());
		setClass(DynamicStorageAppEntity.class);

		setClassLoader(com.liferay.portal.dynamicstorage.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("appEntityId");
	}
}