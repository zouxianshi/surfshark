package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
import com.zxs.surfshark.service.impl.SurfSharkInfoServiceImpl;
import com.zxs.surfshark.util.NetTool;
import com.zxs.surfshark.util.SSLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * surfshark节点信息(SurfSharkInfo)表控制层
 *
 * @author makejava
 * @since 2020-12-22 21:38:49
 */
@RestController
@RequestMapping("surfSharkInfo")
@Slf4j
public class SurfSharkInfoController {
    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 1080;
    /**
     * 服务对象
     */
    @Autowired
    private SurfSharkInfoService surfSharkInfoService;

    @RequestMapping(value = "/insertBatch")
    public String insertBatch() throws KeyManagementException, NoSuchAlgorithmException {
        String url = "https://my.surfshark.com/vpn/api/v1/server/clusters";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(this.httpClientFactory());
        SSLUtil.turnOffSslChecking();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        JSONArray jsonArray= JSONArray.parseArray(body);
        if (jsonArray != null) {
            JSONObject object;
            NetTool netTool = new NetTool();
            List<SurfSharkInfo> list = new ArrayList<>();
            for (int i = 0 ; i < jsonArray.size() ; i++) {
                object = jsonArray.getJSONObject(i);
                SurfSharkInfo surfSharkInfo =  add(object);
                String ip = netTool.getIp(jsonArray.getJSONObject(i).getString("connectionName"));
                surfSharkInfo.setIpaddress(ip);
                list.add(surfSharkInfo);
                System.out.print("\n"+jsonArray.getJSONObject(i).toString());
                System.out.print("\n"+list.get(i).toString());
            }
            surfSharkInfoService.insertBatch(list);
        }
        return  "responseEntity.getBody()：" + body + "<hr>" +
                "responseEntity.getStatusCode()：" + statusCode + "<hr>" +
                "responseEntity.getStatusCodeValue()：" + statusCodeValue + "<hr>" +
                "responseEntity.getHeaders()：" + headers + "<hr>";
    }

    private SurfSharkInfo add(JSONObject object){
        SurfSharkInfo surfSharkInfo = new SurfSharkInfo();
        surfSharkInfo.setConnectid(object.getString("id"));
        surfSharkInfo.setCountry(object.getString("load"));
        surfSharkInfo.setConnectionname(object.getString("connectionName"));
        surfSharkInfo.setRegion(object.getString("region"));
        surfSharkInfo.setRegioncode(object.getString("regionCode"));
        surfSharkInfo.setLocation(object.getString("location"));
        surfSharkInfo.setCountrycode(object.getString("countryCode"));
        surfSharkInfo.setLoad(object.getInteger("load"));
        surfSharkInfo.setLatitude(object.getJSONObject("coordinates").getDoubleValue("latitude"));
        surfSharkInfo.setLongitude(object.getJSONObject("coordinates").getDoubleValue("longitude"));
        surfSharkInfo.setType(object.getString("type"));
        return surfSharkInfo;
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