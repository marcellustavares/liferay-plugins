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

package com.liferay.portal.dynamicstorage.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.dynamicstorage.service.DynamicStoragePersistenceServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portal.dynamicstorage.service.DynamicStoragePersistenceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Marcellus Tavares
 * @see DynamicStoragePersistenceServiceHttp
 * @see com.liferay.portal.dynamicstorage.service.DynamicStoragePersistenceServiceUtil
 * @generated
 */
@ProviderType
public class DynamicStoragePersistenceServiceSoap {
	public static long create(long appId, java.lang.String entityName,
		java.lang.String data) throws RemoteException {
		try {
			long returnValue = DynamicStoragePersistenceServiceUtil.create(appId,
					entityName, data);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void delete(long appId, java.lang.String entityName,
		long entityId) throws RemoteException {
		try {
			DynamicStoragePersistenceServiceUtil.delete(appId, entityName,
				entityId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DynamicStoragePersistenceServiceSoap.class);
}