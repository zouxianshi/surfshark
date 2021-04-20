package com.zxs.surfshark.util;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class NetTool {

    public void getDomain(String ipaddress) {
        try {
            String doamin = InetAddress.getByName(ipaddress).getHostName();
            System.out.println(doamin);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getIp(String domain) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domain);
            StringBuilder str = new StringBuilder();
            for (InetAddress address : addresses) {
                str.append(address.getHostAddress());
            }
            return str.toString();
        } catch (UnknownHostException uhe) {
            System.err.println("Unable to find: " + domain);
            return "解析失败";
        }
    }

    public JSONObject getIp2(String domain) throws KeyManagementException, NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate();
        //设置代理
//        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new HttpClientFactory().httpClientFactory();
//        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);

        //设置请求头和请求体
        String url = "https://dns.google/resolve";
        Map<String, String> params = new HashMap<>();
        params.put("name", domain);
        params.put("type","1");

        //设置请求头参数
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity request = new HttpEntity(requestHeaders);
        SSLUtil.turnOffSslChecking();
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.GET, request, JSONObject.class, params);
        System.out.println(exchange.getBody());
        return exchange.getBody();
    }

    public long responseTime(String ipAddress) throws Exception {
        int timeOut = 3000;   // 超时应该在3钞以上
        long begin = System.currentTimeMillis();
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        long end = System.currentTimeMillis();
        if (status) {
            return (end - begin);
        } else {
            return 0;
        }
    }
}
