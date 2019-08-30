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

package com.jlarrieux.ethereum.Entity;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Jeannius on 8/29/2019
 */

public class ResponseTypeAdapter extends TypeAdapter<RestResponse> {
    private static final String PRICE = "price";
    private static final String VOLUME = "volume";
    private static final String MARKET_CAP = "marketCap";
    private static final String ALL_TIME_HIGH = "allTimeHigh";

    @Override
    public void write(JsonWriter out, RestResponse response) throws IOException {
        out.beginObject();
        out.name(PRICE).value(response.getPrice_usd().getValue());
        if(response.getVolume_last_24_hours()!=null){
            out.name(VOLUME).value(response.getVolume_last_24_hours().getValue());
        }
        if(response.getAll_time_high() != null){
            out.name(ALL_TIME_HIGH).value(response.getAll_time_high().getValue());
        }

        if(response.getMarket_cap() != null){
            out.name(MARKET_CAP).value(response.getMarket_cap().getValue());
        }
        out.endObject();
    }

    @Override
    public RestResponse read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        RestResponse.RestResponseBuilder builder = RestResponse.builder();
        if(token.equals(JsonToken.BEGIN_OBJECT)){
            while(in.hasNext()){
                switch (in.nextName()){
                    case PRICE:
                        builder.price(in.nextString());
                        break;
                    case VOLUME:
                        builder.volume(in.nextString());
                        break;
                    case MARKET_CAP:
                        builder.marketCap(in.nextString());
                        break;
                    case ALL_TIME_HIGH:
                        builder.allTimeHigh(in.nextString());
                        break;
                }
            }

            in.endObject();
        }

        return builder.build();
    }
}
