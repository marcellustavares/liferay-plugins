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
 * Provides a wrapper for {@link DynamicStorageRegistryService}.
 *
 * @author Marcellus Tavares
 * @see DynamicStorageRegistryService
 * @generated
 */
@ProviderType
public class DynamicStorageRegistryServiceWrapper
	implements DynamicStorageRegistryService,
		ServiceWrapper<DynamicStorageRegistryService> {
	public DynamicStorageRegistryServiceWrapper(
		DynamicStorageRegistryService dynamicStorageRegistryService) {
		_dynamicStorageRegistryService = dynamicStorageRegistryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dynamicStorageRegistryService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dynamicStorageRegistryService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public void registerEntity(long appId, java.lang.String entityName,
		java.lang.String entityStructure)
		throws com.liferay.portal.kernel.exception.PortalException {
		_dynamicStorageRegistryService.registerEntity(appId, entityName,
			entityStructure);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dynamicStorageRegistryService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public DynamicStorageRegistryService getWrappedDynamicStorageRegistryService() {
		return _dynamicStorageRegistryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedDynamicStorageRegistryService(
		DynamicStorageRegistryService dynamicStorageRegistryService) {
		_dynamicStorageRegistryService = dynamicStorageRegistryService;
	}

	@Override
	public DynamicStorageRegistryService getWrappedService() {
		return _dynamicStorageRegistryService;
	}

	@Override
	public void setWrappedService(
		DynamicStorageRegistryService dynamicStorageRegistryService) {
		_dynamicStorageRegistryService = dynamicStorageRegistryService;
	}

	private DynamicStorageRegistryService _dynamicStorageRegistryService;
}