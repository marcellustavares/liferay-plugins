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

package com.liferay.portal.dynamicstorage.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.dynamicstorage.NoSuchAppEntityException;
import com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity;
import com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityImpl;
import com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl;
import com.liferay.portal.dynamicstorage.service.persistence.DynamicStorageAppEntityPersistence;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the dynamic storage app entity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marcellus Tavares
 * @see DynamicStorageAppEntityPersistence
 * @see DynamicStorageAppEntityUtil
 * @generated
 */
@ProviderType
public class DynamicStorageAppEntityPersistenceImpl extends BasePersistenceImpl<DynamicStorageAppEntity>
	implements DynamicStorageAppEntityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DynamicStorageAppEntityUtil} to access the dynamic storage app entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DynamicStorageAppEntityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityModelImpl.FINDER_CACHE_ENABLED,
			DynamicStorageAppEntityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityModelImpl.FINDER_CACHE_ENABLED,
			DynamicStorageAppEntityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_A_E = new FinderPath(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityModelImpl.FINDER_CACHE_ENABLED,
			DynamicStorageAppEntityImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByA_E",
			new String[] { Long.class.getName(), String.class.getName() },
			DynamicStorageAppEntityModelImpl.APPID_COLUMN_BITMASK |
			DynamicStorageAppEntityModelImpl.ENTITYNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_E = new FinderPath(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_E",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the dynamic storage app entity where appId = &#63; and entityName = &#63; or throws a {@link com.liferay.portal.dynamicstorage.NoSuchAppEntityException} if it could not be found.
	 *
	 * @param appId the app ID
	 * @param entityName the entity name
	 * @return the matching dynamic storage app entity
	 * @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a matching dynamic storage app entity could not be found
	 */
	@Override
	public DynamicStorageAppEntity findByA_E(long appId, String entityName)
		throws NoSuchAppEntityException {
		DynamicStorageAppEntity dynamicStorageAppEntity = fetchByA_E(appId,
				entityName);

		if (dynamicStorageAppEntity == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appId=");
			msg.append(appId);

			msg.append(", entityName=");
			msg.append(entityName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAppEntityException(msg.toString());
		}

		return dynamicStorageAppEntity;
	}

	/**
	 * Returns the dynamic storage app entity where appId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appId the app ID
	 * @param entityName the entity name
	 * @return the matching dynamic storage app entity, or <code>null</code> if a matching dynamic storage app entity could not be found
	 */
	@Override
	public DynamicStorageAppEntity fetchByA_E(long appId, String entityName) {
		return fetchByA_E(appId, entityName, true);
	}

	/**
	 * Returns the dynamic storage app entity where appId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appId the app ID
	 * @param entityName the entity name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dynamic storage app entity, or <code>null</code> if a matching dynamic storage app entity could not be found
	 */
	@Override
	public DynamicStorageAppEntity fetchByA_E(long appId, String entityName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { appId, entityName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_E,
					finderArgs, this);
		}

		if (result instanceof DynamicStorageAppEntity) {
			DynamicStorageAppEntity dynamicStorageAppEntity = (DynamicStorageAppEntity)result;

			if ((appId != dynamicStorageAppEntity.getAppId()) ||
					!Validator.equals(entityName,
						dynamicStorageAppEntity.getEntityName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DYNAMICSTORAGEAPPENTITY_WHERE);

			query.append(_FINDER_COLUMN_A_E_APPID_2);

			boolean bindEntityName = false;

			if (entityName == null) {
				query.append(_FINDER_COLUMN_A_E_ENTITYNAME_1);
			}
			else if (entityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_E_ENTITYNAME_3);
			}
			else {
				bindEntityName = true;

				query.append(_FINDER_COLUMN_A_E_ENTITYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (bindEntityName) {
					qPos.add(entityName);
				}

				List<DynamicStorageAppEntity> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_E,
						finderArgs, list);
				}
				else {
					DynamicStorageAppEntity dynamicStorageAppEntity = list.get(0);

					result = dynamicStorageAppEntity;

					cacheResult(dynamicStorageAppEntity);

					if ((dynamicStorageAppEntity.getAppId() != appId) ||
							(dynamicStorageAppEntity.getEntityName() == null) ||
							!dynamicStorageAppEntity.getEntityName()
														.equals(entityName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_E,
							finderArgs, dynamicStorageAppEntity);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_E,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DynamicStorageAppEntity)result;
		}
	}

	/**
	 * Removes the dynamic storage app entity where appId = &#63; and entityName = &#63; from the database.
	 *
	 * @param appId the app ID
	 * @param entityName the entity name
	 * @return the dynamic storage app entity that was removed
	 */
	@Override
	public DynamicStorageAppEntity removeByA_E(long appId, String entityName)
		throws NoSuchAppEntityException {
		DynamicStorageAppEntity dynamicStorageAppEntity = findByA_E(appId,
				entityName);

		return remove(dynamicStorageAppEntity);
	}

	/**
	 * Returns the number of dynamic storage app entities where appId = &#63; and entityName = &#63;.
	 *
	 * @param appId the app ID
	 * @param entityName the entity name
	 * @return the number of matching dynamic storage app entities
	 */
	@Override
	public int countByA_E(long appId, String entityName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_E;

		Object[] finderArgs = new Object[] { appId, entityName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DYNAMICSTORAGEAPPENTITY_WHERE);

			query.append(_FINDER_COLUMN_A_E_APPID_2);

			boolean bindEntityName = false;

			if (entityName == null) {
				query.append(_FINDER_COLUMN_A_E_ENTITYNAME_1);
			}
			else if (entityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_E_ENTITYNAME_3);
			}
			else {
				bindEntityName = true;

				query.append(_FINDER_COLUMN_A_E_ENTITYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (bindEntityName) {
					qPos.add(entityName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_E_APPID_2 = "dynamicStorageAppEntity.appId = ? AND ";
	private static final String _FINDER_COLUMN_A_E_ENTITYNAME_1 = "dynamicStorageAppEntity.entityName IS NULL";
	private static final String _FINDER_COLUMN_A_E_ENTITYNAME_2 = "dynamicStorageAppEntity.entityName = ?";
	private static final String _FINDER_COLUMN_A_E_ENTITYNAME_3 = "(dynamicStorageAppEntity.entityName IS NULL OR dynamicStorageAppEntity.entityName = '')";

	public DynamicStorageAppEntityPersistenceImpl() {
		setModelClass(DynamicStorageAppEntity.class);
	}

	/**
	 * Caches the dynamic storage app entity in the entity cache if it is enabled.
	 *
	 * @param dynamicStorageAppEntity the dynamic storage app entity
	 */
	@Override
	public void cacheResult(DynamicStorageAppEntity dynamicStorageAppEntity) {
		EntityCacheUtil.putResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityImpl.class,
			dynamicStorageAppEntity.getPrimaryKey(), dynamicStorageAppEntity);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_E,
			new Object[] {
				dynamicStorageAppEntity.getAppId(),
				dynamicStorageAppEntity.getEntityName()
			}, dynamicStorageAppEntity);

		dynamicStorageAppEntity.resetOriginalValues();
	}

	/**
	 * Caches the dynamic storage app entities in the entity cache if it is enabled.
	 *
	 * @param dynamicStorageAppEntities the dynamic storage app entities
	 */
	@Override
	public void cacheResult(
		List<DynamicStorageAppEntity> dynamicStorageAppEntities) {
		for (DynamicStorageAppEntity dynamicStorageAppEntity : dynamicStorageAppEntities) {
			if (EntityCacheUtil.getResult(
						DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
						DynamicStorageAppEntityImpl.class,
						dynamicStorageAppEntity.getPrimaryKey()) == null) {
				cacheResult(dynamicStorageAppEntity);
			}
			else {
				dynamicStorageAppEntity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all dynamic storage app entities.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DynamicStorageAppEntityImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DynamicStorageAppEntityImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the dynamic storage app entity.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DynamicStorageAppEntity dynamicStorageAppEntity) {
		EntityCacheUtil.removeResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityImpl.class,
			dynamicStorageAppEntity.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dynamicStorageAppEntity);
	}

	@Override
	public void clearCache(
		List<DynamicStorageAppEntity> dynamicStorageAppEntities) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DynamicStorageAppEntity dynamicStorageAppEntity : dynamicStorageAppEntities) {
			EntityCacheUtil.removeResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
				DynamicStorageAppEntityImpl.class,
				dynamicStorageAppEntity.getPrimaryKey());

			clearUniqueFindersCache(dynamicStorageAppEntity);
		}
	}

	protected void cacheUniqueFindersCache(
		DynamicStorageAppEntity dynamicStorageAppEntity) {
		if (dynamicStorageAppEntity.isNew()) {
			Object[] args = new Object[] {
					dynamicStorageAppEntity.getAppId(),
					dynamicStorageAppEntity.getEntityName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_E, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_E, args,
				dynamicStorageAppEntity);
		}
		else {
			DynamicStorageAppEntityModelImpl dynamicStorageAppEntityModelImpl = (DynamicStorageAppEntityModelImpl)dynamicStorageAppEntity;

			if ((dynamicStorageAppEntityModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_A_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dynamicStorageAppEntity.getAppId(),
						dynamicStorageAppEntity.getEntityName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_E, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_E, args,
					dynamicStorageAppEntity);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DynamicStorageAppEntity dynamicStorageAppEntity) {
		DynamicStorageAppEntityModelImpl dynamicStorageAppEntityModelImpl = (DynamicStorageAppEntityModelImpl)dynamicStorageAppEntity;

		Object[] args = new Object[] {
				dynamicStorageAppEntity.getAppId(),
				dynamicStorageAppEntity.getEntityName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_E, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_E, args);

		if ((dynamicStorageAppEntityModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_E.getColumnBitmask()) != 0) {
			args = new Object[] {
					dynamicStorageAppEntityModelImpl.getOriginalAppId(),
					dynamicStorageAppEntityModelImpl.getOriginalEntityName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_E, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_E, args);
		}
	}

	/**
	 * Creates a new dynamic storage app entity with the primary key. Does not add the dynamic storage app entity to the database.
	 *
	 * @param appEntityId the primary key for the new dynamic storage app entity
	 * @return the new dynamic storage app entity
	 */
	@Override
	public DynamicStorageAppEntity create(long appEntityId) {
		DynamicStorageAppEntity dynamicStorageAppEntity = new DynamicStorageAppEntityImpl();

		dynamicStorageAppEntity.setNew(true);
		dynamicStorageAppEntity.setPrimaryKey(appEntityId);

		return dynamicStorageAppEntity;
	}

	/**
	 * Removes the dynamic storage app entity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appEntityId the primary key of the dynamic storage app entity
	 * @return the dynamic storage app entity that was removed
	 * @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a dynamic storage app entity with the primary key could not be found
	 */
	@Override
	public DynamicStorageAppEntity remove(long appEntityId)
		throws NoSuchAppEntityException {
		return remove((Serializable)appEntityId);
	}

	/**
	 * Removes the dynamic storage app entity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dynamic storage app entity
	 * @return the dynamic storage app entity that was removed
	 * @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a dynamic storage app entity with the primary key could not be found
	 */
	@Override
	public DynamicStorageAppEntity remove(Serializable primaryKey)
		throws NoSuchAppEntityException {
		Session session = null;

		try {
			session = openSession();

			DynamicStorageAppEntity dynamicStorageAppEntity = (DynamicStorageAppEntity)session.get(DynamicStorageAppEntityImpl.class,
					primaryKey);

			if (dynamicStorageAppEntity == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAppEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dynamicStorageAppEntity);
		}
		catch (NoSuchAppEntityException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DynamicStorageAppEntity removeImpl(
		DynamicStorageAppEntity dynamicStorageAppEntity) {
		dynamicStorageAppEntity = toUnwrappedModel(dynamicStorageAppEntity);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dynamicStorageAppEntity)) {
				dynamicStorageAppEntity = (DynamicStorageAppEntity)session.get(DynamicStorageAppEntityImpl.class,
						dynamicStorageAppEntity.getPrimaryKeyObj());
			}

			if (dynamicStorageAppEntity != null) {
				session.delete(dynamicStorageAppEntity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dynamicStorageAppEntity != null) {
			clearCache(dynamicStorageAppEntity);
		}

		return dynamicStorageAppEntity;
	}

	@Override
	public DynamicStorageAppEntity updateImpl(
		com.liferay.portal.dynamicstorage.model.DynamicStorageAppEntity dynamicStorageAppEntity) {
		dynamicStorageAppEntity = toUnwrappedModel(dynamicStorageAppEntity);

		boolean isNew = dynamicStorageAppEntity.isNew();

		Session session = null;

		try {
			session = openSession();

			if (dynamicStorageAppEntity.isNew()) {
				session.save(dynamicStorageAppEntity);

				dynamicStorageAppEntity.setNew(false);
			}
			else {
				session.merge(dynamicStorageAppEntity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DynamicStorageAppEntityModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
			DynamicStorageAppEntityImpl.class,
			dynamicStorageAppEntity.getPrimaryKey(), dynamicStorageAppEntity,
			false);

		clearUniqueFindersCache(dynamicStorageAppEntity);
		cacheUniqueFindersCache(dynamicStorageAppEntity);

		dynamicStorageAppEntity.resetOriginalValues();

		return dynamicStorageAppEntity;
	}

	protected DynamicStorageAppEntity toUnwrappedModel(
		DynamicStorageAppEntity dynamicStorageAppEntity) {
		if (dynamicStorageAppEntity instanceof DynamicStorageAppEntityImpl) {
			return dynamicStorageAppEntity;
		}

		DynamicStorageAppEntityImpl dynamicStorageAppEntityImpl = new DynamicStorageAppEntityImpl();

		dynamicStorageAppEntityImpl.setNew(dynamicStorageAppEntity.isNew());
		dynamicStorageAppEntityImpl.setPrimaryKey(dynamicStorageAppEntity.getPrimaryKey());

		dynamicStorageAppEntityImpl.setAppEntityId(dynamicStorageAppEntity.getAppEntityId());
		dynamicStorageAppEntityImpl.setAppId(dynamicStorageAppEntity.getAppId());
		dynamicStorageAppEntityImpl.setEntityName(dynamicStorageAppEntity.getEntityName());
		dynamicStorageAppEntityImpl.setDDMStructureId(dynamicStorageAppEntity.getDDMStructureId());

		return dynamicStorageAppEntityImpl;
	}

	/**
	 * Returns the dynamic storage app entity with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the dynamic storage app entity
	 * @return the dynamic storage app entity
	 * @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a dynamic storage app entity with the primary key could not be found
	 */
	@Override
	public DynamicStorageAppEntity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAppEntityException {
		DynamicStorageAppEntity dynamicStorageAppEntity = fetchByPrimaryKey(primaryKey);

		if (dynamicStorageAppEntity == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAppEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dynamicStorageAppEntity;
	}

	/**
	 * Returns the dynamic storage app entity with the primary key or throws a {@link com.liferay.portal.dynamicstorage.NoSuchAppEntityException} if it could not be found.
	 *
	 * @param appEntityId the primary key of the dynamic storage app entity
	 * @return the dynamic storage app entity
	 * @throws com.liferay.portal.dynamicstorage.NoSuchAppEntityException if a dynamic storage app entity with the primary key could not be found
	 */
	@Override
	public DynamicStorageAppEntity findByPrimaryKey(long appEntityId)
		throws NoSuchAppEntityException {
		return findByPrimaryKey((Serializable)appEntityId);
	}

	/**
	 * Returns the dynamic storage app entity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dynamic storage app entity
	 * @return the dynamic storage app entity, or <code>null</code> if a dynamic storage app entity with the primary key could not be found
	 */
	@Override
	public DynamicStorageAppEntity fetchByPrimaryKey(Serializable primaryKey) {
		DynamicStorageAppEntity dynamicStorageAppEntity = (DynamicStorageAppEntity)EntityCacheUtil.getResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
				DynamicStorageAppEntityImpl.class, primaryKey);

		if (dynamicStorageAppEntity == _nullDynamicStorageAppEntity) {
			return null;
		}

		if (dynamicStorageAppEntity == null) {
			Session session = null;

			try {
				session = openSession();

				dynamicStorageAppEntity = (DynamicStorageAppEntity)session.get(DynamicStorageAppEntityImpl.class,
						primaryKey);

				if (dynamicStorageAppEntity != null) {
					cacheResult(dynamicStorageAppEntity);
				}
				else {
					EntityCacheUtil.putResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
						DynamicStorageAppEntityImpl.class, primaryKey,
						_nullDynamicStorageAppEntity);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
					DynamicStorageAppEntityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dynamicStorageAppEntity;
	}

	/**
	 * Returns the dynamic storage app entity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param appEntityId the primary key of the dynamic storage app entity
	 * @return the dynamic storage app entity, or <code>null</code> if a dynamic storage app entity with the primary key could not be found
	 */
	@Override
	public DynamicStorageAppEntity fetchByPrimaryKey(long appEntityId) {
		return fetchByPrimaryKey((Serializable)appEntityId);
	}

	@Override
	public Map<Serializable, DynamicStorageAppEntity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DynamicStorageAppEntity> map = new HashMap<Serializable, DynamicStorageAppEntity>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DynamicStorageAppEntity dynamicStorageAppEntity = fetchByPrimaryKey(primaryKey);

			if (dynamicStorageAppEntity != null) {
				map.put(primaryKey, dynamicStorageAppEntity);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			DynamicStorageAppEntity dynamicStorageAppEntity = (DynamicStorageAppEntity)EntityCacheUtil.getResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
					DynamicStorageAppEntityImpl.class, primaryKey);

			if (dynamicStorageAppEntity == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, dynamicStorageAppEntity);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DYNAMICSTORAGEAPPENTITY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (DynamicStorageAppEntity dynamicStorageAppEntity : (List<DynamicStorageAppEntity>)q.list()) {
				map.put(dynamicStorageAppEntity.getPrimaryKeyObj(),
					dynamicStorageAppEntity);

				cacheResult(dynamicStorageAppEntity);

				uncachedPrimaryKeys.remove(dynamicStorageAppEntity.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(DynamicStorageAppEntityModelImpl.ENTITY_CACHE_ENABLED,
					DynamicStorageAppEntityImpl.class, primaryKey,
					_nullDynamicStorageAppEntity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the dynamic storage app entities.
	 *
	 * @return the dynamic storage app entities
	 */
	@Override
	public List<DynamicStorageAppEntity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dynamic storage app entities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dynamic storage app entities
	 * @param end the upper bound of the range of dynamic storage app entities (not inclusive)
	 * @return the range of dynamic storage app entities
	 */
	@Override
	public List<DynamicStorageAppEntity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dynamic storage app entities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.dynamicstorage.model.impl.DynamicStorageAppEntityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of dynamic storage app entities
	 * @param end the upper bound of the range of dynamic storage app entities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dynamic storage app entities
	 */
	@Override
	public List<DynamicStorageAppEntity> findAll(int start, int end,
		OrderByComparator<DynamicStorageAppEntity> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<DynamicStorageAppEntity> list = (List<DynamicStorageAppEntity>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DYNAMICSTORAGEAPPENTITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DYNAMICSTORAGEAPPENTITY;

				if (pagination) {
					sql = sql.concat(DynamicStorageAppEntityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DynamicStorageAppEntity>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DynamicStorageAppEntity>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the dynamic storage app entities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DynamicStorageAppEntity dynamicStorageAppEntity : findAll()) {
			remove(dynamicStorageAppEntity);
		}
	}

	/**
	 * Returns the number of dynamic storage app entities.
	 *
	 * @return the number of dynamic storage app entities
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DYNAMICSTORAGEAPPENTITY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the dynamic storage app entity persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(DynamicStorageAppEntityImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DYNAMICSTORAGEAPPENTITY = "SELECT dynamicStorageAppEntity FROM DynamicStorageAppEntity dynamicStorageAppEntity";
	private static final String _SQL_SELECT_DYNAMICSTORAGEAPPENTITY_WHERE_PKS_IN =
		"SELECT dynamicStorageAppEntity FROM DynamicStorageAppEntity dynamicStorageAppEntity WHERE appEntityId IN (";
	private static final String _SQL_SELECT_DYNAMICSTORAGEAPPENTITY_WHERE = "SELECT dynamicStorageAppEntity FROM DynamicStorageAppEntity dynamicStorageAppEntity WHERE ";
	private static final String _SQL_COUNT_DYNAMICSTORAGEAPPENTITY = "SELECT COUNT(dynamicStorageAppEntity) FROM DynamicStorageAppEntity dynamicStorageAppEntity";
	private static final String _SQL_COUNT_DYNAMICSTORAGEAPPENTITY_WHERE = "SELECT COUNT(dynamicStorageAppEntity) FROM DynamicStorageAppEntity dynamicStorageAppEntity WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dynamicStorageAppEntity.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DynamicStorageAppEntity exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DynamicStorageAppEntity exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static final Log _log = LogFactoryUtil.getLog(DynamicStorageAppEntityPersistenceImpl.class);
	private static final DynamicStorageAppEntity _nullDynamicStorageAppEntity = new DynamicStorageAppEntityImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DynamicStorageAppEntity> toCacheModel() {
				return _nullDynamicStorageAppEntityCacheModel;
			}
		};

	private static final CacheModel<DynamicStorageAppEntity> _nullDynamicStorageAppEntityCacheModel =
		new CacheModel<DynamicStorageAppEntity>() {
			@Override
			public DynamicStorageAppEntity toEntityModel() {
				return _nullDynamicStorageAppEntity;
			}
		};
}