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

import com.liferay.portal.search.elasticsearch.document.util.EmbeddedElasticsearchServer;

import org.elasticsearch.client.Client;

import org.junit.After;
import org.junit.Before;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
public abstract class BaseElasticsearchTest extends PowerMockito {

	public String getDocument(String indexName, String id) {

		return _embeddedElasticsearchServer.getDocument(indexName, id);
	}

	public String indexDocument(String indexName, String jsonDocument) {

		return _embeddedElasticsearchServer.indexDocument(
			indexName, jsonDocument);
	}

	@Before
	public void setUp() throws Exception {

		_startEmbeddedElasticsearchServer();
		doBeforeRunTest();
	}

	@After
	public void tearDown() {

		doAfterRunTest();
		_shutdownEmbeddedElasticsearchServer();
	}

	protected abstract EmbeddedElasticsearchServer createSearchServer()
		throws Exception;

	protected abstract void doAfterRunTest();

	protected abstract void doBeforeRunTest();

	protected Client getClient() {

		return _embeddedElasticsearchServer.getClient();
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

		_embeddedElasticsearchServer = createSearchServer();
	}

	private String _alphabet = "abcdefghijklmnopqrstuvxyz";
	private EmbeddedElasticsearchServer _embeddedElasticsearchServer;
	private String[] locales = {
		"pt_BR", "en_US", "fr_FR", "de_DE", "zh-CN", "ar_AE", "iw_IL", "hi_IN",
		"it_IT", "ja_JP"
	};

}