package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxs.surfshark.service.SurfSharkInfoService;
import com.zxs.surfshark.util.BasicAuthenticator;
import com.zxs.surfshark.util.SSLUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.cache.CacheException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpHeaders;
import org.thymeleaf.cache.ICache;

import java.io.IOException;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class SurfSharkInfoControllerTest {

    @Test
    public static void main(String[] args){

    }

    @Test
    void insertBatch() throws KeyManagementException, NoSuchAlgorithmException {
        SurfSharkInfoController surfSharkInfoController = new SurfSharkInfoController();
        String result = surfSharkInfoController.insertBatch();
        System.out.print(result);
    }

    @Test
    void proxy(){
        String PROXY_HOST = "2.58.241.45";
        int PROXY_PORT = 38011;
        String PROXY_USERNAME = "YJnXy5ycMmJhbkNh8RS7QERy";
        String PROXY_PASSWORD = "kQy5aEegUX2kEu4HEq5EYKqs";
        URL url = null;
        try {
            url = new URL("https://my.surfshark.com/vpn/api/v1/server/clusters");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        SocketAddress address = new InetSocketAddress(PROXY_HOST,PROXY_PORT);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
        Authenticator.setDefault(new BasicAuthenticator(PROXY_USERNAME,PROXY_PASSWORD));
        URLConnection conn;
        try {
            conn = Objects.requireNonNull(url).openConnection(proxy);
            conn.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void key() {
//        String i ="20";
//        int a = Integer.parseInt(i);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://merchants.test.ydjia.cn/businesses-gateway/b2c/1.0/order/page/normal");
            httpPost.setEntity(new StringEntity("{\"pageSize\":10,\"currentPage\":1,\"orderState\":10,\"organizationCode\":\"-99\",\"serviceMode\":\"B2C\"}", ContentType.APPLICATION_JSON));
            SSLUtil.turnOffSslChecking();
            httpPost.setHeader("Accept","application/json, text/plain, */*");
            httpPost.setHeader("Authorization","eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0OTA3NWYxMjM4NmQ0MzRjYWNlNDVjZjU5NzkxNDFmOCIsIm5hbWUiOiI5OTk5OTlfYWRtaW4iLCJ6aC1uYW1lIjoi6LaF566hOTk5OTk5X3Rlc3QiLCJleHAiOjE2MTI3ODIzNTh9.n2LRlG7q6x2m1ghzC0ks5cIMYBjehQ9FdpbX054jaFw47UpOZJQJCAMmy0Y0lrsuy-h7KZc4QioQUZ_68xJC8QEhEetw0Vh_Ph4zZKesrBzdk-NHvI8khqUlVIut0RhHPDITnG-GtkQsGR3xYFmMoHI-qb6V3eyOVBBU6clTpdNlyhNXkLNHFrMWWs14ScLpn25Err35sYcIAaxESOKWOS6ZCTWqyWD3l2H-pViMZQP1_K_RYS7eI6VzQsnVx7YUTT1w5nuhmzjIy6caYtEKkw-xymxNR58iNIXhuuIlOhkG86kmHA2szWy_Lim11ZQOf5YVqlBSXVUgaQLNd2VliQ");
            String responseBody = httpClient.execute(httpPost,httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    System.out.println("请求失败");
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });
            JSONObject jsonObject = JSON.parseObject(responseBody);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("records");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String orderNo = jsonArray.getJSONObject(i).getString("orderNo");
                String omsOrderNo = jsonArray.getJSONObject(i).getJSONObject("orderDeliveryAddress").getString("omsOrderNo");
                System.out.println("orderno["+i+"]:"+orderNo);
                System.out.println("omsorderno["+i+"]:"+omsOrderNo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}