package com.zxs.surfshark.util;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;


public class HttpClientFactory {
    //配置代理
    private static final String PROXY_HOST = "2.58.241.45";
    private static final int PROXY_PORT = 38011;
    private static final String PROXY_USERNAME = "YJnXy5ycMmJhbkNh8RS7QERy";
    private static final String PROXY_PASSWORD = "kQy5aEegUX2kEu4HEq5EYKqs";
    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory(){
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(35000);
        httpRequestFactory.setConnectTimeout(6000);
        SocketAddress address = new InetSocketAddress(PROXY_HOST,PROXY_PORT);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
        httpRequestFactory.setProxy(proxy);
        Authenticator.setDefault(new BasicAuthenticator(PROXY_USERNAME,PROXY_PASSWORD));
        return httpRequestFactory;
    }

}
