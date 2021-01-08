package com.zxs.surfshark.util;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.Authenticator;

/**
 *配置代理授权
 *
 *@version2017年3月30日
 *@authorZeninte
 *@sinceJDK1.6
 *
 */
public class BasicAuthenticator extends Authenticator{
    public RestTemplate restTemplate(){
        final String username = "1813561379@qq.com";
        final String password = "silvercrow@6133";
        final String pass="pTGNJnxxz54WrZ2DvxgkHGvd";
        final String proxyUrl = "2.58.241.45";
        final int port = 38011;
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyUrl, port),
                new UsernamePasswordCredentials(pass));
        HttpHost myProxy = new HttpHost(proxyUrl, port);
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        return new RestTemplate(factory);
    }
}