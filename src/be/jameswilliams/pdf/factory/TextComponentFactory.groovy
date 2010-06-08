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
import com.lowagie.text.Chunk
import com.lowagie.text.Phrase
import com.lowagie.text.Paragraph
import org.codehaus.groovy.runtime.InvokerHelper
import com.lowagie.text.Element
public class TextComponentFactory extends AbstractFactory {
	
	Class klazz
	def nodeName 
	def paraAttributes=[:]		// attributes that can only be processed
							// at the Paragraph or Phrase level
	public TextComponentFactory(Class klazz) {
		this.klazz = klazz
	}
	
	//This node can have subnodes if it is a Paragraph or Phrase.
	boolean isLeaf() { 
		switch (klazz) {
			case Paragraph: case Phrase:
				return false
				break
			default:
				return true 
				break
		}	
	}
	
	public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map properties) throws InstantiationException, IllegalAccessException {
        if (builder.checkValueIsTypeNotString(value, name, klazz)) {
            return value
        }
        Chunk chunk
        Object widget = klazz.newInstance() 
        
        if (value instanceof String) {
            // this does not create property setting order issues, since the value arg preceeds all properties in the builder element
            chunk = new Chunk(value)	
        }
        else { 
        	if (properties.text != null) {
        		chunk = new Chunk(properties.remove("text"))
        	}
        }
        globalAttributes(builder, properties)
        handleNodeAttributes(chunk, properties)
        if (widget instanceof Phrase || widget instanceof Paragraph) {
        	widget.add(chunk)
        	handleNodeAttributes(widget,paraAttributes)	
        }
        else widget = chunk
        
        if (builder.debug)
        	println "returning "+widget.class
        	
        println "completedNode"+widget
    	if (nodeName != null) {
    		builder.registeredNodes.put( nodeName, widget)
    	}
        
        return widget
    } 
    
    void globalAttributes(FactoryBuilderSupport builder, Map properties) {
    	nodeName = properties.remove("name")
    	if (properties?.margins != null) {
    		if (builder.debug)
    			println "margins"
			def margins = properties.remove("margins")
			//builder.document.setMargins(margins)
			builder.document.setMargins(margins[0], margins[1], margins[2], margins[3])
			if (builder.debug) {
			//	builder.document.properties.each { println it }
				def a = builder.document
			//	println "Margins: ${a.marginLeft} ${a.marginRight} ${a.marginTop} ${a.marginBottom}"
			}
		}
		if (properties?.marginMirroring != null) {
			def mirroring = properties.remove("marginMirroring")
			builder.document.setMarginMirroring(mirroring)
		}
		if (properties?.spaceCharRatio != null) {
			def spaceCharRatio = properties.remove("spaceCharRatio")
			builder.writer.setSpaceCharRatio(spaceCharRatio)
		}
		prepareMap(properties)
    }
    
    void prepareMap(Map properties) {
    	["alignment"].each {
    		if (properties.containsKey(it)) {
	    		def value = properties.remove(it)
	   			paraAttributes.put(it, value)
	    	}
    	}
    }
    
    void handleNodeAttributes(Element chunk, Map properties) {  
    	if (properties?.get("underline") != null) {
    		def u = properties.remove("underline")
    		if (u.size() == 6)
    			chunk.setUnderline(u[0], u[1], u[2], u[3], u[4], u[5])
    		else 
    			chunk.setUnderline(u[0], u[1])
    	}
    	for (entry in properties) {
            String property = entry.getKey().toString()
            Object val = entry.getValue()
			if (property != "content")
				InvokerHelper.setProperty(chunk, property, val)
			else InvokerHelper.setProperty(chunk, property, val)
			println "after property:"+property
        }
    }
    
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
    	return false
    }
	void setParent( FactoryBuilderSupport builder, Object parent, Object child ) {
		if (parent instanceof PageFactory) {
			println "adding content"
			parent.content += child
		}
	}
	void setChild( FactoryBuilderSupport builder, Object parent, Object child ) { }
	
	void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
		
	}
}
