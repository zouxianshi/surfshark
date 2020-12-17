package com.zxs.surfshark.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

@RestController
public class SurfShark {
    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 1080;

    @RequestMapping(value = "/surfshark")
    public String surfshark(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(this.httpClientFactory());
        ResponseEntity responseEntity = restTemplate.getForEntity("https://my.surfshark.com/vpn/api/v1/server/clusters", String.class);
        String body = (String) responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        return "responseEntity.getBody()：" + body + "<hr>" +
                "responseEntity.getStatusCode()：" + statusCode + "<hr>" +
                "responseEntity.getStatusCodeValue()：" + statusCodeValue + "<hr>" +
                "responseEntity.getHeaders()：" + headers + "<hr>";
    }

    public SimpleClientHttpRequestFactory httpClientFactory(){
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(35000);
        httpRequestFactory.setConnectTimeout(6000);
        SocketAddress address = new InetSocketAddress(PROXY_HOST,PROXY_PORT);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
        httpRequestFactory.setProxy(proxy);
        return httpRequestFactory;
    }

}
