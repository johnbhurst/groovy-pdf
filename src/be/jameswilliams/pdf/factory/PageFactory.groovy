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
import be.jameswilliams.pdf.PDFBuilder
import groovy.util.AbstractFactory

public class PageFactory extends AbstractFactory  {
	def content = []
	
	Object newInstance( FactoryBuilderSupport builder, Object name, Object value, Map attributes ) {
		if (attributes?.pageSize != null)
			builder.document.setPageSize(attributes.remove("pageSize"))
		builder.document.newPage()
		return this
	}
	
	void processContent(FactoryBuilderSupport builder) {
		def doc = builder.document
		for(item in content) {
			println "process content"
			doc.add(item)
		}
	}
	
	boolean onHandleNodeAttributes( FactoryBuilderSupport builder, Object node, Map attributes ) {
		return false
	}
	
	void setParent( FactoryBuilderSupport builder, Object parent, Object child ) { }
	
	void setChild( FactoryBuilderSupport builder, Object parent, Object child ) {
		content += child
	}
	
	boolean isLeaf() { return false }
	
	void onNodeCompleted( FactoryBuilderSupport builder, Object parent, Object node ) {
		def doc = builder.document
		println "process content"
		for(item in content) {
			println "process content"
			doc.add(item)
		}
	}
}
