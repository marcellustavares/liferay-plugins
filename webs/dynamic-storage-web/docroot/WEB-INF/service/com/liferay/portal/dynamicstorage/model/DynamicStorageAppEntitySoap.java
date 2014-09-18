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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Marcellus Tavares
 * @generated
 */
@ProviderType
public class DynamicStorageAppEntitySoap implements Serializable {
	public static DynamicStorageAppEntitySoap toSoapModel(
		DynamicStorageAppEntity model) {
		DynamicStorageAppEntitySoap soapModel = new DynamicStorageAppEntitySoap();

		soapModel.setAppEntityId(model.getAppEntityId());
		soapModel.setAppId(model.getAppId());
		soapModel.setEntityName(model.getEntityName());
		soapModel.setDDMStructureId(model.getDDMStructureId());

		return soapModel;
	}

	public static DynamicStorageAppEntitySoap[] toSoapModels(
		DynamicStorageAppEntity[] models) {
		DynamicStorageAppEntitySoap[] soapModels = new DynamicStorageAppEntitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DynamicStorageAppEntitySoap[][] toSoapModels(
		DynamicStorageAppEntity[][] models) {
		DynamicStorageAppEntitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DynamicStorageAppEntitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new DynamicStorageAppEntitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DynamicStorageAppEntitySoap[] toSoapModels(
		List<DynamicStorageAppEntity> models) {
		List<DynamicStorageAppEntitySoap> soapModels = new ArrayList<DynamicStorageAppEntitySoap>(models.size());

		for (DynamicStorageAppEntity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DynamicStorageAppEntitySoap[soapModels.size()]);
	}

	public DynamicStorageAppEntitySoap() {
	}

	public long getPrimaryKey() {
		return _appEntityId;
	}

	public void setPrimaryKey(long pk) {
		setAppEntityId(pk);
	}

	public long getAppEntityId() {
		return _appEntityId;
	}

	public void setAppEntityId(long appEntityId) {
		_appEntityId = appEntityId;
	}

	public long getAppId() {
		return _appId;
	}

	public void setAppId(long appId) {
		_appId = appId;
	}

	public String getEntityName() {
		return _entityName;
	}

	public void setEntityName(String entityName) {
		_entityName = entityName;
	}

	public long getDDMStructureId() {
		return _DDMStructureId;
	}

	public void setDDMStructureId(long DDMStructureId) {
		_DDMStructureId = DDMStructureId;
	}

	private long _appEntityId;
	private long _appId;
	private String _entityName;
	private long _DDMStructureId;
}