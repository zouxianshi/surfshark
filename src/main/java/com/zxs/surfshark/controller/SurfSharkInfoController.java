package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
import com.zxs.surfshark.service.impl.SurfSharkInfoServiceImpl;
import com.zxs.surfshark.util.SSLUtil;
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
public class SurfSharkInfoController {
    private static final String PROXY_HOST = "127.0.0.1";
    private static final int PROXY_PORT = 1080;
    /**
     * 服务对象
     */
    @Autowired
    private SurfSharkInfoService surfSharkInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SurfSharkInfo selectOne(Long id) {
        return this.surfSharkInfoService.queryById(id);
    }


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
            List<SurfSharkInfo> list = new ArrayList<>();
            for (int i = 0 ; i < jsonArray.size() ; i++) {
                object = jsonArray.getJSONObject(i);
                SurfSharkInfo surfSharkInfo =  add(object);
                list.add(surfSharkInfo);
                System.out.print("\n"+list.get(i).getConnectionname());
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
        surfSharkInfo.setCountry(object.getString("load"));
        surfSharkInfo.setConnectionname(object.getString("ConnectionName"));
        surfSharkInfo.setRegion(object.getString("ConnectionName"));
        surfSharkInfo.setRegioncode(object.getString("regionCode"));
        surfSharkInfo.setLocation(object.getString("location"));
        surfSharkInfo.setCountrycode(object.getString("countryCode"));
        surfSharkInfo.setLoad(object.getInteger("load"));
        surfSharkInfo.setLatitude(object.getDoubleValue("latitude"));
        surfSharkInfo.setLongitude(object.getDoubleValue("longitude"));
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