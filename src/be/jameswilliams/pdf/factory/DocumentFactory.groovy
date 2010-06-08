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
//  @author James Williams

package be.jameswilliams.pdf.factory
import groovy.util.AbstractFactory
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.Document
import com.lowagie.text.DocumentException
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.rtf.RtfWriter2
import com.lowagie.text.html.HtmlWriter
import org.codehaus.groovy.runtime.InvokerHelper

public class DocumentFactory extends AbstractFactory {
	def metadata = ['title', 'subject', 'keywords', 'creator', 'author']
	Object widget
	Object newInstance( FactoryBuilderSupport builder, Object name, Object value, Map attributes ) {
		widget = new Document()

		if (builder.debug)
			println attributes
		if (attributes?.filename != null) {
				if (builder.debug) {
					println "Document/filename"
				}
				def filename = attributes.remove('filename')
				builder.writer = PdfWriter.getInstance(widget, new FileOutputStream(filename))
		}
		if (attributes?.margins != null) {
    		if (builder.debug)
    			println "margins/Document"
			def margins = attributes.remove("margins")
			widget.setMargins(margins[0], margins[1], margins[2], margins[3])
			if (builder.debug) {
				//builder.document.properties.each { println it }
				def a = widget
				println "Margins: ${a.marginLeft} ${a.marginRight} ${a.marginTop} ${a.marginBottom}"
			}
		}
		if (attributes?.pdfVersion != null) {
				def version = attributes.remove("pdfVersion")
				def writer = builder.writer
				if (writer != null)
					writer.setPdfVersion(version)
		}
		//Assign metadata, if any
		for (entry in metadata) {
				if (attributes != null && attributes[entry] != null) {
					def methodName = "add" + entry.substring(0,1).toUpperCase() + entry.substring(1,entry.size())
					InvokerHelper.invokeMethod(widget, methodName, attributes.remove(entry))
				}
		}
        
        if (builder.debug)	
        	println widget
        return widget
	}
	
	boolean onHandleNodeAttributes( FactoryBuilderSupport builder, Object node, Map attributes ) {
		return true
	}
	
	void setParent( FactoryBuilderSupport builder, Object parent, Object child ) { }
	
	void setChild( FactoryBuilderSupport builder, Object parent, Object child ) { }
	
	boolean isLeaf() { return false }
	
	void onNodeCompleted( FactoryBuilderSupport builder, Object parent, Object node ) {	}
}
