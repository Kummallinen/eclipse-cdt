/*******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.lrparser.gnu;

import java.util.Set;

import org.eclipse.cdt.core.dom.ILinkage;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.lrparser.BaseExtensibleLanguage;
import org.eclipse.cdt.core.dom.lrparser.IParser;
import org.eclipse.cdt.core.dom.parser.IBuiltinBindingsProvider;
import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.cpp.GPPParserExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.cpp.GPPScannerExtensionConfiguration;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.internal.core.dom.lrparser.gpp.GPPParser;

/**
 * ILanguage implementation for the GPP parser.
 * 
 * @author Mike Kucera
 */
public class GPPLanguage extends BaseExtensibleLanguage {

	public static final String ID = "org.eclipse.cdt.core.lrparser.gpp"; //$NON-NLS-1$ 
	
	private static GPPLanguage DEFAULT = new GPPLanguage();
	
	public static GPPLanguage getDefault() {
		return DEFAULT;
	}
	
	@Override
	protected IParser<IASTTranslationUnit> getParser(IScanner scanner, IIndex index, Set<IParser.Options> options) {
		return new GPPParser(scanner, DOMToGPPTokenMap.DEFAULT_MAP, getBuiltinBindingsProvider(), index, options);
	}

	@Override
	protected IScannerExtensionConfiguration getScannerExtensionConfiguration() {
		return GPPScannerExtensionConfiguration.getInstance();
	}

	public String getId() {
		return ID;
	}

	public int getLinkageID() {
		return ILinkage.CPP_LINKAGE_ID;
	}

	@Override
	protected ParserLanguage getParserLanguage() {
		return ParserLanguage.CPP;
	}

	protected IBuiltinBindingsProvider getBuiltinBindingsProvider() {
		return new GPPParserExtensionConfiguration().getBuiltinBindingsProvider();
	}
}
