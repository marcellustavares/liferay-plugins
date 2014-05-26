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

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
@PrepareForTest( {
	PropsUtil.class, LocalizationUtil.class
})
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
public class ElasticSearchTest extends BaseElasticsearchTest {

	@Override
	public EmbeddedElasticsearchServer createSearchServer() throws Exception {

		EmbeddedElasticsearchServer.Builder searchServerBuilder =
			EmbeddedElasticsearchServer.serverBuilder(
				"elasticsearch-embedded-test.yml");

		searchServerBuilder.createIndex("index-test", "type_mappings.json");

		return searchServerBuilder.build();
	}

	@Override
	public void doAfterRunTest() {
	}

	@Override
	public void doBeforeRunTest() {

		_mockPropsUtil();
		_mockLocalizationUtil();

		_documentFactory = new DefaultElasticsearchDocumentFactory();
	}

	@Test
	public void testGetDocumentWithField() throws Exception {

		DocumentImpl document = new DocumentImpl();

		document.addText("tags", "liferay");

		_assertAllConditions(document);
	}

	@Test
	public void testGetDocumentWithLocalizableField() throws Exception {

		DocumentImpl document = new DocumentImpl();

		Map<Locale, String> localizedFieldValues =
			new HashMap<Locale, String>();
		localizedFieldValues.clear();
		localizedFieldValues.put(Locale.ENGLISH, "developer");
		localizedFieldValues.put(new Locale("pt", "BR"), "desenvolvedor");

		document.addLocalizedText("tags", localizedFieldValues);

		_assertAllConditions(document);
	}

	@Test
	public void testGetDocumentWithLocalizableNestedField() throws Exception {

		DocumentImpl document = new DocumentImpl();

		Map<Locale, String> localizedFieldValues =
			new HashMap<Locale, String>();
		localizedFieldValues.clear();
		localizedFieldValues.put(Locale.ENGLISH, "contract");
		localizedFieldValues.put(new Locale("pt", "BR"), "contrato");

		document.addLocalizedText("liferay.webcontent.documents.name",
		localizedFieldValues);

		_assertAllConditions(document);
	}

	@Test
	public void testGetDocumentWithNestedField() throws Exception {

		DocumentImpl document = new DocumentImpl();

		document.addText("liferay.blogs.posts.tags.name", "liferay");

		_assertAllConditions(document);
	}

	private void _assertAllConditions(DocumentImpl document) throws IOException
	{

		String elasticSearchDocument = _getElasticSearchDocument(document);
		assertNotNull(elasticSearchDocument);
		assertNotEquals("", elasticSearchDocument);

		indexDocument("index-test", elasticSearchDocument);


	}

	private String _getElasticSearchDocument(DocumentImpl document)
		throws IOException {

		return _documentFactory.getElasticsearchDocument(document);
	}

	private void _mockLocalizationUtil() {

		Localization localizationMock = mock(Localization.class);

		when(
			localizationMock.getLocalizedName(Matchers.anyString(), Matchers.
				anyString())).then(new Answer<String>() {
					@Override
					public String answer(InvocationOnMock invocation)
						throws Throwable {

						Object[] args = invocation.getArguments();

						String name = (String)args[0];
						String languageId = (String)args[1];

						return name.concat(StringPool.UNDERLINE).concat(
							languageId);
					}
		});

		spy(LocalizationUtil.class);

		when(LocalizationUtil.getLocalization()).thenReturn(localizationMock);
	}

	private void _mockPropsUtil() {

		mockStatic(PropsUtil.class);

		when(PropsUtil.get(PropsKeys.INDEX_DATE_FORMAT_PATTERN)).thenReturn(
			"yyyyMMddHHmmss");

		when(PropsUtil.get(PropsKeys.INDEX_SORTABLE_TEXT_FIELDS)).thenReturn(
			"firstName,jobTitle,lastName,name,screenName,title");

		when(
			PropsUtil.get(PropsKeys.INDEX_SORTABLE_TEXT_FIELDS_TRUNCATED_LENGTH)
			).thenReturn("255");
	}

	private DefaultElasticsearchDocumentFactory _documentFactory;

}