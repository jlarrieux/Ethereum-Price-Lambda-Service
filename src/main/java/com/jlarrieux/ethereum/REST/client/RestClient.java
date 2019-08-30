/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jlarrieux.ethereum.REST.client;


import com.google.gson.*;
import com.jlarrieux.ethereum.Entity.Constants;
import com.jlarrieux.ethereum.Entity.RestResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 *  Created by Jeannius on 8/20/2019
 */

public abstract class RestClient {


    protected abstract RestResponse parseJsonObject(JsonObject jsonObject);
    public abstract RestResponse getAssetFromServer(String symbol);

    static String getJsonAsString(String url)  {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .accept(Constants.APPLICATION_JSON)
                .get(ClientResponse.class);

        client.destroy();
        return handleResponse(response, url);
    }

    private static String handleResponse(ClientResponse response, String url){
        if(response.getStatus()!=200){
            throw new RuntimeException(String.format("Url: %s\n failed with HTTP error code: %d", url, response.getStatus()));
        }
        return response.getEntity(String.class);
    }

    static JsonObject getJsonObjectFromJsonString(String json) {
        return getJsonElementFromString(json).getAsJsonObject();
    }


    protected static JsonArray getJsonArrayFromJsonString(String json){
        return getJsonElementFromString(json).getAsJsonArray();
    }

    private static JsonElement getJsonElementFromString(String json){
        return new JsonParser().parse(json);
    }

    static double getDoubleFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsDouble();
    }

    static JsonPrimitive getJsonPrimitiveFromJsonObject(JsonObject jsonObject, String key){
        return jsonObject.getAsJsonPrimitive(key);
    }



    static JsonObject getJsonObjectFromJsonObject(JsonObject jsonObject, String key){
        return jsonObject.getAsJsonObject(key).getAsJsonObject();
    }

    protected static int getIntFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonPrimitive(key).getAsInt();
    }
}
