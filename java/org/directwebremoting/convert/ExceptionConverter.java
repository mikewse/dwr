/*
 * Copyright 2005 Joe Walker
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
package org.directwebremoting.convert;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Map;

import org.directwebremoting.extend.MarshallException;
import org.directwebremoting.impl.PropertyDescriptorProperty;

/**
 * A special case of BeanConverter that doesn't convert StackTraces
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class ExceptionConverter extends BeanConverter
{
    /* (non-Javadoc)
     * @see org.directwebremoting.convert.BasicBeanConverter#getPropertyDescriptors(java.lang.Class, boolean, boolean)
     */
    public Map getPropertyMapFromClass(Class type, boolean readRequired, boolean writeRequired) throws MarshallException
    {
        Map descriptors = super.getPropertyMapFromClass(type, readRequired, writeRequired);
        descriptors.put("javaClassName", new PlainProperty("javaClassName", type.getName()));

        // Make sure Throwable's standard properties are added 
        // (fix for Bean Introspector peculiarities) 
        try
        {
            fixMissingThrowableProperty(descriptors, "message", "getMessage");
            fixMissingThrowableProperty(descriptors, "cause", "getCause");
        }
        catch (IntrospectionException ex)
        {
            throw new MarshallException(type, ex);
        }
        
        return descriptors;
    }
    
    /* (non-Javadoc)
     */
    protected void fixMissingThrowableProperty(Map descriptors, String name, String readMethodName) throws IntrospectionException
    {
        if (!descriptors.containsKey(name) && isAllowedByIncludeExcludeRules(name))
        {
            PropertyDescriptor descriptor = new PropertyDescriptor(name, Throwable.class, readMethodName, null); 
            descriptors.put(name, new PropertyDescriptorProperty(descriptor));
        }
    }
}
