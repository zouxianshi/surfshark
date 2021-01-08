package com.zxs.surfshark.util;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;


public class HttpClientFactory {
    private static final String PROXY_HOST = "2.58.241.45";
    private static final int PROXY_PORT = 38011;
    private static final int READTIMEOUT = 20000;
    private static final int CONNECTTIMEOUT = 20000;
    public SimpleClientHttpRequestFactory httpClientFactory(){
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(READTIMEOUT);
        httpRequestFactory.setConnectTimeout(CONNECTTIMEOUT);
        SocketAddress address = new InetSocketAddress(PROXY_HOST,PROXY_PORT);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS,address);
        httpRequestFactory.setProxy(proxy);
        return httpRequestFactory;
    }
}
