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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DynamicStoragePersistence. This utility wraps
 * {@link com.liferay.portal.dynamicstorage.service.impl.DynamicStoragePersistenceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marcellus Tavares
 * @see DynamicStoragePersistenceLocalService
 * @see com.liferay.portal.dynamicstorage.service.base.DynamicStoragePersistenceLocalServiceBaseImpl
 * @see com.liferay.portal.dynamicstorage.service.impl.DynamicStoragePersistenceLocalServiceImpl
 * @generated
 */
@ProviderType
public class DynamicStoragePersistenceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.dynamicstorage.service.impl.DynamicStoragePersistenceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static long create(long userId, long appId,
		java.lang.String entityName, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().create(userId, appId, entityName, data);
	}

	public static void delete(long entityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().delete(entityId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void clearService() {
		_service = null;
	}

	public static DynamicStoragePersistenceLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DynamicStoragePersistenceLocalService.class.getName());

			if (invokableLocalService instanceof DynamicStoragePersistenceLocalService) {
				_service = (DynamicStoragePersistenceLocalService)invokableLocalService;
			}
			else {
				_service = new DynamicStoragePersistenceLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DynamicStoragePersistenceLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(DynamicStoragePersistenceLocalService service) {
	}

	private static DynamicStoragePersistenceLocalService _service;
}