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
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;

/**
 * @author Miguel Angelo Caldas Gallindo
 */
public class EmbeddedElasticsearchServer {

	public static Builder serverBuilder(String configFileName) {

		return new Builder(configFileName);
	}

	public void deleteDataDirectory() {

		try {
			FileUtils.deleteDirectory(new File(_node.settings().get(
				"path.test.data.dir")));
		}
		catch (IOException e) {
			throw new RuntimeException(
				"Could not delete data directory of embedded elasticsearch " +
					"server", e);
		}
	}

	public Client getClient() {

		return _node.client();
	}

	public String getDocument(String indexName, String id) {

		GetRequestBuilder getRequestBuilder = getClient().prepareGet(
			indexName, "FormContentDocumentType", id);

		GetResponse response = getRequestBuilder.execute().actionGet();

		return response.getSourceAsString();
	}

	public String indexDocument(String indexName, String jsonDocument) {

		IndexResponse response = getClient().prepareIndex(
			indexName, "FormContentDocumentType").setSource(jsonDocument).
			execute().actionGet();

		return response.getId();
	}

	public void pushMapping(String indexName, String mappingName)
		throws Exception {

		getClient().prepareIndex(indexName, "FormContentDocumentType");

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

	public static class Builder {

		Builder(String configFileName) {

			_configFileName = configFileName;
		}

		public EmbeddedElasticsearchServer build() throws Exception {

			return new EmbeddedElasticsearchServer(
				_configFileName, indexesAndMappings);
		}

		public Builder createIndex(String name, String... mappings) {

			if (!indexesAndMappings.containsKey(name)) {
				List<String> mappingList = new ArrayList<String>();
				indexesAndMappings.put(name, mappingList);
			}

			if (mappings != null) {
				Collections.addAll(indexesAndMappings.get(name), mappings);
			}

			return this;
		}

		private String _configFileName;

		private Map<String, List<String>> indexesAndMappings =
			new HashMap<String, List<String>>();
	}

	private EmbeddedElasticsearchServer(
		String configFileName, Map<String, List<String>> indexesAndMappings)
			throws Exception {

		ImmutableSettings.Builder elasticsearchSettings =
			ImmutableSettings.settingsBuilder().loadFromClasspath(
				"META-INF/" + configFileName);

		_node = nodeBuilder().local(true).settings(
			elasticsearchSettings.build()).data(true).build();

		deleteDataDirectory();

		_node.start();

		for (Entry<String, List<String>> index : indexesAndMappings.entrySet())
		{
			_createIndex(index.getKey());

			for (String mapping : index.getValue()) {
				pushMapping(index.getKey(), mapping);
			}
		}
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

	private String _documentType;
	private final Node _node;

}