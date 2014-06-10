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

package com.liferay.portal.search.elasticsearch.document.util;

import static com.liferay.portal.search.elasticsearch.document.util.ElasticSearchConfigEnum.DOCUMENT_COMPANY_ID;
import static com.liferay.portal.search.elasticsearch.document.util.ElasticSearchConfigEnum.DOCUMENT_TYPE;
import static com.liferay.portal.search.elasticsearch.document.util.ElasticSearchConfigEnum.INDEX_NAME;
import static com.liferay.portal.search.elasticsearch.document.util.ElasticSearchConfigEnum.SYSTEM_ENGINE;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
public class EmbeddedElasticsearchServer {

	public static Builder serverBuilder(String configFileName) {

		return new Builder(configFileName);
	}

	public void createIndexes() throws Exception {

		for (Entry<String, List<String>> index : _indexesAndMappings.entrySet())
		{
			_createIndex(index.getKey());

			for (String mapping : index.getValue()) {
				pushMapping(index.getKey(), mapping);
			}
		}
	}

	public void deleteDataDirectory() {

		try {
			String dataDir = _getDataDir();
			FileUtils.deleteDirectory(new File(dataDir));
		}
		catch (IOException e) {
			throw new RuntimeException(
				"Could not delete data directory of embedded elasticsearch " +
					"server", e);
		}
	}

	public void deleteIndexes() {

		for (Entry<String, List<String>> index : _indexesAndMappings.entrySet())
		{
			_deleteIndex(index.getKey());
		}
	}

	public Client getClient() {

		return _node.client();
	}

	public String getCompanyId() {

		return _settings.get(DOCUMENT_COMPANY_ID.getConfigName());
	}

	public String getDocumentType() {

		return _settings.get(DOCUMENT_TYPE.getConfigName());
	}

	public String getIndexedJsonDocument(String indexName, String id) {

		GetRequestBuilder getRequestBuilder = getClient().prepareGet(
			indexName, getDocumentType(), id);

		GetResponse response = getRequestBuilder.execute().actionGet();

		return response.getSourceAsString();
	}

	public String getSystemEngine() {

		return _settings.get(SYSTEM_ENGINE.getConfigName());
	}

	public String indexJsonDocument(String jsonDocument) {

		IndexResponse response = getClient().prepareIndex(
			_getDefaultIndexName(), getDocumentType()).setSource(jsonDocument).
			execute().actionGet();

		return response.getId();
	}

	public String indexJsonDocument(String indexName, String jsonDocument) {

		IndexResponse response = getClient().prepareIndex(
			indexName, getDocumentType()).setSource(jsonDocument).
			execute().actionGet();

		return response.getId();
	}

	public void pushMapping(String indexName, String mappingName)
		throws Exception {

		getClient().prepareIndex(indexName, getDocumentType());

		String path =
			EmbeddedElasticsearchServer.class.getResource(
				"/META-INF/" + mappingName).getPath();

		String source = FileUtils.readFileToString(new File(path));

		PutMappingRequestBuilder pmrb =
			getClient().admin().indices().preparePutMapping(indexName).setType(
				mappingName);

		pmrb.setSource(source);

		PutMappingResponse response = pmrb.execute().actionGet();

		if (!response.isAcknowledged()) {
			throw new RuntimeException(
				"Could not define mapping for type [" + indexName + "/" +
					mappingName + "].");
		}
	}

	public void shutdown() {

		_node.stop();
		_node.close();
	}

	public void start() {
		_node.start();
	}

	public static class Builder {

		Builder(String configFileName) {

			_settingsBuilder =
				ImmutableSettings.settingsBuilder().loadFromClasspath(
					"META-INF/" + configFileName);
		}

		public Builder addDefaultIndex(String... mappings) {
			return addIndex(_settingsBuilder.get("index.name"), mappings);
		}

		public Builder addIndex(String name, String... mappings) {

			if (!_indexesAndMappings.containsKey(name)) {
				List<String> mappingList = new ArrayList<String>();
				_indexesAndMappings.put(name, mappingList);
			}

			if (mappings != null) {
				Collections.addAll(_indexesAndMappings.get(name), mappings);
			}

			return this;
		}

		public EmbeddedElasticsearchServer build() throws Exception {

			if (_embeddedElasticsearchServer == null) {
				_embeddedElasticsearchServer = new EmbeddedElasticsearchServer(
					_settingsBuilder, _indexesAndMappings);
			}

			return _embeddedElasticsearchServer;
		}

		private EmbeddedElasticsearchServer _embeddedElasticsearchServer;
		private Map<String, List<String>> _indexesAndMappings =
			new HashMap<String, List<String>>();
		private ImmutableSettings.Builder _settingsBuilder;
	}

	private EmbeddedElasticsearchServer(
		ImmutableSettings.Builder settingsBuilder,
		Map<String, List<String>> indexesAndMappings) throws Exception {

		_indexesAndMappings = indexesAndMappings;

		_settings = settingsBuilder.build();

		_node = nodeBuilder().local(true).settings(
			_settings).data(true).build();

		deleteDataDirectory();

		start();

		createIndexes();
	}

	private void _createIndex(String name) {

		CreateIndexRequestBuilder cirb =
			getClient().admin().indices().prepareCreate(name);
		CreateIndexResponse createIndexResponse = cirb.execute().actionGet();

		if (!createIndexResponse.isAcknowledged()) {
			throw new RuntimeException("Could not create index [" + name + "]."
				);
		}
	}

	private void _deleteIndex(String indexName) {

		DeleteIndexResponse delete = getClient().admin().indices().delete(
			new DeleteIndexRequest(indexName)). actionGet();

		if (!delete.isAcknowledged()) {
			throw new RuntimeException("Index wasn't deleted");
		}
	}

	private String _getDataDir() {

		return _node.settings().get("path.test.data.dir");
	}

	private String _getDefaultIndexName() {

		return _settings.get(INDEX_NAME.getConfigName());
	}

	private Map<String, List<String>> _indexesAndMappings;
	private final Node _node;
	private Settings _settings;

}