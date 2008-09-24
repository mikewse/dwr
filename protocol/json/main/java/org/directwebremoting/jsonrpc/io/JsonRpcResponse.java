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
package org.directwebremoting.jsonrpc.io;


/**
 * A Container for a JSON-RPC response
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class JsonRpcResponse
{
    /**
     * Create an Error from a request and the data to fulfill the request
     */
    public JsonRpcResponse(JsonRpcRequest request, Object result)
    {
        this.jsonrpc = request.getJsonrpc();
        this.id = request.getId();
        this.result = result;
    }

    /**
     * @return A String specifying the version of the JSON-RPC protocol.
     */
    public String getJsonrpc()
    {
        return jsonrpc;
    }

    /**
     * @see #getJsonrpc()
     */
    private final String jsonrpc;

    /**
     * @return The data that results from running a JSON-RPC request
     */
    public Object getResult()
    {
        return result;
    }

    /**
     * @see #getResult()
     */
    private final Object result;

    /**
     * A Request identifier that SHOULD be a JSON scalar (String, Number, True,
     * False), but SHOULD normally not be Null [1].
     * If omitted, the Request is a Notification.
     */
    public String getId()
    {
        return id;
    }

    /**
     * @see #getId()
     */
    private final String id;
}