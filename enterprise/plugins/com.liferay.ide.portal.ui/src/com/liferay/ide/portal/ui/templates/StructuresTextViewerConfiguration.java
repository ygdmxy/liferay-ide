/*******************************************************************************
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
 *
 *******************************************************************************/
package com.liferay.ide.portal.ui.templates;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.wst.sse.core.text.IStructuredPartitions;
import org.eclipse.wst.xml.core.text.IXMLPartitions;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;


/**
 * @author Gregory Amerson
 */
public class StructuresTextViewerConfiguration extends StructuredTextViewerConfigurationXML
{

    public StructuresTextViewerConfiguration()
    {
        super();
    }

    @Override
    protected IContentAssistProcessor[] getContentAssistProcessors( ISourceViewer sourceViewer, String partitionType )
    {
        if( partitionType == IStructuredPartitions.DEFAULT_PARTITION || partitionType == IXMLPartitions.XML_DEFAULT )
        {
            return new IContentAssistProcessor[] { new StructuresContentAssistProcessor( sourceViewer ) };
        }

        return super.getContentAssistProcessors( sourceViewer, partitionType );
    }

}
