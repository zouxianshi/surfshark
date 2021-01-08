package com.zxs.surfshark.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

class NetToolTest {

    @Test
    void getDomain() {
        NetTool netTool = new NetTool();
        netTool.getDomain("49.233.207.137");
    }

    @Test
    void getIp() {
        NetTool netTool = new NetTool();
        netTool.getIp("www.baidu.com");
    }

    @Test
    void getIp2() throws NoSuchAlgorithmException, KeyManagementException {
        NetTool netTool = new NetTool();
        JSONObject ip = netTool.getIp2("jp-tok.prod.surfshark.com");
        String IP = ip.get("ip").toString();
        System.out.println("解析地址为:"+IP);
    }

    @Test
    void responseTime() {
        }
}