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

package com.liferay.portal.dynamicstorage.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DynamicStorageAppEntity}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see DynamicStorageAppEntity
 * @generated
 */
@ProviderType
public class DynamicStorageAppEntityWrapper implements DynamicStorageAppEntity,
	ModelWrapper<DynamicStorageAppEntity> {
	public DynamicStorageAppEntityWrapper(
		DynamicStorageAppEntity dynamicStorageAppEntity) {
		_dynamicStorageAppEntity = dynamicStorageAppEntity;
	}

	@Override
	public Class<?> getModelClass() {
		return DynamicStorageAppEntity.class;
	}

	@Override
	public String getModelClassName() {
		return DynamicStorageAppEntity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appEntityId", getAppEntityId());
		attributes.put("appId", getAppId());
		attributes.put("entityName", getEntityName());
		attributes.put("DDMStructureId", getDDMStructureId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long appEntityId = (Long)attributes.get("appEntityId");

		if (appEntityId != null) {
			setAppEntityId(appEntityId);
		}

		Long appId = (Long)attributes.get("appId");

		if (appId != null) {
			setAppId(appId);
		}

		String entityName = (String)attributes.get("entityName");

		if (entityName != null) {
			setEntityName(entityName);
		}

		Long DDMStructureId = (Long)attributes.get("DDMStructureId");

		if (DDMStructureId != null) {
			setDDMStructureId(DDMStructureId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DynamicStorageAppEntityWrapper((DynamicStorageAppEntity)_dynamicStorageAppEntity.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		return _dynamicStorageAppEntity.compareTo(dynamicStorageAppEntity);
	}

	/**
	* Returns the app entity ID of this dynamic storage app entity.
	*
	* @return the app entity ID of this dynamic storage app entity
	*/
	@Override
	public long getAppEntityId() {
		return _dynamicStorageAppEntity.getAppEntityId();
	}

	/**
	* Returns the app ID of this dynamic storage app entity.
	*
	* @return the app ID of this dynamic storage app entity
	*/
	@Override
	public long getAppId() {
		return _dynamicStorageAppEntity.getAppId();
	}

	/**
	* Returns the d d m structure ID of this dynamic storage app entity.
	*
	* @return the d d m structure ID of this dynamic storage app entity
	*/
	@Override
	public long getDDMStructureId() {
		return _dynamicStorageAppEntity.getDDMStructureId();
	}

	/**
	* Returns the entity name of this dynamic storage app entity.
	*
	* @return the entity name of this dynamic storage app entity
	*/
	@Override
	public java.lang.String getEntityName() {
		return _dynamicStorageAppEntity.getEntityName();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dynamicStorageAppEntity.getExpandoBridge();
	}

	/**
	* Returns the primary key of this dynamic storage app entity.
	*
	* @return the primary key of this dynamic storage app entity
	*/
	@Override
	public long getPrimaryKey() {
		return _dynamicStorageAppEntity.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dynamicStorageAppEntity.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _dynamicStorageAppEntity.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _dynamicStorageAppEntity.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _dynamicStorageAppEntity.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _dynamicStorageAppEntity.isNew();
	}

	@Override
	public void persist() {
		_dynamicStorageAppEntity.persist();
	}

	/**
	* Sets the app entity ID of this dynamic storage app entity.
	*
	* @param appEntityId the app entity ID of this dynamic storage app entity
	*/
	@Override
	public void setAppEntityId(long appEntityId) {
		_dynamicStorageAppEntity.setAppEntityId(appEntityId);
	}

	/**
	* Sets the app ID of this dynamic storage app entity.
	*
	* @param appId the app ID of this dynamic storage app entity
	*/
	@Override
	public void setAppId(long appId) {
		_dynamicStorageAppEntity.setAppId(appId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dynamicStorageAppEntity.setCachedModel(cachedModel);
	}

	/**
	* Sets the d d m structure ID of this dynamic storage app entity.
	*
	* @param DDMStructureId the d d m structure ID of this dynamic storage app entity
	*/
	@Override
	public void setDDMStructureId(long DDMStructureId) {
		_dynamicStorageAppEntity.setDDMStructureId(DDMStructureId);
	}

	/**
	* Sets the entity name of this dynamic storage app entity.
	*
	* @param entityName the entity name of this dynamic storage app entity
	*/
	@Override
	public void setEntityName(java.lang.String entityName) {
		_dynamicStorageAppEntity.setEntityName(entityName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dynamicStorageAppEntity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dynamicStorageAppEntity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dynamicStorageAppEntity.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_dynamicStorageAppEntity.setNew(n);
	}

	/**
	* Sets the primary key of this dynamic storage app entity.
	*
	* @param primaryKey the primary key of this dynamic storage app entity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dynamicStorageAppEntity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dynamicStorageAppEntity.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity> toCacheModel() {
		return _dynamicStorageAppEntity.toCacheModel();
	}

	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity toEscapedModel() {
		return new DynamicStorageAppEntityWrapper(_dynamicStorageAppEntity.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dynamicStorageAppEntity.toString();
	}

	@Override
	public com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity toUnescapedModel() {
		return new DynamicStorageAppEntityWrapper(_dynamicStorageAppEntity.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _dynamicStorageAppEntity.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DynamicStorageAppEntityWrapper)) {
			return false;
		}

		DynamicStorageAppEntityWrapper dynamicStorageAppEntityWrapper = (DynamicStorageAppEntityWrapper)obj;

		if (Validator.equals(_dynamicStorageAppEntity,
					dynamicStorageAppEntityWrapper._dynamicStorageAppEntity)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public DynamicStorageAppEntity getWrappedDynamicStorageAppEntity() {
		return _dynamicStorageAppEntity;
	}

	@Override
	public DynamicStorageAppEntity getWrappedModel() {
		return _dynamicStorageAppEntity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _dynamicStorageAppEntity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _dynamicStorageAppEntity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_dynamicStorageAppEntity.resetOriginalValues();
	}

	private final DynamicStorageAppEntity _dynamicStorageAppEntity;
}