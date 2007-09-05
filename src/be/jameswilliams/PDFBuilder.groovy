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
package be.jameswilliams

import com.lowagie.text.Document
import com.lowagie.text.DocumentException
import com.lowagie.text.Paragraph
import com.lowagie.text.PageSize
import com.lowagie.text.pdf.PdfWriter
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
public class PDFBuilder extends BuilderSupport{
	private Logger log = Logger.getLogger(getClass().getName())
    private Map factories = new HashMap()
	static writer
	private elements = [ ]
    public shortcuts = [:]
    Map widgets = [:]
    def document
    def metadata = ['title', 'subject', 'keywords', 'creator', 'author']
    
	PDFBuilder() {
		registerComponents()
    }
	
	def chunkFactory = {
		Chunk a = new Chunk(attributes.remove("text"))
		return a
	}
	
    void registerComponents() {
		registerBeanFactory("document", Document.class)
		registerBeanFactory("paragraph", Paragraph.class)
		registerBeanFactory("phrase", Phrase.class)
		//registerBeanFactory("chunk", Chunk.class)
		registerBeanFactory("image", ImageFacade.class)
		registerBeanFactory("table", TableFacade.class)
		registerBeanFactory("cell", TableCellFacade.class)
		registerBeanFactory("row", PdfPRow.class)
		registerBeanFactory("alignedText", AlignedTextFacade.class)
		registerBeanFactory("directContent", DirectContentFacade.class)
		registerBeanFactory("page", PageFacade.class)
		registerBeanFactory("widget", WidgetFacade.class)
    }
	
    Object createNode(name) {
		return createNode(name, null, null)
    }
	
    Object createNode(name,value) {
		return createNode(name,null,value)
    }
	
    Object createNode(name, Map attributes) {
		return createNode(name, attributes, null)
    }
	
	Object createNode(name, Map attributes, value) {
		def widget = null
		def factory = (Closure) factories.get(name)
		
		// stuff with getInstance methods have to be
		// handled differently
		// move to custom factories later
		if (name == "chunk") {
			widget = new Chunk(attributes.remove("text"))
			processAttributes(name,widget,attributes)
			return widget
		}
		
		String widgetName = (String) attributes?.remove("id")
        if (factory == null) {
            log.log(Level.WARNING, "Could not find match for name: " + name)
            return null
        }
                
        try {
            widget = factory()
			if (widget == null) {
                log.log(Level.WARNING, "Factory for name: " + name + " returned null")
                return null
            }
            if (log.isLoggable(Level.FINE)) {
                log.fine("For name: " + name + " created widget: " + widget)
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create component for '" + name + "' reason: " + e, e)
        }
		println name
		processAttributes(name,widget, attributes)
		
		return widget
	}
	
	void processAttributes(widgetName, widget, attributes) {
		println "processing attrib "+ widget
		if ( widget instanceof Paragraph || widget instanceof Phrase) {
			if (attributes.text != null) {
				println widgetName
				widget.add(new Chunk(attributes.remove("text")))
			}
			if (attributes.margins != null) {
				def margins = attributes.remove("margins")
				document.setMargins(margins[0], margins[1], margins[2], margins[3])
			}
			if (attributes.marginMirroring != null) {
				document.setMarginMirroring(attributes.remove("marginMirroring"))
			}
		}
		if ( widget instanceof Document) {
			document = widget
			if (attributes.filename != null) {
				def filename = attributes.remove('filename')
				writer = PdfWriter.getInstance(widget, new FileOutputStream(filename))
			}
			//margins need to be specially handled
			if (attributes.margins != null) {
				def margins = attributes.remove("margins")
				widget.setMargins(margins[0], margins[1], margins[2], margins[3])
			}
		}
		/*else if (widgetName == "writeDirectTextContent") {
			widget.add(attributes)
		}*/
		
		//Handle metadata properties
		//These must be set BEFORE the document is opened.
		for (entry in metadata) {
			if (attributes[entry] != null) {
				def methodName = "add" + entry.substring(0,1).toUpperCase() + entry.substring(1,entry.size())
				InvokerHelper.invokeMethod(document, methodName, attributes.remove(entry))
			}
		}
		for (entry in attributes) {
			println widgetName +" "+ entry
            String property = entry.getKey().toString()
            Object value = entry.getValue()
			if (property != "content")
				InvokerHelper.setProperty(widget, property, value)
			else InvokerHelper.setProperty(widget, property, new StringBuffer(value))
        }
        
        //Document instance is split so properties are properly set
        //before opening the document and that the document is
        //opened only once.
        if (widget instanceof Document && !widget.isOpen())
        	widget.open()
	}
	
	void setParent(parent,child) {	}
	
	void nodeCompleted(parent,node) {
		println parent
		println node
		if (node instanceof Chunk) {
			parent.add(node)
		}
		else if (node instanceof Paragraph) {
			parent.add(node)
		}
		else if (node instanceof Document) {
			println "closing document"
			node.close()
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
	
	def createFactory = { a -> return { return a.newInstance() } }

    public void registerBeanFactory(String name, final Class beanClass) {
        registerFactory(name, createFactory(beanClass))
    }
    
    public void registerFactory(String name, Closure factory) {
        factories.put(name, factory);
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
