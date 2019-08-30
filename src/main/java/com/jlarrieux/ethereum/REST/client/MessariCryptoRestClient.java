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

import com.google.gson.JsonObject;
import com.jlarrieux.ethereum.Entity.Constants;
import com.jlarrieux.ethereum.Entity.RestResponse;

/**
 * Created by Jeannius on 8/20/2019
 */

public class MessariCryptoRestClient extends RestClient {

    private static final String STATUS = "status";
    private static final String MARKET_DATA = "market_data";
    private static final String DATA = "data";
    private static final String PRICE_USD = "price_usd";


    @Override
    public RestResponse getAssetFromServer(String symbol) {
        String URI = String.format(Constants.MESSARI_PREFIX_FORMAT, symbol);
        String json = getJsonAsString(String.format(URI, symbol));
        JsonObject jsonObjectFromJsonString = getJsonObjectFromJsonString(json);
        return parseJsonObject(jsonObjectFromJsonString);
    }


    @Override
    protected RestResponse parseJsonObject(JsonObject jsonObject) {
        JsonObject dataElement = getJsonObjectFromJsonObject(jsonObject, DATA);
        JsonObject marketData = getJsonObjectFromJsonObject( dataElement, MARKET_DATA);
        RestResponse.RestResponseBuilder builder = RestResponse.builder();
        builder.price(getJsonPrimitiveFromJsonObject(marketData, PRICE_USD).getAsString());
        return builder.build();
    }

//    public static void main(String[] args) {
//        System.out.println(new MessariCryptoRestClient().getAssetFromServer(Constants.ETH));
//    }
////    public static void main(String[] args) {
////        new MessariCryptoRestClient().getAssetFromServer(Constants.ETH);
////        MonetaryAmount v = Monetary.getDefaultAmountFactory()
////                .setCurrency(Monetary.getCurrency(Locale.US))
////                .setNumber(123456789.55)
////                .create();
////        System.out.println(Asset.formatter.format(v));
////
////
////    }


}
