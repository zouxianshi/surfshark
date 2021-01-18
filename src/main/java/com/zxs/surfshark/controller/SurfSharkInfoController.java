package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
import com.zxs.surfshark.util.BasicAuthenticator;
import com.zxs.surfshark.util.HttpClientFactory;
import com.zxs.surfshark.util.NetTool;
import com.zxs.surfshark.util.SSLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.*;
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
@Slf4j
@RestController
@RequestMapping("surfSharkInfo")
public class SurfSharkInfoController {
    /**
     * 服务对象
     */
    @Autowired
    private SurfSharkInfoService surfSharkInfoService;

    @RequestMapping(value = "/insertBatch")
    public String insertBatch() throws KeyManagementException, NoSuchAlgorithmException {
        String url ="https://my.surfshark.com/vpn/api/v1/server/clusters";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpClientFactory().httpClientFactory());
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
                try{
                    JSONObject ip = netTool.getIp2(jsonArray.getJSONObject(i).getString("connectionName"));
                    String IP = ip.get("ip").toString();
                    surfSharkInfo.setIpaddress(IP);
                    list.add(surfSharkInfo);
                    log.info("\n"+jsonArray.getJSONObject(i).toString());
                }catch (Exception e){
                    String IP = "域名解析超时";
                    surfSharkInfo.setIpaddress(IP);
                    list.add(surfSharkInfo);
                    log.info("\n"+jsonArray.getJSONObject(i).toString());
                }
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
        surfSharkInfo.setConnectname(object.getString("connectionName"));
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



}