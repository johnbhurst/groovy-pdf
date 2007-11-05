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
package be.jameswilliams.pdf
import com.lowagie.text.Document
import com.lowagie.text.DocumentException

import com.lowagie.text.Paragraph
import com.lowagie.text.PageSize
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.rtf.RtfWriter2
import com.lowagie.text.html.HtmlWriter
import com.lowagie.text.Chunk
import com.lowagie.text.Font
import com.lowagie.text.FontFactory
import com.lowagie.text.Phrase
import com.lowagie.text.Image
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPRow
import java.net.URL
import java.util.logging.Level
import java.util.logging.Logger
import groovy.util.BuilderSupport
import org.codehaus.groovy.runtime.InvokerHelper
import be.jameswilliams.pdf.*
import be.jameswilliams.pdf.factory.*

public class PDFBuilder extends FactoryBuilderSupport{
	private Logger log = Logger.getLogger(getClass().getName())
    private Map factories = new HashMap()
	public writers = []
	def registeredNodes = [:]
	private elements = [ ]
    public shortcuts = [:]
    Map widgets = [:]
    def document
    def debug = false
    
	PDFBuilder() {
		registerComponents()
    }
	
	def chunkFactory = {
		Chunk a = new Chunk(attributes.remove("text"))
		return a
	}
	
    void registerComponents() {
		registerFactory("document", new DocumentFactory())
		registerFactory("paragraph", new TextComponentFactory(Paragraph.class))
		registerFactory("phrase", new TextComponentFactory(Phrase.class))
		registerFactory("chunk", new TextComponentFactory(Phrase.class))
		registerBeanFactory("image", ImageFacade.class)
		registerBeanFactory("table", TableFacade.class)
		registerBeanFactory("cell", TableCellFacade.class)
		registerBeanFactory("row", PdfPRow.class)
		registerBeanFactory("alignedText", AlignedTextFacade.class)
		registerBeanFactory("directContent", DirectContentFacade.class)
		registerBeanFactory("page", PageFacade)
		registerFactory("widget", new WidgetFactory())
    }
	
	
	
	// These attributes get handled before the regular handleNodeAttributes function
	// ie special cases
	void preInstantiate( Object name, Map attributes, Object value) {
		/*println name
		println attributes
		println value*/
		if (document != null && !document.isOpen())
        	document.open()
	}
	
	void postInstantiate( Object name, Map attributes, Object node ) {
		if (name == "document") {
			document = node
		}
		//println "${name} ${attributes} ${node}"
	}
	
	void nodeCompleted(parent,node) {
		if (debug) {
			//println parent
			//println node
		}
		if ((node instanceof Paragraph || node instanceof Phrase) && !(parent instanceof Chunk)) {
			parent.add(node)
		}
		else if (node instanceof ImageFacade)
			parent.add(node.process())
		else if (node instanceof DirectContentFacade)
			node.process()
		else if (node instanceof TableFacade)
			node.process()
		else if (node instanceof AlignedTextFacade) {
				parent.add(node)
		}
		else if (parent instanceof TableFacade) {
			parent.add(node)
		}
		else if (node instanceof PageFacade) {
			println "pageFacade completed"
			node.process(parent)
		}
		else if (node instanceof WidgetFacade) {
			node.process(parent)
		}
	}
	
	Object postNodeCompletion( Object parent, Object node ) {
		if (node instanceof Document) {
			if (debug)
				println "closing document"
			node.close()
		}
	}
	
	public void addShortcut(className, propName, shortcut) {
		if (shortcuts[className] !=null) {
			shortcuts[className].put(shortcut,propName)
		}
		else {
			def a = [(shortcut.toString()):propName]
			shortcuts.put(className, a)
		}
    }
}
