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

package com.liferay.portal.search.elasticsearch.document;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class DefaultElasticsearchDocumentFactory
	implements ElasticsearchDocumentFactory {

	@Override
	public String getElasticsearchDocument(Document document)
		throws IOException {

		XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();

		Map<String, Field> fields = document.getFields();

		_addFields(fields, xContentBuilder);

		return xContentBuilder.string();
	}

	public String getElasticsearchFormDocument(Document document,
	IndexableFormContent formContent) throws IOException {
		XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();

		xContentBuilder.startObject("FormContent");
		_buildFormContetBody(xContentBuilder, formContent);
		xContentBuilder.endObject();

		return xContentBuilder.string();
	}

	private void _addField(XContentBuilder xContentBuilder, Field field)
		throws IOException {

		String name = field.getName();

		if (!field.isLocalized()) {
			for (String value : field.getValues()) {
				if (Validator.isNull(value)) {
					continue;
				}

				xContentBuilder.field(name, value.trim());
			}
		}
		else {
			Map<Locale, String> localizedValues = field.getLocalizedValues();

			for (Map.Entry<Locale, String> entry : localizedValues.entrySet()) {
				String value = entry.getValue();

				if (Validator.isNull(value)) {
					continue;
				}

				Locale locale = entry.getKey();

				String languageId = LocaleUtil.toLanguageId(locale);

				String defaultLanguageId = LocaleUtil.toLanguageId(
					LocaleUtil.getDefault());

				if (languageId.equals(defaultLanguageId)) {
					xContentBuilder.field(name, value.trim());
				}

				String localizedName = DocumentImpl.getLocalizedName(
					languageId, name);

				xContentBuilder.field(localizedName, value.trim());
			}
		}
	}

	private void _addFields(Map<String, Field> fields,
	XContentBuilder xContentBuilder) throws IOException {
		xContentBuilder.startObject();

		for (Field field : fields.values()) {
			if (field.getNestedFields().isEmpty()) {
				_addField(xContentBuilder, field);
			}
			else {
				_addNestedField(xContentBuilder, field);
			}
		}

		xContentBuilder.endObject();
	}

	private void _addNestedField(XContentBuilder xContentBuilder, Field field)
		throws IOException {

		xContentBuilder.startObject(field.getName());
		_addFields(field.getNestedFields(), xContentBuilder);
		xContentBuilder.endObject();
	}

	private void _buildFieldsBody(XContentBuilder xContentBuilder,
	List<IndexableFormFieldValue> indexableFormFieldValues) throws IOException {
		for (IndexableFormFieldValue value : indexableFormFieldValues) {
			xContentBuilder.field("fieldName", value.getFieldName());
			xContentBuilder.startObject("calculatedValues");
			_buildLocalizedValue(xContentBuilder, value.getCalculatedValues());
			xContentBuilder.endObject();
		}
	}

	private void _buildFormContetBody(XContentBuilder xContentBuilder,
	IndexableFormContent formContent) throws IOException {
		xContentBuilder.field("companyId", formContent.getCompanyId());
		xContentBuilder.field("contentId", formContent.getContentId());
		xContentBuilder.field("groupId", formContent.getGroupId());
		xContentBuilder.field("name", formContent.getName());
		xContentBuilder.field("structureId", formContent.getStructureId());
		xContentBuilder.field("userId", formContent.getUserId());
		xContentBuilder.field("userName", formContent.getUserName());

		xContentBuilder.startObject("fields");
		_buildFieldsBody(xContentBuilder, formContent.getFields());
		xContentBuilder.endObject();
	}

	private void _buildLocalizedValue(XContentBuilder xContentBuilder,
	List<IndexableLocalizedValue> indexableLocalizedValues) throws IOException {
		for (IndexableLocalizedValue value : indexableLocalizedValues) {
			xContentBuilder.field("locale", value.getLocale());
			xContentBuilder.field("value", value.getValue());
		}
	}

}