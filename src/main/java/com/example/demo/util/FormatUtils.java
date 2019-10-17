package com.example.demo.util;

import java.math.BigDecimal;

/**
 * 格式化工具类
 */
public class FormatUtils {

    /**
     * 将为null的BigDecimal转为0
     *
     * @param amout
     * @return
     */
    public static final BigDecimal getAmt(BigDecimal amout) {
        return amout == null ? BigDecimal.ZERO : amout;
    }
}
