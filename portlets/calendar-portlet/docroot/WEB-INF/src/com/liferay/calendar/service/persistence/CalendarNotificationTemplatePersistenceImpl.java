/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.service.persistence;

import com.liferay.calendar.NoSuchNotificationTemplateException;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.impl.CalendarNotificationTemplateImpl;
import com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the calendar notification template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplatePersistence
 * @see CalendarNotificationTemplateUtil
 * @generated
 */
public class CalendarNotificationTemplatePersistenceImpl
	extends BasePersistenceImpl<CalendarNotificationTemplate>
	implements CalendarNotificationTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalendarNotificationTemplateUtil} to access the calendar notification template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalendarNotificationTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CalendarNotificationTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the calendar notification templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarNotificationTemplate> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar notification templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @return the range of matching calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarNotificationTemplate> findByUuid(String uuid,
		int start, int end) throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar notification templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarNotificationTemplate> findByUuid(String uuid,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CalendarNotificationTemplate> list = (List<CalendarNotificationTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarNotificationTemplate calendarNotificationTemplate : list) {
				if (!Validator.equals(uuid,
							calendarNotificationTemplate.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CalendarNotificationTemplate>(list);
				}
				else {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
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
	 * Returns the first calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationTemplateException, SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUuid_First(uuid,
				orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the first calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<CalendarNotificationTemplate> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationTemplateException, SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByUuid_Last(uuid,
				orderByComparator);

		if (calendarNotificationTemplate != null) {
			return calendarNotificationTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationTemplateException(msg.toString());
	}

	/**
	 * Returns the last calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<CalendarNotificationTemplate> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar notification templates before and after the current calendar notification template in the ordered set where uuid = &#63;.
	 *
	 * @param calendarNotificationTemplateId the primary key of the current calendar notification template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar notification template
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate[] findByUuid_PrevAndNext(
		long calendarNotificationTemplateId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationTemplateException, SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByPrimaryKey(calendarNotificationTemplateId);

		Session session = null;

		try {
			session = openSession();

			CalendarNotificationTemplate[] array = new CalendarNotificationTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					calendarNotificationTemplate, uuid, orderByComparator, true);

			array[1] = calendarNotificationTemplate;

			array[2] = getByUuid_PrevAndNext(session,
					calendarNotificationTemplate, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarNotificationTemplate getByUuid_PrevAndNext(
		Session session,
		CalendarNotificationTemplate calendarNotificationTemplate, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarNotificationTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarNotificationTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar notification templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (CalendarNotificationTemplate calendarNotificationTemplate : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarNotificationTemplate);
		}
	}

	/**
	 * Returns the number of calendar notification templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendarNotificationTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendarNotificationTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendarNotificationTemplate.uuid IS NULL OR calendarNotificationTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_NT_NTT_F = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_NT_NTT_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			},
			CalendarNotificationTemplateModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.NOTIFICATIONTYPE_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.NOTIFICATIONTEMPLATETYPE_COLUMN_BITMASK |
			CalendarNotificationTemplateModelImpl.FIELD_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_NT_NTT_F = new FinderPath(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByC_NT_NTT_F",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(), String.class.getName()
			});

	/**
	 * Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; and field = &#63; or throws a {@link com.liferay.calendar.NoSuchNotificationTemplateException} if it could not be found.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @param field the field
	 * @return the matching calendar notification template
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate findByC_NT_NTT_F(long calendarId,
		String notificationType, String notificationTemplateType, String field)
		throws NoSuchNotificationTemplateException, SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByC_NT_NTT_F(calendarId,
				notificationType, notificationTemplateType, field);

		if (calendarNotificationTemplate == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("calendarId=");
			msg.append(calendarId);

			msg.append(", notificationType=");
			msg.append(notificationType);

			msg.append(", notificationTemplateType=");
			msg.append(notificationTemplateType);

			msg.append(", field=");
			msg.append(field);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchNotificationTemplateException(msg.toString());
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; and field = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @param field the field
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate fetchByC_NT_NTT_F(long calendarId,
		String notificationType, String notificationTemplateType, String field)
		throws SystemException {
		return fetchByC_NT_NTT_F(calendarId, notificationType,
			notificationTemplateType, field, true);
	}

	/**
	 * Returns the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; and field = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @param field the field
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate fetchByC_NT_NTT_F(long calendarId,
		String notificationType, String notificationTemplateType, String field,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				calendarId, notificationType, notificationTemplateType, field
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F,
					finderArgs, this);
		}

		if (result instanceof CalendarNotificationTemplate) {
			CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)result;

			if ((calendarId != calendarNotificationTemplate.getCalendarId()) ||
					!Validator.equals(notificationType,
						calendarNotificationTemplate.getNotificationType()) ||
					!Validator.equals(notificationTemplateType,
						calendarNotificationTemplate.getNotificationTemplateType()) ||
					!Validator.equals(field,
						calendarNotificationTemplate.getField())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_C_NT_NTT_F_CALENDARID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_2);
			}

			boolean bindNotificationTemplateType = false;

			if (notificationTemplateType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_1);
			}
			else if (notificationTemplateType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_3);
			}
			else {
				bindNotificationTemplateType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_2);
			}

			boolean bindField = false;

			if (field == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_FIELD_1);
			}
			else if (field.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_FIELD_3);
			}
			else {
				bindField = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_F_FIELD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindNotificationTemplateType) {
					qPos.add(notificationTemplateType);
				}

				if (bindField) {
					qPos.add(field);
				}

				List<CalendarNotificationTemplate> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CalendarNotificationTemplatePersistenceImpl.fetchByC_NT_NTT_F(long, String, String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					CalendarNotificationTemplate calendarNotificationTemplate = list.get(0);

					result = calendarNotificationTemplate;

					cacheResult(calendarNotificationTemplate);

					if ((calendarNotificationTemplate.getCalendarId() != calendarId) ||
							(calendarNotificationTemplate.getNotificationType() == null) ||
							!calendarNotificationTemplate.getNotificationType()
															 .equals(notificationType) ||
							(calendarNotificationTemplate.getNotificationTemplateType() == null) ||
							!calendarNotificationTemplate.getNotificationTemplateType()
															 .equals(notificationTemplateType) ||
							(calendarNotificationTemplate.getField() == null) ||
							!calendarNotificationTemplate.getField()
															 .equals(field)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F,
							finderArgs, calendarNotificationTemplate);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F,
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
			return (CalendarNotificationTemplate)result;
		}
	}

	/**
	 * Removes the calendar notification template where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; and field = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @param field the field
	 * @return the calendar notification template that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate removeByC_NT_NTT_F(long calendarId,
		String notificationType, String notificationTemplateType, String field)
		throws NoSuchNotificationTemplateException, SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = findByC_NT_NTT_F(calendarId,
				notificationType, notificationTemplateType, field);

		return remove(calendarNotificationTemplate);
	}

	/**
	 * Returns the number of calendar notification templates where calendarId = &#63; and notificationType = &#63; and notificationTemplateType = &#63; and field = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param notificationType the notification type
	 * @param notificationTemplateType the notification template type
	 * @param field the field
	 * @return the number of matching calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_NT_NTT_F(long calendarId, String notificationType,
		String notificationTemplateType, String field)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_NT_NTT_F;

		Object[] finderArgs = new Object[] {
				calendarId, notificationType, notificationTemplateType, field
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_C_NT_NTT_F_CALENDARID_2);

			boolean bindNotificationType = false;

			if (notificationType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_1);
			}
			else if (notificationType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_3);
			}
			else {
				bindNotificationType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_2);
			}

			boolean bindNotificationTemplateType = false;

			if (notificationTemplateType == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_1);
			}
			else if (notificationTemplateType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_3);
			}
			else {
				bindNotificationTemplateType = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_2);
			}

			boolean bindField = false;

			if (field == null) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_FIELD_1);
			}
			else if (field.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_NT_NTT_F_FIELD_3);
			}
			else {
				bindField = true;

				query.append(_FINDER_COLUMN_C_NT_NTT_F_FIELD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (bindNotificationType) {
					qPos.add(notificationType);
				}

				if (bindNotificationTemplateType) {
					qPos.add(notificationTemplateType);
				}

				if (bindField) {
					qPos.add(field);
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

	private static final String _FINDER_COLUMN_C_NT_NTT_F_CALENDARID_2 = "calendarNotificationTemplate.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_1 = "calendarNotificationTemplate.notificationType IS NULL AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_2 = "calendarNotificationTemplate.notificationType = ? AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTYPE_3 = "(calendarNotificationTemplate.notificationType IS NULL OR calendarNotificationTemplate.notificationType = '') AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_1 =
		"calendarNotificationTemplate.notificationTemplateType IS NULL AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_2 =
		"calendarNotificationTemplate.notificationTemplateType = ? AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_NOTIFICATIONTEMPLATETYPE_3 =
		"(calendarNotificationTemplate.notificationTemplateType IS NULL OR calendarNotificationTemplate.notificationTemplateType = '') AND ";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_FIELD_1 = "calendarNotificationTemplate.field IS NULL";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_FIELD_2 = "calendarNotificationTemplate.field = ?";
	private static final String _FINDER_COLUMN_C_NT_NTT_F_FIELD_3 = "(calendarNotificationTemplate.field IS NULL OR calendarNotificationTemplate.field = '')";

	/**
	 * Caches the calendar notification template in the entity cache if it is enabled.
	 *
	 * @param calendarNotificationTemplate the calendar notification template
	 */
	public void cacheResult(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		EntityCacheUtil.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			calendarNotificationTemplate.getPrimaryKey(),
			calendarNotificationTemplate);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F,
			new Object[] {
				calendarNotificationTemplate.getCalendarId(),
				calendarNotificationTemplate.getNotificationType(),
				calendarNotificationTemplate.getNotificationTemplateType(),
				calendarNotificationTemplate.getField()
			}, calendarNotificationTemplate);

		calendarNotificationTemplate.resetOriginalValues();
	}

	/**
	 * Caches the calendar notification templates in the entity cache if it is enabled.
	 *
	 * @param calendarNotificationTemplates the calendar notification templates
	 */
	public void cacheResult(
		List<CalendarNotificationTemplate> calendarNotificationTemplates) {
		for (CalendarNotificationTemplate calendarNotificationTemplate : calendarNotificationTemplates) {
			if (EntityCacheUtil.getResult(
						CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
						CalendarNotificationTemplateImpl.class,
						calendarNotificationTemplate.getPrimaryKey()) == null) {
				cacheResult(calendarNotificationTemplate);
			}
			else {
				calendarNotificationTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all calendar notification templates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CalendarNotificationTemplateImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CalendarNotificationTemplateImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the calendar notification template.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		EntityCacheUtil.removeResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			calendarNotificationTemplate.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(calendarNotificationTemplate);
	}

	@Override
	public void clearCache(
		List<CalendarNotificationTemplate> calendarNotificationTemplates) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CalendarNotificationTemplate calendarNotificationTemplate : calendarNotificationTemplates) {
			EntityCacheUtil.removeResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
				CalendarNotificationTemplateImpl.class,
				calendarNotificationTemplate.getPrimaryKey());

			clearUniqueFindersCache(calendarNotificationTemplate);
		}
	}

	protected void cacheUniqueFindersCache(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		if (calendarNotificationTemplate.isNew()) {
			Object[] args = new Object[] {
					calendarNotificationTemplate.getCalendarId(),
					calendarNotificationTemplate.getNotificationType(),
					calendarNotificationTemplate.getNotificationTemplateType(),
					calendarNotificationTemplate.getField()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_NT_NTT_F, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F, args,
				calendarNotificationTemplate);
		}
		else {
			CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl =
				(CalendarNotificationTemplateModelImpl)calendarNotificationTemplate;

			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_NT_NTT_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplate.getCalendarId(),
						calendarNotificationTemplate.getNotificationType(),
						calendarNotificationTemplate.getNotificationTemplateType(),
						calendarNotificationTemplate.getField()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_NT_NTT_F,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F,
					args, calendarNotificationTemplate);
			}
		}
	}

	protected void clearUniqueFindersCache(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl =
			(CalendarNotificationTemplateModelImpl)calendarNotificationTemplate;

		Object[] args = new Object[] {
				calendarNotificationTemplate.getCalendarId(),
				calendarNotificationTemplate.getNotificationType(),
				calendarNotificationTemplate.getNotificationTemplateType(),
				calendarNotificationTemplate.getField()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_NT_NTT_F, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F, args);

		if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_NT_NTT_F.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarNotificationTemplateModelImpl.getOriginalCalendarId(),
					calendarNotificationTemplateModelImpl.getOriginalNotificationType(),
					calendarNotificationTemplateModelImpl.getOriginalNotificationTemplateType(),
					calendarNotificationTemplateModelImpl.getOriginalField()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_NT_NTT_F, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_NT_NTT_F, args);
		}
	}

	/**
	 * Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	 *
	 * @param calendarNotificationTemplateId the primary key for the new calendar notification template
	 * @return the new calendar notification template
	 */
	public CalendarNotificationTemplate create(
		long calendarNotificationTemplateId) {
		CalendarNotificationTemplate calendarNotificationTemplate = new CalendarNotificationTemplateImpl();

		calendarNotificationTemplate.setNew(true);
		calendarNotificationTemplate.setPrimaryKey(calendarNotificationTemplateId);

		String uuid = PortalUUIDUtil.generate();

		calendarNotificationTemplate.setUuid(uuid);

		return calendarNotificationTemplate;
	}

	/**
	 * Removes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template that was removed
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate remove(
		long calendarNotificationTemplateId)
		throws NoSuchNotificationTemplateException, SystemException {
		return remove((Serializable)calendarNotificationTemplateId);
	}

	/**
	 * Removes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar notification template
	 * @return the calendar notification template that was removed
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarNotificationTemplate remove(Serializable primaryKey)
		throws NoSuchNotificationTemplateException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)session.get(CalendarNotificationTemplateImpl.class,
					primaryKey);

			if (calendarNotificationTemplate == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(calendarNotificationTemplate);
		}
		catch (NoSuchNotificationTemplateException nsee) {
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
	protected CalendarNotificationTemplate removeImpl(
		CalendarNotificationTemplate calendarNotificationTemplate)
		throws SystemException {
		calendarNotificationTemplate = toUnwrappedModel(calendarNotificationTemplate);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calendarNotificationTemplate)) {
				calendarNotificationTemplate = (CalendarNotificationTemplate)session.get(CalendarNotificationTemplateImpl.class,
						calendarNotificationTemplate.getPrimaryKeyObj());
			}

			if (calendarNotificationTemplate != null) {
				session.delete(calendarNotificationTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (calendarNotificationTemplate != null) {
			clearCache(calendarNotificationTemplate);
		}

		return calendarNotificationTemplate;
	}

	@Override
	public CalendarNotificationTemplate updateImpl(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate)
		throws SystemException {
		calendarNotificationTemplate = toUnwrappedModel(calendarNotificationTemplate);

		boolean isNew = calendarNotificationTemplate.isNew();

		CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl =
			(CalendarNotificationTemplateModelImpl)calendarNotificationTemplate;

		if (Validator.isNull(calendarNotificationTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendarNotificationTemplate.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (calendarNotificationTemplate.isNew()) {
				session.save(calendarNotificationTemplate);

				calendarNotificationTemplate.setNew(false);
			}
			else {
				session.merge(calendarNotificationTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!CalendarNotificationTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((calendarNotificationTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarNotificationTemplateModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						calendarNotificationTemplateModelImpl.getUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		EntityCacheUtil.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
			CalendarNotificationTemplateImpl.class,
			calendarNotificationTemplate.getPrimaryKey(),
			calendarNotificationTemplate);

		clearUniqueFindersCache(calendarNotificationTemplate);
		cacheUniqueFindersCache(calendarNotificationTemplate);

		return calendarNotificationTemplate;
	}

	protected CalendarNotificationTemplate toUnwrappedModel(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		if (calendarNotificationTemplate instanceof CalendarNotificationTemplateImpl) {
			return calendarNotificationTemplate;
		}

		CalendarNotificationTemplateImpl calendarNotificationTemplateImpl = new CalendarNotificationTemplateImpl();

		calendarNotificationTemplateImpl.setNew(calendarNotificationTemplate.isNew());
		calendarNotificationTemplateImpl.setPrimaryKey(calendarNotificationTemplate.getPrimaryKey());

		calendarNotificationTemplateImpl.setUuid(calendarNotificationTemplate.getUuid());
		calendarNotificationTemplateImpl.setCalendarNotificationTemplateId(calendarNotificationTemplate.getCalendarNotificationTemplateId());
		calendarNotificationTemplateImpl.setCalendarId(calendarNotificationTemplate.getCalendarId());
		calendarNotificationTemplateImpl.setNotificationType(calendarNotificationTemplate.getNotificationType());
		calendarNotificationTemplateImpl.setNotificationTemplateType(calendarNotificationTemplate.getNotificationTemplateType());
		calendarNotificationTemplateImpl.setField(calendarNotificationTemplate.getField());
		calendarNotificationTemplateImpl.setContent(calendarNotificationTemplate.getContent());

		return calendarNotificationTemplateImpl;
	}

	/**
	 * Returns the calendar notification template with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar notification template
	 * @return the calendar notification template
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarNotificationTemplate findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchNotificationTemplateException, SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = fetchByPrimaryKey(primaryKey);

		if (calendarNotificationTemplate == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template with the primary key or throws a {@link com.liferay.calendar.NoSuchNotificationTemplateException} if it could not be found.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template
	 * @throws com.liferay.calendar.NoSuchNotificationTemplateException if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate findByPrimaryKey(
		long calendarNotificationTemplateId)
		throws NoSuchNotificationTemplateException, SystemException {
		return findByPrimaryKey((Serializable)calendarNotificationTemplateId);
	}

	/**
	 * Returns the calendar notification template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar notification template
	 * @return the calendar notification template, or <code>null</code> if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarNotificationTemplate fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		CalendarNotificationTemplate calendarNotificationTemplate = (CalendarNotificationTemplate)EntityCacheUtil.getResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
				CalendarNotificationTemplateImpl.class, primaryKey);

		if (calendarNotificationTemplate == _nullCalendarNotificationTemplate) {
			return null;
		}

		if (calendarNotificationTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				calendarNotificationTemplate = (CalendarNotificationTemplate)session.get(CalendarNotificationTemplateImpl.class,
						primaryKey);

				if (calendarNotificationTemplate != null) {
					cacheResult(calendarNotificationTemplate);
				}
				else {
					EntityCacheUtil.putResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
						CalendarNotificationTemplateImpl.class, primaryKey,
						_nullCalendarNotificationTemplate);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CalendarNotificationTemplateModelImpl.ENTITY_CACHE_ENABLED,
					CalendarNotificationTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return calendarNotificationTemplate;
	}

	/**
	 * Returns the calendar notification template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarNotificationTemplateId the primary key of the calendar notification template
	 * @return the calendar notification template, or <code>null</code> if a calendar notification template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarNotificationTemplate fetchByPrimaryKey(
		long calendarNotificationTemplateId) throws SystemException {
		return fetchByPrimaryKey((Serializable)calendarNotificationTemplateId);
	}

	/**
	 * Returns all the calendar notification templates.
	 *
	 * @return the calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarNotificationTemplate> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar notification templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @return the range of calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarNotificationTemplate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar notification templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar notification templates
	 * @param end the upper bound of the range of calendar notification templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarNotificationTemplate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<CalendarNotificationTemplate> list = (List<CalendarNotificationTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE;

				if (pagination) {
					sql = sql.concat(CalendarNotificationTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CalendarNotificationTemplate>(list);
				}
				else {
					list = (List<CalendarNotificationTemplate>)QueryUtil.list(q,
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
	 * Removes all the calendar notification templates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CalendarNotificationTemplate calendarNotificationTemplate : findAll()) {
			remove(calendarNotificationTemplate);
		}
	}

	/**
	 * Returns the number of calendar notification templates.
	 *
	 * @return the number of calendar notification templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE);

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
	 * Initializes the calendar notification template persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.calendar.model.CalendarNotificationTemplate")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CalendarNotificationTemplate>> listenersList = new ArrayList<ModelListener<CalendarNotificationTemplate>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CalendarNotificationTemplate>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CalendarNotificationTemplateImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE = "SELECT calendarNotificationTemplate FROM CalendarNotificationTemplate calendarNotificationTemplate";
	private static final String _SQL_SELECT_CALENDARNOTIFICATIONTEMPLATE_WHERE = "SELECT calendarNotificationTemplate FROM CalendarNotificationTemplate calendarNotificationTemplate WHERE ";
	private static final String _SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE = "SELECT COUNT(calendarNotificationTemplate) FROM CalendarNotificationTemplate calendarNotificationTemplate";
	private static final String _SQL_COUNT_CALENDARNOTIFICATIONTEMPLATE_WHERE = "SELECT COUNT(calendarNotificationTemplate) FROM CalendarNotificationTemplate calendarNotificationTemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarNotificationTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalendarNotificationTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalendarNotificationTemplate exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CalendarNotificationTemplatePersistenceImpl.class);
	private static CalendarNotificationTemplate _nullCalendarNotificationTemplate =
		new CalendarNotificationTemplateImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CalendarNotificationTemplate> toCacheModel() {
				return _nullCalendarNotificationTemplateCacheModel;
			}
		};

	private static CacheModel<CalendarNotificationTemplate> _nullCalendarNotificationTemplateCacheModel =
		new CacheModel<CalendarNotificationTemplate>() {
			public CalendarNotificationTemplate toEntityModel() {
				return _nullCalendarNotificationTemplate;
			}
		};
}