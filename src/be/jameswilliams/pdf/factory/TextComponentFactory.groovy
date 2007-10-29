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
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.Chunk
import com.lowagie.text.Phrase
import com.lowagie.text.Paragraph

public class TextComponentFactory implements Factory {
	
	Class klazz

	public TextComponentFactory(Class klazz) {
		this.klazz = klazz
	}
	
	public Object newInstance(PDFBuilder builder, Object name, Object value, Map properties) throws InstantiationException, IllegalAccessException {
        if (PDFBuilder.checkValueIsTypeNotString(value, name, klazz)) {
            return value
        }
        
        Object widget = klazz.newInstance()
        def text
        if (value instanceof String) {
            // this does not create property setting order issues, since the value arg preceeds all properties in the builder element
            switch(widget) {
            	case Chunk:case Phrase:case Paragraph:
            		text = new Chunk(value)
            		break
            }	
        }
        else { 
        	if (properties.text != null) {
        		text = new Chunk(properties.remove("text"))
        	}
        }
        if (text != null) {
        	//Try to process whatever properties can be consumed
        	//at the chunk level, then process the rest at the next
        	//level up.
        	try {
        		builder.processAttributes(name, text, properties)
        	}
        	catch (Exception e) {
        		builder.processAttributes(name, widget, properties)
        	}
        	widget.add(text)
        }
        println "returning "+widget.class
        return widget
    } 
	
}
