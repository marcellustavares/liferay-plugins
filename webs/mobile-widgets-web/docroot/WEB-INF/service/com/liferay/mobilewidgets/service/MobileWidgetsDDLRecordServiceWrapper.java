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

package com.liferay.mobilewidgets.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MobileWidgetsDDLRecordService}.
 *
 * @author Jos�� Manuel Navarro
 * @see MobileWidgetsDDLRecordService
 * @generated
 */
public class MobileWidgetsDDLRecordServiceWrapper
	implements MobileWidgetsDDLRecordService,
		ServiceWrapper<MobileWidgetsDDLRecordService> {
	public MobileWidgetsDDLRecordServiceWrapper(
		MobileWidgetsDDLRecordService mobileWidgetsDDLRecordService) {
		_mobileWidgetsDDLRecordService = mobileWidgetsDDLRecordService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mobileWidgetsDDLRecordService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mobileWidgetsDDLRecordService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mobileWidgetsDDLRecordService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public int getDDLRecordsCount(long recordSetId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsDDLRecordService.getDDLRecordsCount(recordSetId,
			userId);
	}

	@Override
	public java.util.List<java.util.HashMap> getDDLRecords(long recordSetId,
		long userId, int start, int end, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsDDLRecordService.getDDLRecords(recordSetId,
			userId, start, end, locale);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.String> getDDLRecordValues(
		long ddlRecordId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsDDLRecordService.getDDLRecordValues(ddlRecordId,
			locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MobileWidgetsDDLRecordService getWrappedMobileWidgetsDDLRecordService() {
		return _mobileWidgetsDDLRecordService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMobileWidgetsDDLRecordService(
		MobileWidgetsDDLRecordService mobileWidgetsDDLRecordService) {
		_mobileWidgetsDDLRecordService = mobileWidgetsDDLRecordService;
	}

	@Override
	public MobileWidgetsDDLRecordService getWrappedService() {
		return _mobileWidgetsDDLRecordService;
	}

	@Override
	public void setWrappedService(
		MobileWidgetsDDLRecordService mobileWidgetsDDLRecordService) {
		_mobileWidgetsDDLRecordService = mobileWidgetsDDLRecordService;
	}

	private MobileWidgetsDDLRecordService _mobileWidgetsDDLRecordService;
}