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

package com.liferay.portal.dynamicstorage.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.dynamicstorage.service.base.DynamicStorageRegistryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.io.DDMFormJSONDeserializerUtil;
import com.liferay.portlet.dynamicdatamapping.io.DDMFormXSDSerializerUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMForm;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
@ProviderType
public class DynamicStorageRegistryLocalServiceImpl
	extends DynamicStorageRegistryLocalServiceBaseImpl {

	public void registerEntity(
			long userId, long appId, String entityName, String entityStructure)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(
			"com.liferay.StandAloneApp");

		Map<Locale, String> nameMap = getLocalizationMap(entityName);

		String definition = getStructureDefinition(entityStructure);

		ServiceContext serviceContext = createServiceContext();

		DDMStructure ddmStructure = ddmStructureLocalService.addStructure(
			userId, 0, classNameId, nameMap, null, definition, serviceContext);

		dynamicStorageAppEntityLocalService.addAppEntity(
			appId, entityName, ddmStructure.getStructureId());
	}

	protected ServiceContext createServiceContext() {
		ServiceContext serviceContext = new ServiceContext();

		return serviceContext;
	}

	protected JSONArray getAvailableLanguageIds() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(getDefaultLanguageId());

		return jsonArray;
	}

	protected JSONObject getDDMFormJSONObject(String entityStructure)
		throws PortalException {

		JSONObject entityJSONObject = JSONFactoryUtil.createJSONObject(
			entityStructure);

		return toDDMStructure(entityJSONObject);
	}

	protected String getDefaultLanguageId() {
		Locale defaultLocale = LocaleUtil.getDefault();

		return LanguageUtil.getLanguageId(defaultLocale);
	}

	protected JSONArray getFields(JSONObject entityJSONObject) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Iterator<String> keys = entityJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", key);
			jsonObject.put("type", entityJSONObject.getString(key));
			jsonObject.put("label", getLabel(key));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected JSONObject getLabel(String label) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(getDefaultLanguageId(), label);

		return jsonObject;
	}

	protected Map<Locale, String> getLocalizationMap(String value) {
		Map<Locale, String> localizationMap = new HashMap<Locale, String>();

		localizationMap.put(LocaleUtil.getDefault(), value);

		return localizationMap;
	}

	protected String getStructureDefinition(String entityStructure)
		throws PortalException {

		JSONObject ddmFormJSONObject = getDDMFormJSONObject(entityStructure);

		DDMForm ddmForm = DDMFormJSONDeserializerUtil.deserialize(
			ddmFormJSONObject.toString());

		return DDMFormXSDSerializerUtil.serialize(ddmForm);
	}

	protected JSONObject toDDMStructure(JSONObject entityJSONObject) {
		JSONObject ddmFormJSONObject = JSONFactoryUtil.createJSONObject();

		ddmFormJSONObject.put(
			"availableLanguageIds", getAvailableLanguageIds());
		ddmFormJSONObject.put("defaultLanguageId", getDefaultLanguageId());

		ddmFormJSONObject.put("fields", getFields(entityJSONObject));

		return ddmFormJSONObject;
	}

}