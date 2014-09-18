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
 * Provides the local service utility for DynamicStorageAppEntity. This utility wraps
 * {@link com.liferay.portal.dynamicstorage.service.impl.DynamicStorageAppEntityLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marcellus Tavares
 * @see DynamicStorageAppEntityLocalService
 * @see com.liferay.portal.dynamicstorage.service.base.DynamicStorageAppEntityLocalServiceBaseImpl
 * @see com.liferay.portal.dynamicstorage.service.impl.DynamicStorageAppEntityLocalServiceImpl
 * @generated
 */
@ProviderType
public class DynamicStorageAppEntityLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.dynamicstorage.service.impl.DynamicStorageAppEntityLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity addAppEntity(
		long appId, java.lang.String entityName, long ddmStructureId) {
		return getService().addAppEntity(appId, entityName, ddmStructureId);
	}

	/**
	* Adds the dynamic storage app entity to the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	* @return the dynamic storage app entity that was added
	*/
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity addDynamicStorageAppEntity(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return getService().addDynamicStorageAppEntity(dynamicStorageAppEntity);
	}

	/**
	* Creates a new dynamic storage app entity with the primary key. Does not add the dynamic storage app entity to the database.
	*
	* @param appEntityId the primary key for the new dynamic storage app entity
	* @return the new dynamic storage app entity
	*/
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity createDynamicStorageAppEntity(
		long appEntityId) {
		return getService().createDynamicStorageAppEntity(appEntityId);
	}

	/**
	* Deletes the dynamic storage app entity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity that was removed
	* @throws PortalException if a dynamic storage app entity with the primary key could not be found
	*/
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity deleteDynamicStorageAppEntity(
		long appEntityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDynamicStorageAppEntity(appEntityId);
	}

	/**
	* Deletes the dynamic storage app entity from the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	* @return the dynamic storage app entity that was removed
	*/
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity deleteDynamicStorageAppEntity(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return getService()
				   .deleteDynamicStorageAppEntity(dynamicStorageAppEntity);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fectAppEntity(
		long appId, java.lang.String entityName) {
		return getService().fectAppEntity(appId, entityName);
	}

	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fetchDynamicStorageAppEntity(
		long appEntityId) {
		return getService().fetchDynamicStorageAppEntity(appEntityId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Returns a range of all the dynamic storage app entities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic storage app entities
	* @param end the upper bound of the range of dynamic storage app entities (not inclusive)
	* @return the range of dynamic storage app entities
	*/
	public static java.util.List<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> getDynamicStorageAppEntities(
		int start, int end) {
		return getService().getDynamicStorageAppEntities(start, end);
	}

	/**
	* Returns the number of dynamic storage app entities.
	*
	* @return the number of dynamic storage app entities
	*/
	public static int getDynamicStorageAppEntitiesCount() {
		return getService().getDynamicStorageAppEntitiesCount();
	}

	/**
	* Returns the dynamic storage app entity with the primary key.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity
	* @throws PortalException if a dynamic storage app entity with the primary key could not be found
	*/
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity getDynamicStorageAppEntity(
		long appEntityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDynamicStorageAppEntity(appEntityId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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

	/**
	* Updates the dynamic storage app entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	* @return the dynamic storage app entity that was updated
	*/
	public static com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity updateDynamicStorageAppEntity(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return getService()
				   .updateDynamicStorageAppEntity(dynamicStorageAppEntity);
	}

	public static void clearService() {
		_service = null;
	}

	public static DynamicStorageAppEntityLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DynamicStorageAppEntityLocalService.class.getName());

			if (invokableLocalService instanceof DynamicStorageAppEntityLocalService) {
				_service = (DynamicStorageAppEntityLocalService)invokableLocalService;
			}
			else {
				_service = new DynamicStorageAppEntityLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DynamicStorageAppEntityLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(DynamicStorageAppEntityLocalService service) {
	}

	private static DynamicStorageAppEntityLocalService _service;
}