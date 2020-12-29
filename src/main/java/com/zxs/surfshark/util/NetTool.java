package com.zxs.surfshark.util;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetTool {
    public void getDomain(String ipaddress){
        try {
            String doamin = InetAddress.getByName(ipaddress).getHostName();
//            String doamin = Address.getHostName(InetAddress.getByName(ipaddress));
            System.out.println(doamin);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getIp(String domain){
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domain);
            StringBuilder str = new StringBuilder();
            for (InetAddress address : addresses) {
                str.append(address.getHostAddress()).append(",");
            }
            System.out.println(addresses.length > 0 ? str.deleteCharAt(str.length() - 1).toString() : "");
            return addresses.length > 0 ? str.deleteCharAt(str.length() - 1).toString() : "";
        } catch (UnknownHostException uhe) {
            System.err.println("Unable to find: " + domain);
            return "解析失败";
        }
    }

    public static long responseTime (String ipAddress) throws Exception {
        int  timeOut =  3000 ;   // 超时应该在3钞以上
        long begin=System.currentTimeMillis();
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        long end=System.currentTimeMillis();
        if (status){
            return (end-begin);
        }else {
            return 0;
        }

    }
}
