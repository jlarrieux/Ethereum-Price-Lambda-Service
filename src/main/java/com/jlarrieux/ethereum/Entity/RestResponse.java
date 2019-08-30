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

import lombok.*;

import javax.money.MonetaryAmount;

/**
 * Created by Jeannius on 8/29/2019
 */


public class RestResponse {

    @Setter @Getter
    private Money volume_last_24_hours,  market_cap, all_time_high, price_usd;
    public static RestResponseBuilder restResponseBuilder;

    public static RestResponseBuilder builder(){
        return new RestResponseBuilder();
    }


    private RestResponse(){
    }

    @Override public String toString() {
        return String.format("%s: { price: %s    volume: %s    market cap: %s    all time high: %s }", this.getClass().getSimpleName(),
                getValue(price_usd), getValue(volume_last_24_hours), getValue(market_cap), getValue(all_time_high));
    }

    private String getValue(Money amount){
        if(amount == null){
            return "null";
        }
        else {
            return amount.getPrettyFormattedValue();
        }
    }

    public static class RestResponseBuilder {
        private Money price, volume, allTimeHigh, marketCap;

        public RestResponseBuilder volume(String volume){
            this.volume = new Money(volume);
            return this;
        }

        public RestResponseBuilder allTimeHigh(String allTimeHigh){
            this.allTimeHigh = new Money(allTimeHigh);
            return this;
        }

        public RestResponseBuilder marketCap(String marketCap){
            this.marketCap = new Money(marketCap);
            return this;
        }

        public RestResponseBuilder price(String price){
            this.price = new Money(price);
            return this;
        }

        public RestResponse build(){
            RestResponse response = new RestResponse();
            if(price != null){
                response.setPrice_usd(price);
            }
            if(volume!=null){
                response.setVolume_last_24_hours(volume);
            }
            if(allTimeHigh!=null){
                response.setAll_time_high(allTimeHigh);
            }
            if(marketCap != null ){
                response.setMarket_cap(marketCap);
            }

            return response;

        }


    }

}
