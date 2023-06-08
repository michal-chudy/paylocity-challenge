package com.paylocity.benefitchallenge;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyUtils {

    public static BigDecimal getDollarValue(long centsValue) {
        return new BigDecimal(centsValue).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
    }
}
