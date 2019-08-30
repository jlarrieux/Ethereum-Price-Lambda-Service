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

import org.javamoney.moneta.format.AmountFormatParams;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Locale;


/**
 * Created by Jeannius on 8/29/2019
 */

public class Money implements Serializable {

    private MonetaryAmount value;
    private static CurrencyUnit usDollars = Monetary.getCurrency(Locale.US);
    public static MonetaryAmountFormat prettyFormatter = MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(Locale.US)
                    .set(AmountFormatParams.GROUPING_SIZES, 3)
                    .set(AmountFormatParams.GROUPING_GROUPING_SEPARATORS, ",")
                    .set(AmountFormatParams.PATTERN, "$###,###.##")
                    .build());
    public static MonetaryAmountFormat bareFormatter = MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(Locale.US)
                    .set(AmountFormatParams.PATTERN, "###,###.##")
                    .build());


    public Money(String value){
        this.value = fromString(value);
    }

    private MonetaryAmount createMonetaryAmount(Double value) {
        return Monetary.getDefaultAmountFactory()
                .setCurrency(usDollars)
                .setNumber(value)
                .create();
    }

    public String getValue(){
        return bareFormatter.format(value);
    }

    public String getPrettyFormattedValue(){
        return prettyFormatter.format(value);
    }

    public static MonetaryAmount fromString(String value){
        return org.javamoney.moneta.Money.of(new BigDecimal(value), usDollars);
    }


}
