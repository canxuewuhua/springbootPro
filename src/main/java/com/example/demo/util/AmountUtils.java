package com.example.demo.util;

import java.math.BigDecimal;

public class AmountUtils {

    /**
     * BigDecimal累加
     *
     * @param addends
     * @return
     */
    public static final BigDecimal adds(BigDecimal... addends) {
        BigDecimal result = BigDecimal.ZERO;

        if (addends == null) {
            return result;
        }

        for (BigDecimal num : addends) {
            if (num != null) {
                result = result.add(num);
            }
        }
        return result;
    }


    /**
     * BigDecimal相减
     *
     * @param val1
     * @param val2
     * @return
     */
    public static final BigDecimal subtract(BigDecimal val1, BigDecimal val2) {
        BigDecimal result = BigDecimal.ZERO;

        if (val1 == null && val2 == null) {
            return result;
        } else if (val1 == null && val2 != null) {
            return BigDecimal.ZERO.subtract(val2);
        } else if (val1 != null && val2 == null) {
            return val1;
        } else {
            return val1.subtract(val2);
        }
    }

    /**
     * 判断是否是大于0
     *
     * @param val
     * @return
     */
    public static final boolean positiveNumber(BigDecimal val) {

        if (val == null) {
            return false;
        }

        if (BigDecimal.ZERO.compareTo(val) < 0) {
            return true;
        }

        return false;
    }
}
