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

package com.jlarrieux.ethereum.IT;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jlarrieux.ethereum.Entity.ResponseTypeAdapter;
import com.jlarrieux.ethereum.Entity.RestResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jeannius on 8/29/2019
 */

public class ResponseWithGsonIT {
    private String price = "15.0", volume ="123456789.5", allTimeHigh="20000.6";
    private RestResponse response;
    private Gson gson;

    @Before
    public void setUp(){
        gson = new GsonBuilder().registerTypeAdapter(RestResponse.class, new ResponseTypeAdapter()).create();
        response = RestResponse.builder()
                .price(price)
                .volume(volume)
                .allTimeHigh(allTimeHigh)
                .build();
    }
    
    
    @Test
    public void testSerializationWorks(){

        String json = gson.toJson(response);
        System.out.println(json);

    }
}
