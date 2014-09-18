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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the dynamic storage app entity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marcellus Tavares
 * @see DynamicStorageAppEntityPersistenceImpl
 * @see DynamicStorageAppEntityUtil
 * @generated
 */
@ProviderType
public interface DynamicStorageAppEntityPersistence extends BasePersistence<DynamicStorageAppEntity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DynamicStorageAppEntityUtil} to access the dynamic storage app entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the dynamic storage app entity where appId = &#63; and entityName = &#63; or throws a {@link com.liferay.portal.dynamicstorage.NoSuchAppEntityException} if it could not be found.
	*
	* @param appId the app ID
	* @param entityName the entity name
	* @return the matching dynamic storage app entity
	* @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a matching dynamic storage app entity could not be found
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity findByA_E(
		long appId, java.lang.String entityName)
		throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException;

	/**
	* Returns the dynamic storage app entity where appId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param appId the app ID
	* @param entityName the entity name
	* @return the matching dynamic storage app entity, or <code>null</code> if a matching dynamic storage app entity could not be found
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fetchByA_E(
		long appId, java.lang.String entityName);

	/**
	* Returns the dynamic storage app entity where appId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param appId the app ID
	* @param entityName the entity name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching dynamic storage app entity, or <code>null</code> if a matching dynamic storage app entity could not be found
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fetchByA_E(
		long appId, java.lang.String entityName, boolean retrieveFromCache);

	/**
	* Removes the dynamic storage app entity where appId = &#63; and entityName = &#63; from the database.
	*
	* @param appId the app ID
	* @param entityName the entity name
	* @return the dynamic storage app entity that was removed
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity removeByA_E(
		long appId, java.lang.String entityName)
		throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException;

	/**
	* Returns the number of dynamic storage app entities where appId = &#63; and entityName = &#63;.
	*
	* @param appId the app ID
	* @param entityName the entity name
	* @return the number of matching dynamic storage app entities
	*/
	public int countByA_E(long appId, java.lang.String entityName);

	/**
	* Caches the dynamic storage app entity in the entity cache if it is enabled.
	*
	* @param dynamicStorageAppEntity the dynamic storage app entity
	*/
	public void cacheResult(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity);

	/**
	* Caches the dynamic storage app entities in the entity cache if it is enabled.
	*
	* @param dynamicStorageAppEntities the dynamic storage app entities
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> dynamicStorageAppEntities);

	/**
	* Creates a new dynamic storage app entity with the primary key. Does not add the dynamic storage app entity to the database.
	*
	* @param appEntityId the primary key for the new dynamic storage app entity
	* @return the new dynamic storage app entity
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity create(
		long appEntityId);

	/**
	* Removes the dynamic storage app entity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity that was removed
	* @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a dynamic storage app entity with the primary key could not be found
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity remove(
		long appEntityId)
		throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException;

	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity updateImpl(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity);

	/**
	* Returns the dynamic storage app entity with the primary key or throws a {@link com.liferay.portal.dynamicstorage.NoSuchAppEntityException} if it could not be found.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity
	* @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a dynamic storage app entity with the primary key could not be found
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity findByPrimaryKey(
		long appEntityId)
		throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException;

	/**
	* Returns the dynamic storage app entity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appEntityId the primary key of the dynamic storage app entity
	* @return the dynamic storage app entity, or <code>null</code> if a dynamic storage app entity with the primary key could not be found
	*/
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity fetchByPrimaryKey(
		long appEntityId);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dynamic storage app entities.
	*
	* @return the dynamic storage app entities
	*/
	public java.util.List<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> findAll();

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
	public java.util.List<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the dynamic storage app entities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic storage app entities
	* @param end the upper bound of the range of dynamic storage app entities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dynamic storage app entities
	*/
	public java.util.List<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> orderByComparator);

	/**
	* Removes all the dynamic storage app entities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dynamic storage app entities.
	*
	* @return the number of dynamic storage app entities
	*/
	public int countAll();
}