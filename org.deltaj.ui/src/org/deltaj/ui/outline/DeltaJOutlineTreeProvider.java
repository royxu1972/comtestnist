/*
* generated by Xtext
*/
package org.deltaj.ui.outline;

import org.deltaj.deltaj.ClassAddition;
import org.deltaj.deltaj.MethodAddition;
import org.deltaj.deltaj.Configuration;
import org.deltaj.deltaj.ModuleReference;
import org.deltaj.deltaj.Field;
import org.deltaj.deltaj.Method;
import org.deltaj.deltaj.MethodModification;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;

/**
 * customization of the default outline structure
 * 
 */
public class DeltaJOutlineTreeProvider extends DefaultOutlineTreeProvider {
	
	protected void _createChildren(IOutlineNode parentNode, ClassAddition addsClass) {
		_createChildren(parentNode, addsClass.getClass_());
	}

	protected void _createChildren(IOutlineNode parentNode, MethodAddition addsMethod) {
		_createChildren(parentNode, addsMethod.getMethod());
	}

	protected void _createChildren(IOutlineNode parentNode, MethodModification modifiesMethod) {
		_createChildren(parentNode, modifiesMethod.getMethod());
	}
	
	protected boolean _isLeaf(Method method) {
		return true;
	}

	protected boolean _isLeaf(MethodAddition modifiesMethod) {
		return true;
	}

	protected boolean _isLeaf(MethodModification modifiesMethod) {
		return true;
	}

	protected boolean _isLeaf(Field field) {
		return true;
	}

	protected boolean _isLeaf(Configuration configuration) {
		return true;
	}
	
	protected boolean _isLeaf(ModuleReference deltaModule) {
		return true;
	}
}
