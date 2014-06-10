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
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.elasticsearch.ElasticsearchUpdateDocumentCommandImpl;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.connection.EmbeddedElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.document.util.EmbeddedElasticsearchServer;

import java.io.IOException;

import org.elasticsearch.client.Client;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
@PrepareForTest( {
	ElasticsearchUpdateDocumentCommandImpl.class, LocalizationUtil.class,
	PropsUtil.class
})
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
public abstract class BaseElasticsearchTest extends PowerMockito {

	public EmbeddedElasticsearchServer createSearchServer() throws Exception {

		EmbeddedElasticsearchServer.Builder searchServerBuilder =
			EmbeddedElasticsearchServer.serverBuilder(ELASTICSEARCH_CONFIG);

		searchServerBuilder.addIndex(TYPE_MAPPINGS_JSON
			);

		return searchServerBuilder.build();
	}

	public String getIndexedJsonDocument(String indexName, String id) {

		return _embeddedElasticsearchServer.getIndexedJsonDocument(
			indexName, id);
	}

	public String indexJsonDocument(String indexName, String jsonDocument) {

		return _embeddedElasticsearchServer.indexJsonDocument(
			indexName, jsonDocument);
	}

	@Before()
	public void setUp() throws Exception {

		_startEmbeddedElasticsearchServer();
		_documentFactory = new DefaultElasticsearchDocumentFactory();
		_mockPropsUtil();
		_mockLocalizationUtil();
		_mockElasticsearchUpdateDocumentCommandImpl();
		_prepareElasticSearchConnectionManager();

		doBeforeRunTest();
	}

	@After
	public void tearDown() {

		doAfterRunTest();
		_shutdownEmbeddedElasticsearchServer();
	}

	public void updateDocument(Document document) throws SearchException {

		_updateDocumentCommand.updateDocument(
			_embeddedElasticsearchServer.getDocumentType(), _getSearchContext(),
			document);
	}

	protected DocumentImpl _createDocumentWithRequiredData() {

		DocumentImpl document = new DocumentImpl();

		document.addUID(Field.PORTLET_ID, 147L);

		document.addKeyword(
			Field.COMPANY_ID, _embeddedElasticsearchServer.getCompanyId());
		document.addKeyword(Field.ENTRY_CLASS_NAME, Document.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, 4L);
		document.addKeyword(Field.GROUP_ID, 4L);

		return document;
	}

	protected String _generateElasticSearchJson(DocumentImpl document)
		throws IOException {

		return _documentFactory.getElasticsearchDocument(document);
	}

	protected SearchContext _getSearchContext() {

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(
			Long.valueOf(_embeddedElasticsearchServer.getCompanyId()));
		searchContext.setSearchEngineId(
			_embeddedElasticsearchServer.getSystemEngine());

		return searchContext;
	}

	protected abstract void doAfterRunTest();

	protected abstract void doBeforeRunTest();

	protected Client getClient() {

		return _embeddedElasticsearchServer.getClient();
	}

	protected DefaultElasticsearchDocumentFactory _documentFactory;

	private void _mockElasticsearchUpdateDocumentCommandImpl() {

		_updateDocumentCommand = spy(
			new ElasticsearchUpdateDocumentCommandImpl());

		try {
			_updateDocumentCommand.setElasticsearchDocumentFactory(
				new DefaultElasticsearchDocumentFactory());
			doReturn(getClient()).when(_updateDocumentCommand, "getClient");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
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

	private void _prepareElasticSearchConnectionManager() {

		ElasticsearchConnection connection =
			new EmbeddedElasticsearchConnection();

		ElasticsearchConnectionManager.getInstance().setElasticsearchConnection(
			connection);
	}

	private int _random() {

		return (int) (Math.random() * 10);
	}

	private String _randomLocale() {

		return locales[_random()];
	}

	private String _randomString() {

		int length = _random();

		StringBuilder _randomString = new StringBuilder();

		for (int i = 0; i < length; i++) {
			_randomString.append(_alphabet.charAt(_random()));
		}

		return _randomString.toString();
	}

	private String _randomStringOrInt() {

		if ((_random() % 2) == 0) {
			return _randomString();
		}
		else {
			return _random() + "";
		}
	}

	private void _shutdownEmbeddedElasticsearchServer() {

		if (_embeddedElasticsearchServer != null) {
			_embeddedElasticsearchServer.shutdown();
			_embeddedElasticsearchServer.deleteDataDirectory();
		}
	}

	private void _startEmbeddedElasticsearchServer() throws Exception {

		if (_embeddedElasticsearchServer == null) {
			_embeddedElasticsearchServer = createSearchServer();
		}
		else {
			_embeddedElasticsearchServer.start();
		}
	}

	private static final String ELASTICSEARCH_CONFIG =
		"elasticsearch-embedded-test.yml";

	private static final String TYPE_MAPPINGS_JSON = "type_mappings_test.json";

	private String _alphabet = "abcdefghijklmnopqrstuvxyz";
	private EmbeddedElasticsearchServer _embeddedElasticsearchServer;
	private ElasticsearchUpdateDocumentCommandImpl _updateDocumentCommand;
	private String[] locales = {
		"pt_BR", "en_US", "fr_FR", "de_DE", "zh-CN", "ar_AE", "iw_IL", "hi_IN",
		"it_IT", "ja_JP"
	};

}