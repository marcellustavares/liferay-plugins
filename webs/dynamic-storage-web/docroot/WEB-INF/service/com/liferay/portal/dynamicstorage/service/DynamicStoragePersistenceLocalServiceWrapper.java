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

package com.liferay.portal.dynamicstorage.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DynamicStoragePersistenceLocalService}.
 *
 * @author Marcellus Tavares
 * @see DynamicStoragePersistenceLocalService
 * @generated
 */
@ProviderType
public class DynamicStoragePersistenceLocalServiceWrapper
	implements DynamicStoragePersistenceLocalService,
		ServiceWrapper<DynamicStoragePersistenceLocalService> {
	public DynamicStoragePersistenceLocalServiceWrapper(
		DynamicStoragePersistenceLocalService dynamicStoragePersistenceLocalService) {
		_dynamicStoragePersistenceLocalService = dynamicStoragePersistenceLocalService;
	}

	@Override
	public long create(long userId, long appId, java.lang.String entityName,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicStoragePersistenceLocalService.create(userId, appId,
			entityName, data);
	}

	@Override
	public void delete(long entityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dynamicStoragePersistenceLocalService.delete(entityId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dynamicStoragePersistenceLocalService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dynamicStoragePersistenceLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dynamicStoragePersistenceLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public DynamicStoragePersistenceLocalService getWrappedDynamicStoragePersistenceLocalService() {
		return _dynamicStoragePersistenceLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedDynamicStoragePersistenceLocalService(
		DynamicStoragePersistenceLocalService dynamicStoragePersistenceLocalService) {
		_dynamicStoragePersistenceLocalService = dynamicStoragePersistenceLocalService;
	}

	@Override
	public DynamicStoragePersistenceLocalService getWrappedService() {
		return _dynamicStoragePersistenceLocalService;
	}

	@Override
	public void setWrappedService(
		DynamicStoragePersistenceLocalService dynamicStoragePersistenceLocalService) {
		_dynamicStoragePersistenceLocalService = dynamicStoragePersistenceLocalService;
	}

	private DynamicStoragePersistenceLocalService _dynamicStoragePersistenceLocalService;
}