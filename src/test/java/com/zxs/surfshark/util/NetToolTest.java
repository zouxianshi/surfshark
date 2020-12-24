package com.zxs.surfshark.util;

import org.junit.jupiter.api.Test;
import org.xbill.DNS.Address;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
}