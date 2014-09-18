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

import com.liferay.portal.dynamicstorage.service.ClpSerializer;
import com.liferay.portal.dynamicstorage.service.DynamicStorageAppEntityLocalServiceUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
@ProviderType
public class DynamicStorageAppEntityClp extends BaseModelImpl<DynamicStorageAppEntity>
	implements DynamicStorageAppEntity {
	public DynamicStorageAppEntityClp() {
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
	public long getPrimaryKey() {
		return _appEntityId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAppEntityId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _appEntityId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appEntityId", getAppEntityId());
		attributes.put("appId", getAppId());
		attributes.put("entityName", getEntityName());
		attributes.put("DDMStructureId", getDDMStructureId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAppEntityId() {
		return _appEntityId;
	}

	@Override
	public void setAppEntityId(long appEntityId) {
		_appEntityId = appEntityId;

		if (_dynamicStorageAppEntityRemoteModel != null) {
			try {
				Class<?> clazz = _dynamicStorageAppEntityRemoteModel.getClass();

				Method method = clazz.getMethod("setAppEntityId", long.class);

				method.invoke(_dynamicStorageAppEntityRemoteModel, appEntityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAppId() {
		return _appId;
	}

	@Override
	public void setAppId(long appId) {
		_appId = appId;

		if (_dynamicStorageAppEntityRemoteModel != null) {
			try {
				Class<?> clazz = _dynamicStorageAppEntityRemoteModel.getClass();

				Method method = clazz.getMethod("setAppId", long.class);

				method.invoke(_dynamicStorageAppEntityRemoteModel, appId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEntityName() {
		return _entityName;
	}

	@Override
	public void setEntityName(String entityName) {
		_entityName = entityName;

		if (_dynamicStorageAppEntityRemoteModel != null) {
			try {
				Class<?> clazz = _dynamicStorageAppEntityRemoteModel.getClass();

				Method method = clazz.getMethod("setEntityName", String.class);

				method.invoke(_dynamicStorageAppEntityRemoteModel, entityName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDDMStructureId() {
		return _DDMStructureId;
	}

	@Override
	public void setDDMStructureId(long DDMStructureId) {
		_DDMStructureId = DDMStructureId;

		if (_dynamicStorageAppEntityRemoteModel != null) {
			try {
				Class<?> clazz = _dynamicStorageAppEntityRemoteModel.getClass();

				Method method = clazz.getMethod("setDDMStructureId", long.class);

				method.invoke(_dynamicStorageAppEntityRemoteModel,
					DDMStructureId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getDynamicStorageAppEntityRemoteModel() {
		return _dynamicStorageAppEntityRemoteModel;
	}

	public void setDynamicStorageAppEntityRemoteModel(
		BaseModel<?> dynamicStorageAppEntityRemoteModel) {
		_dynamicStorageAppEntityRemoteModel = dynamicStorageAppEntityRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _dynamicStorageAppEntityRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_dynamicStorageAppEntityRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			DynamicStorageAppEntityLocalServiceUtil.addDynamicStorageAppEntity(this);
		}
		else {
			DynamicStorageAppEntityLocalServiceUtil.updateDynamicStorageAppEntity(this);
		}
	}

	@Override
	public DynamicStorageAppEntity toEscapedModel() {
		return (DynamicStorageAppEntity)ProxyUtil.newProxyInstance(DynamicStorageAppEntity.class.getClassLoader(),
			new Class[] { DynamicStorageAppEntity.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		DynamicStorageAppEntityClp clone = new DynamicStorageAppEntityClp();

		clone.setAppEntityId(getAppEntityId());
		clone.setAppId(getAppId());
		clone.setEntityName(getEntityName());
		clone.setDDMStructureId(getDDMStructureId());

		return clone;
	}

	@Override
	public int compareTo(DynamicStorageAppEntity dynamicStorageAppEntity) {
		long primaryKey = dynamicStorageAppEntity.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DynamicStorageAppEntityClp)) {
			return false;
		}

		DynamicStorageAppEntityClp dynamicStorageAppEntity = (DynamicStorageAppEntityClp)obj;

		long primaryKey = dynamicStorageAppEntity.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{appEntityId=");
		sb.append(getAppEntityId());
		sb.append(", appId=");
		sb.append(getAppId());
		sb.append(", entityName=");
		sb.append(getEntityName());
		sb.append(", DDMStructureId=");
		sb.append(getDDMStructureId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>appEntityId</column-name><column-value><![CDATA[");
		sb.append(getAppEntityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appId</column-name><column-value><![CDATA[");
		sb.append(getAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>entityName</column-name><column-value><![CDATA[");
		sb.append(getEntityName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMStructureId</column-name><column-value><![CDATA[");
		sb.append(getDDMStructureId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _appEntityId;
	private long _appId;
	private String _entityName;
	private long _DDMStructureId;
	private BaseModel<?> _dynamicStorageAppEntityRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.portal.dynamicstorage.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}