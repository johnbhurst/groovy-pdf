// Copyright © 2007 James Williams 
//
// Licensed under the Apache License, Version 2.0 (the "License"); you may not 
// use this file except in compliance with the License. You may obtain a copy 
// of the License at 
//
// http://www.apache.org/licenses/LICENSE-2.0 
//
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
// WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
// License for the specific language governing permissions and limitations 
// under the License.
//  Author : James Williams
package be.jameswilliams.pdf.factory
import com.lowagie.text.*
import com.lowagie.text.pdf.*
import groovy.util.AbstractFactory

public class WidgetFactory extends AbstractFactory  {
	Object newInstance( FactoryBuilderSupport builder, Object name, Object value, Map attributes ) {
		//println "regNodes: "+builder.registeredNodes
		if (builder.debug) {
			println attributes
			println value
			println name
		}
		println "in widgetFactory"
		println attributes
		if (value instanceof String) {
			//find the node in the registered nodes list
			println "finding node"
			
			return builder.registeredNodes[value]
        }
        else {
        	def nodeName = attributes.remove("name")
        	return builder.registeredNodes[nodeName]
        }
	}
	
	boolean onHandleNodeAttributes( FactoryBuilderSupport builder, Object node, Map attributes ) {
		return false
	}
	
	void setParent( FactoryBuilderSupport builder, Object parent, Object child ) { }
	
	void setChild( FactoryBuilderSupport builder, Object parent, Object child ) { }
	
	boolean isLeaf() { return true }
	
	void onNodeCompleted( FactoryBuilderSupport builder, Object parent, Object node ) {
		println "parent:"+parent
		println node
		parent.add(node)
	}
}
