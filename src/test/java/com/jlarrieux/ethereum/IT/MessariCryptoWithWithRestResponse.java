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

import com.jlarrieux.ethereum.Entity.Constants;
import com.jlarrieux.ethereum.Entity.Money;
import com.jlarrieux.ethereum.Entity.RestResponse;
import com.jlarrieux.ethereum.REST.client.MessariCryptoRestClient;
import org.junit.Before;
import org.junit.Test;

import java.text.NumberFormat;

import static com.jlarrieux.ethereum.TestUtil.isGreaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jeannius on 8/25/2019
 */

public class MessariCryptoWithWithRestResponse {
    MessariCryptoRestClient client;
    RestResponse restResponse;


    @Before
    public void setUp() {
        client = new MessariCryptoRestClient();
        restResponse = client.getAssetFromServer(Constants.ETH);
    }

    @Test
    public void testAssetPriceIsPositive() {
        assertTrue(isGreaterThan(restResponse.getPrice_usd(), new Money("0")));
    }


    @Test
    public void testToString(){
        assertEquals(String.format("RestResponse: { price: %s    volume: null    market cap: null    all time high: null }",
                restResponse.getPrice_usd().getPrettyFormattedValue()),
                restResponse.toString());
    }
}
