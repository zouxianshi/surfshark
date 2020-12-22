package com.zxs.surfshark.controller;

import com.alibaba.fastjson.JSONArray;

import com.zxs.surfshark.service.impl.SurfSharkInfoServiceImpl;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
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
    @Resource
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


    @GetMapping(value = "/insertBatch")
    public String insertBatch(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(this.httpClientFactory());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://my.surfshark.com/vpn/api/v1/server/clusters",String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        JSONArray jsonArray= JSONArray.parseArray(body);
        System.out.print(body);
        if (jsonArray != null) {
            SurfSharkInfoService surfSharkService = new SurfSharkInfoServiceImpl();
            List<SurfSharkInfo> list = new ArrayList<>();
            for (int i = 0; i<jsonArray.size(); i++) {
                SurfSharkInfo surfSharkInfo = new SurfSharkInfo();
                surfSharkInfo.setCountry(jsonArray.getJSONObject(i).getString("country"));
                surfSharkInfo.setConnectionname(jsonArray.getJSONObject(i).getString("connectionName"));
                surfSharkInfo.setRegion(jsonArray.getJSONObject(i).getString("region"));
                surfSharkInfo.setRegioncode(jsonArray.getJSONObject(i).getString("regionCode"));
                surfSharkInfo.setLocation(jsonArray.getJSONObject(i).getString("location"));
                surfSharkInfo.setCountrycode(jsonArray.getJSONObject(i).getString("countryCode"));
                surfSharkInfo.setLoad(jsonArray.getJSONObject(i).getInteger("load"));
                surfSharkInfo.setLatitude(jsonArray.getJSONObject(i).getDoubleValue("latitude"));
                surfSharkInfo.setLongitude(jsonArray.getJSONObject(i).getDoubleValue("longitude"));
                surfSharkInfo.setType(jsonArray.getJSONObject(i).getString("type"));
                list.add(surfSharkInfo);
                System.out.print("\n"+list.get(i));
            }
            System.out.print("\n"+list.size()+"\n");
            surfSharkService.insertBatch(list);
        }  else {
            return "请求失败";
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