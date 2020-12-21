package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxs.surfshark.beans.SurfSharkEntity;
import com.zxs.surfshark.service.SurfSharkService;
import com.zxs.surfshark.util.SSLUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SurfSharkController {
    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 1080;

    @Autowired
    private SurfSharkService surfSharkService;
    @RequestMapping(value = "/surf_shark")
    public String surfshark() throws KeyManagementException, NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(this.httpClientFactory());
        SSLUtil.turnOffSslChecking();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://my.surfshark.com/vpn/api/v1/server/clusters",String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        JSONArray jsonArray= JSONArray.parseArray(body);
        if (jsonArray != null) {
            JSONObject object;
            List<SurfSharkEntity> list = new ArrayList<>();
            for (int i = 0 ; i < jsonArray.size() ; i++) {
                object = jsonArray.getJSONObject(i);
                SurfSharkEntity surfSharkEntity =  add(object);
                list.add(surfSharkEntity);
            }
            surfSharkService.insertSurfshark(list);
        }
        return  "responseEntity.getBody()：" + body + "<hr>" +
                "responseEntity.getStatusCode()：" + statusCode + "<hr>" +
                "responseEntity.getStatusCodeValue()：" + statusCodeValue + "<hr>" +
                "responseEntity.getHeaders()：" + headers + "<hr>";
    }

    private SurfSharkEntity add(JSONObject object){
        SurfSharkEntity surfSharkEntity = new SurfSharkEntity();
        surfSharkEntity.setId(object.getString("id"));
        surfSharkEntity.setCountry(object.getString("load"));
        surfSharkEntity.setConnectionName(object.getString("ConnectionName"));
        surfSharkEntity.setRegion(object.getString("ConnectionName"));
        surfSharkEntity.setRegionCode(object.getString("regionCode"));
        surfSharkEntity.setLocation(object.getString("location"));
        surfSharkEntity.setCountryCode(object.getString("countryCode"));
        surfSharkEntity.setLoad(object.getInteger("load"));
        surfSharkEntity.setLatitude(object.getDoubleValue("latitude"));
        surfSharkEntity.setLongitude(object.getDoubleValue("longitude"));
        surfSharkEntity.setType(object.getString("type"));
        return surfSharkEntity;
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
