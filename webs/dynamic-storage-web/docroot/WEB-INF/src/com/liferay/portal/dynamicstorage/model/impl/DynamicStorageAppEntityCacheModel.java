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

package com.liferay.portal.dynamicstorage.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DynamicStorageAppEntity in entity cache.
 *
 * @author Marcellus Tavares
 * @see DynamicStorageAppEntity
 * @generated
 */
@ProviderType
public class DynamicStorageAppEntityCacheModel implements CacheModel<DynamicStorageAppEntity>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{appEntityId=");
		sb.append(appEntityId);
		sb.append(", appId=");
		sb.append(appId);
		sb.append(", entityName=");
		sb.append(entityName);
		sb.append(", DDMStructureId=");
		sb.append(DDMStructureId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DynamicStorageAppEntity toEntityModel() {
		DynamicStorageAppEntityImpl dynamicStorageAppEntityImpl = new DynamicStorageAppEntityImpl();

		dynamicStorageAppEntityImpl.setAppEntityId(appEntityId);
		dynamicStorageAppEntityImpl.setAppId(appId);

		if (entityName == null) {
			dynamicStorageAppEntityImpl.setEntityName(StringPool.BLANK);
		}
		else {
			dynamicStorageAppEntityImpl.setEntityName(entityName);
		}

		dynamicStorageAppEntityImpl.setDDMStructureId(DDMStructureId);

		dynamicStorageAppEntityImpl.resetOriginalValues();

		return dynamicStorageAppEntityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		appEntityId = objectInput.readLong();
		appId = objectInput.readLong();
		entityName = objectInput.readUTF();
		DDMStructureId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(appEntityId);
		objectOutput.writeLong(appId);

		if (entityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityName);
		}

		objectOutput.writeLong(DDMStructureId);
	}

	public long appEntityId;
	public long appId;
	public String entityName;
	public long DDMStructureId;
}