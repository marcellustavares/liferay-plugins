/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.ruon.service.persistence;

/**
 * <a href="PresenceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceUtil {
	public static com.liferay.ruon.model.Presence create(long presenceId) {
		return getPersistence().create(presenceId);
	}

	public static com.liferay.ruon.model.Presence remove(long presenceId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException {
		return getPersistence().remove(presenceId);
	}

	public static com.liferay.ruon.model.Presence remove(
		com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(presence);
	}

	public static com.liferay.ruon.model.Presence update(
		com.liferay.ruon.model.Presence presence)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(presence);
	}

	public static com.liferay.ruon.model.Presence update(
		com.liferay.ruon.model.Presence presence, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(presence, merge);
	}

	public static com.liferay.ruon.model.Presence updateImpl(
		com.liferay.ruon.model.Presence presence, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(presence, merge);
	}

	public static com.liferay.ruon.model.Presence findByPrimaryKey(
		long presenceId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceException {
		return getPersistence().findByPrimaryKey(presenceId);
	}

	public static com.liferay.ruon.model.Presence fetchByPrimaryKey(
		long presenceId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(presenceId);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.ruon.model.Presence> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ruon.model.Presence> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ruon.model.Presence> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static PresencePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(PresencePersistence persistence) {
		_persistence = persistence;
	}

	private static PresencePersistence _persistence;
}