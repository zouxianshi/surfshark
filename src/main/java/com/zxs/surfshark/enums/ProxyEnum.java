package com.zxs.surfshark.enums;


public enum ProxyEnum {
    SUCCESS("10000", "操作成功"),
    MQ_CONSUMER_CODE("30000","MQ消费过滤"),
    ERROR("99999","前方拥堵，请稍后!"),
    REQUEST_PARAMS_ERROR("50000","请求参数不合法!"),

    ;
    private String PROXY_HOST = "127.0.0.1";
    private String PROXY_PORT = "1080";

    ProxyEnum(String PROXY_HOST, String PROXY_PORT) {
        this.PROXY_HOST = PROXY_HOST;
        this.PROXY_PORT = PROXY_PORT;
    }

    public String getPROXY_HOST() {
        return PROXY_HOST;
    }

    public String getPROXY_PORT() {
        return PROXY_PORT;
    }
}
