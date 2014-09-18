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
 * Provides a wrapper for {@link DynamicStorageAppEntityLocalService}.
 *
 * @author Marcellus Tavares
 * @see DynamicStorageAppEntityLocalService
 * @generated
 */
@ProviderType
public class DynamicStorageAppEntityLocalServiceWrapper
	implements DynamicStorageAppEntityLocalService,
		ServiceWrapper<DynamicStorageAppEntityLocalService> {
	public DynamicStorageAppEntityLocalServiceWrapper(
		DynamicStorageAppEntityLocalService dynamicStorageAppEntityLocalService) {
		_dynamicStorageAppEntityLocalService = dynamicStorageAppEntityLocalService;
	}

	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity addAppEntity(
		long appId, java.lang.String entityName, long ddmStructureId) {
		return _dynamicStorageAppEntityLocalService.addAppEntity(appId,
			entityName, ddmStructureId);
	}

	/**
	* Adds the dynamic storage app entity to the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	* @return the dynamic storage app entity that was added
	*/
	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity addDynamicStorageAppEntity(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return _dynamicStorageAppEntityLocalService.addDynamicStorageAppEntity(dynamicStorageAppEntity);
	}

	/**
	* Creates a new dynamic storage app entity with the primary key. Does not add the dynamic storage app entity to the database.
	*
	* @param appEntityId the primary key for the new dynamic storage app entity
	* @return the new dynamic storage app entity
	*/
	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity createDynamicStorageAppEntity(
		long appEntityId) {
		return _dynamicStorageAppEntityLocalService.createDynamicStorageAppEntity(appEntityId);
	}

	/**
	* Deletes the dynamic storage app entity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity that was removed
	* @throws PortalException if a dynamic storage app entity with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity deleteDynamicStorageAppEntity(
		long appEntityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicStorageAppEntityLocalService.deleteDynamicStorageAppEntity(appEntityId);
	}

	/**
	* Deletes the dynamic storage app entity from the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	* @return the dynamic storage app entity that was removed
	*/
	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity deleteDynamicStorageAppEntity(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return _dynamicStorageAppEntityLocalService.deleteDynamicStorageAppEntity(dynamicStorageAppEntity);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicStorageAppEntityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dynamicStorageAppEntityLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _dynamicStorageAppEntityLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _dynamicStorageAppEntityLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _dynamicStorageAppEntityLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _dynamicStorageAppEntityLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _dynamicStorageAppEntityLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fectAppEntity(
		long appId, java.lang.String entityName) {
		return _dynamicStorageAppEntityLocalService.fectAppEntity(appId,
			entityName);
	}

	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fetchDynamicStorageAppEntity(
		long appEntityId) {
		return _dynamicStorageAppEntityLocalService.fetchDynamicStorageAppEntity(appEntityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dynamicStorageAppEntityLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dynamicStorageAppEntityLocalService.getBeanIdentifier();
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
	@Override
	public java.util.List<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> getDynamicStorageAppEntities(
		int start, int end) {
		return _dynamicStorageAppEntityLocalService.getDynamicStorageAppEntities(start,
			end);
	}

	/**
	* Returns the number of dynamic storage app entities.
	*
	* @return the number of dynamic storage app entities
	*/
	@Override
	public int getDynamicStorageAppEntitiesCount() {
		return _dynamicStorageAppEntityLocalService.getDynamicStorageAppEntitiesCount();
	}

	/**
	* Returns the dynamic storage app entity with the primary key.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity
	* @throws PortalException if a dynamic storage app entity with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity getDynamicStorageAppEntity(
		long appEntityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicStorageAppEntityLocalService.getDynamicStorageAppEntity(appEntityId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicStorageAppEntityLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dynamicStorageAppEntityLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dynamicStorageAppEntityLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the dynamic storage app entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	* @return the dynamic storage app entity that was updated
	*/
	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity updateDynamicStorageAppEntity(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return _dynamicStorageAppEntityLocalService.updateDynamicStorageAppEntity(dynamicStorageAppEntity);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public DynamicStorageAppEntityLocalService getWrappedDynamicStorageAppEntityLocalService() {
		return _dynamicStorageAppEntityLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedDynamicStorageAppEntityLocalService(
		DynamicStorageAppEntityLocalService dynamicStorageAppEntityLocalService) {
		_dynamicStorageAppEntityLocalService = dynamicStorageAppEntityLocalService;
	}

	@Override
	public DynamicStorageAppEntityLocalService getWrappedService() {
		return _dynamicStorageAppEntityLocalService;
	}

	@Override
	public void setWrappedService(
		DynamicStorageAppEntityLocalService dynamicStorageAppEntityLocalService) {
		_dynamicStorageAppEntityLocalService = dynamicStorageAppEntityLocalService;
	}

	private DynamicStorageAppEntityLocalService _dynamicStorageAppEntityLocalService;
}