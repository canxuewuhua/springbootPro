package com.example.demo.constant;


public class ConstRedisParam {

    private static String redisStaticPrefix;

    private final static Configuration CONF = new Configuration("application.properties");

    static {
        redisStaticPrefix = CONF.get("newCore.redis.prefix");
    }

    /**
     * 生成主键ID redis key
     */
    public static final String GENERATE_ID_PRIMARY_KEY = redisStaticPrefix + "generate:id:primary";

    /**
     * 生成请求流水ID redis key
     */
    public static final String GENERATE_ID_REQUEST_KEY = redisStaticPrefix + "generate:id:request";

    /**
     * 生成Code redis key
     */
    public static final String GENERATE_ID_CODE_KEY = redisStaticPrefix + "generate:id:code";


    /**
     * 放款和还款回写产品限额redis key
     */
    public static final String LOAN_WRITE_BACK_PRODUCT_QUOTA_KEY = redisStaticPrefix + "write:back:product:quota";

    /**
     * KeyCenter读取网关配置redis key
     */
    public static final String KEY_CENTER_GATEWAY_CONFIG_KEY = redisStaticPrefix + "key:center:gateway:config";

}
