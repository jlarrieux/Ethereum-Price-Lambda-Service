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

package com.jlarrieux.ethereum;

import com.google.common.math.DoubleMath;
import com.jlarrieux.ethereum.Entity.Money;

import java.math.BigDecimal;

/**
 * Created by Jeannius on 8/25/2019
 */

public class TestUtil {

    public static boolean compareDoubles(double a, double b){
        return DoubleMath.fuzzyEquals(a, b, 0.01);
    }

    public static int compareMoney(Money a, Money b){
        return new BigDecimal(a.getValue()).compareTo(new BigDecimal(b.getValue()));
    }

    public static boolean isGreaterThan(Money a, Money b){
        return compareMoney(a,b)==1;
    }

    public static boolean isEqualTo(Money a, Money b){
        return compareMoney(a, b) ==0;
    }

    public static boolean isLessThan(Money a, Money b){
        return compareMoney(a, b)==-1;
    }
}
