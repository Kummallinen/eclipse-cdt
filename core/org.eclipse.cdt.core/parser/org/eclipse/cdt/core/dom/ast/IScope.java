/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM - Initial API and implementation
 * Markus Schorn (Wind River Systems)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.ast;

import org.eclipse.cdt.core.dom.IName;


/**
 * 
 * @author Doug Schaefer
 */
public interface IScope {

	/**
     * Get the IName for this scope, may be null 
     * @return
     * @throws DOMException
     */
    public IName getScopeName() throws DOMException;
    
	/**
	 * Scopes are arranged hierarchically. Lookups will generally
	 * flow upward to find resolution.
	 * 
	 * @return
	 */
	public IScope getParent() throws DOMException;

	/**
	 * This is the general lookup entry point. It returns the list of
	 * valid bindings for a given name.  The lookup proceeds as an unqualified
	 * lookup.  Constructors are not considered during this lookup and won't be returned.
	 * No attempt is made to resolve potential ambiguities or perform access checking.
	 * 
	 * @param searchString
	 * @return List of IBinding
	 */
	public IBinding[] find(String name) throws DOMException;
        
    /**
     * The IScope serves as a mechanism for caching IASTNames and bindings to
     * speed up resolution.
     * mstodo more work: remove the ast-specific methods.
     */
    
    /**
	 * Add an IASTName to be cached in this scope
	 * 
	 * @param name
	 * @throws DOMException
	 */
	public void addName(IASTName name) throws DOMException;

	/**
	 * remove the given binding from this scope
	 * 
	 * @param binding
	 * @throws DOMException
	 */
	void removeBinding(IBinding binding) throws DOMException;
	
	/**
	 * Get the binding in this scope that the given name would resolve to. Could
	 * return null if there is no matching binding in this scope, if the binding has not
	 * yet been cached in this scope, or if resolve == false and the appropriate binding 
	 * has not yet been resolved.
	 * 
	 * @param name
	 * @param resolve :
	 *            whether or not to resolve the matching binding if it has not
	 *            been so already.
	 * @return : the binding in this scope that matches the name, or null
	 * @throws DOMException
	 */
	public IBinding getBinding(IASTName name, boolean resolve)
			throws DOMException;
	
	/**
	 * This adds an IBinding to the scope.  It is primarily used by the parser to add
	 * implicit IBindings to the scope (such as GCC built-in functions).
	 * 
	 * @param binding
	 * @throws DOMException
	 */
	public void addBinding(IBinding binding) throws DOMException;
}
