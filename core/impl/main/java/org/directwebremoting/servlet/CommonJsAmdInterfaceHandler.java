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
package org.directwebremoting.servlet;

/**
 * A handler for interface generation requests compatible with CommonJS AMD format.
 * @author Mike Wilson
 */
public class CommonJsAmdInterfaceHandler extends BaseInterfaceHandler
{
    /* (non-Javadoc)
     * @see org.directwebremoting.servlet.BaseInterfaceHandler#getBaseInterfacePath()
     */
    @Override
    protected String getBaseInterfacePath()
    {
        return commonJsAmdInterfaceHandlerUrl;
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.servlet.BaseInterfaceHandler#generateInterface(java.lang.String, java.lang.String)
     */
    @Override
    public String generateInterfaceScript(String contextPath, String servletPath, String scriptName)
    {
        CommonJsAmdModule mod = new CommonJsAmdModule(contextPath, servletPath);
        mod.addDependency(commonJsAmdDwrBaseModulePath, "engine", "dwr");

        final StringBuilder buf = new StringBuilder();
        buf.append("  var p;\n");
        buf.append("\n");

        // Add standard interface contents
        buf.append(remoter.generateInterfaceJavaScript(scriptName, "  ", "p", contextPath + servletPath));

        buf.append("\n");
        buf.append("  return p;\n");

        mod.addContent(buf.toString());

        return mod.toString();
    }

    /**
     * Setter for the URL that this handler is available on
     * @param url the url to set
     */
    public void setCommonJsAmdInterfaceHandlerUrl(final String url)
    {
        commonJsAmdInterfaceHandlerUrl = url;
    }

    /**
     * Setter for the module path that dwr.engine is on
     * @param modulePath the modulePath to set
     */
    public void setCommonJsAmdDwrBaseModulePath(final String modulePath)
    {
        commonJsAmdDwrBaseModulePath = modulePath;
    }

    /**
     * What URL is this handler available on?
     */
    protected String commonJsAmdInterfaceHandlerUrl;

    /**
     * What module path is dwr.engine on?
     */
    protected String commonJsAmdDwrBaseModulePath;
}