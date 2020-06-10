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

package com.liferay.ide.project.core.service;

import com.liferay.ide.core.util.SapphireContentAccessor;
import com.liferay.ide.core.util.SapphireUtil;
import com.liferay.ide.project.core.util.WorkspaceProductInfoUtil;
import com.liferay.ide.project.core.workspace.NewLiferayWorkspaceOp;

import java.util.List;

import org.eclipse.sapphire.DefaultValueService;
import org.eclipse.sapphire.FilteredListener;
import org.eclipse.sapphire.PropertyContentEvent;

/**
 * @author Ethan Sun
 */
public class ProductVersionDefaultValueService extends DefaultValueService implements SapphireContentAccessor {

	@Override
	public void dispose() {
		if (_op != null) {
			SapphireUtil.detachListener(_op.property(NewLiferayWorkspaceOp.PROP_PRODUCT_CATEGORY), _listener);
		}

		super.dispose();
	}

	@Override
	protected String compute() {
		String category = get(_op.getProductCategory());

		if (category != null) {
			List<String> productVersionsList = WorkspaceProductInfoUtil.getProductVersionList(
				category, get(_op.getShowAllVersionProduct()));

			productVersionsList.sort(String::compareTo);

			return productVersionsList.get(productVersionsList.size() - 1);
		}

		return null;
	}

	@Override
	protected void initDefaultValueService() {
		_listener = new FilteredListener<PropertyContentEvent>() {

			@Override
			protected void handleTypedEvent(PropertyContentEvent event) {
				String category = get(_op.getProductCategory());

				List<String> productVersionsList = WorkspaceProductInfoUtil.getProductVersionList(
					category, get(_op.getShowAllVersionProduct()));

				productVersionsList.sort(String::compareTo);

				_op.setProductVersion(productVersionsList.get(productVersionsList.size() - 1));

				refresh();
			}

		};

		_op = context(NewLiferayWorkspaceOp.class);

		SapphireUtil.attachListener(_op.property(NewLiferayWorkspaceOp.PROP_PRODUCT_CATEGORY), _listener);
	}

	private FilteredListener<PropertyContentEvent> _listener;
	private NewLiferayWorkspaceOp _op;

}