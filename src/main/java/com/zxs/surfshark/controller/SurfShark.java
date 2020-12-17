package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxs.surfshark.beans.SurfSharkEntity;
import com.zxs.surfshark.service.SurfSharkService;
import com.zxs.surfshark.service.impl.SurfSharkServiceImpl;
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
import java.security.Provider;
import java.util.Collections;

@RestController
public class SurfShark {
    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 1080;

    @RequestMapping(value = "/surfshark")
    public String surfshark(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(this.httpClientFactory());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://my.surfshark.com/vpn/api/v1/server/clusters",String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        JSONArray jsonArray= JSONArray.parseArray(body);
        if (jsonArray != null) {
            SurfSharkService surfSharkService = new SurfSharkServiceImpl();
            SurfSharkEntity surfSharkEntity = new SurfSharkEntity();
            for (int i = 0; i<jsonArray.size(); i++) {
                surfSharkEntity.setCountry(jsonArray.getJSONObject(i).get("country").toString());
                surfSharkEntity.setConnectionName(jsonArray.getJSONObject(i).get("connectionName").toString());
                surfSharkEntity.setRegion(jsonArray.getJSONObject(i).get("region").toString());
                surfSharkEntity.setRegionCode(jsonArray.getJSONObject(i).get("regionCode").toString());
                surfSharkEntity.setLocation(jsonArray.getJSONObject(i).get("location").toString());
                surfSharkEntity.setCountryCode(jsonArray.getJSONObject(i).get("countryCode").toString());
                surfSharkEntity.setLoads(jsonArray.getJSONObject(i).getInteger("load"));
                surfSharkEntity.setLatitude(jsonArray.getJSONObject(i).getDoubleValue("latitude"));
                surfSharkEntity.setLongitude(jsonArray.getJSONObject(i).getDoubleValue("longitude"));
                surfSharkEntity.setType(jsonArray.getJSONObject(i).get("type").toString());
                surfSharkService.insertSurfshark(surfSharkEntity);
            }
        }
        return  "responseEntity.getBody()：" + body + "<hr>" +
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
