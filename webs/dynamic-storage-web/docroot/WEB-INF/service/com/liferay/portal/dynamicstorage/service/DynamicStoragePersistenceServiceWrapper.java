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
 * Provides a wrapper for {@link DynamicStoragePersistenceService}.
 *
 * @author Marcellus Tavares
 * @see DynamicStoragePersistenceService
 * @generated
 */
@ProviderType
public class DynamicStoragePersistenceServiceWrapper
	implements DynamicStoragePersistenceService,
		ServiceWrapper<DynamicStoragePersistenceService> {
	public DynamicStoragePersistenceServiceWrapper(
		DynamicStoragePersistenceService dynamicStoragePersistenceService) {
		_dynamicStoragePersistenceService = dynamicStoragePersistenceService;
	}

	@Override
	public long create(long appId, java.lang.String entityName,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicStoragePersistenceService.create(appId, entityName, data);
	}

	@Override
	public void delete(long appId, java.lang.String entityName, long entityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dynamicStoragePersistenceService.delete(appId, entityName, entityId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dynamicStoragePersistenceService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dynamicStoragePersistenceService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dynamicStoragePersistenceService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public DynamicStoragePersistenceService getWrappedDynamicStoragePersistenceService() {
		return _dynamicStoragePersistenceService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedDynamicStoragePersistenceService(
		DynamicStoragePersistenceService dynamicStoragePersistenceService) {
		_dynamicStoragePersistenceService = dynamicStoragePersistenceService;
	}

	@Override
	public DynamicStoragePersistenceService getWrappedService() {
		return _dynamicStoragePersistenceService;
	}

	@Override
	public void setWrappedService(
		DynamicStoragePersistenceService dynamicStoragePersistenceService) {
		_dynamicStoragePersistenceService = dynamicStoragePersistenceService;
	}

	private DynamicStoragePersistenceService _dynamicStoragePersistenceService;
}