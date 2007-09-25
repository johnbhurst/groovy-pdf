/*
 * Copyright 2003-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.jameswilliams.pdf.factory

import be.jameswilliams.pdf.PDFBuilder

/**
 * Original code for SwingBuilder
 * @author <a href="mailto:shemnon@yahoo.com">Danno Ferrin</a>
 * @version $Revision: 7953 $
 * @since Groovy 1.1
 * Adapted to PDFBuilder by James Williams
 */
class BeanFactory implements Factory {
    Class beanClass

    public BeanFactory(Class beanClass) {
        this.beanClass = beanClass
    }

    public Object newInstance(PDFBuilder builder, Object name, Object value, Map properties) throws InstantiationException, IllegalAccessException {
        if (PDFBuilder.checkValueIsTypeNotString(value, name, beanClass)) {
            return value
        } else {
            return beanClass.newInstance()
        }
    }
}
