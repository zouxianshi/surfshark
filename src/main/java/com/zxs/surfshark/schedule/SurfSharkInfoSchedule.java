package com.zxs.surfshark.schedule;

import com.zxs.surfshark.controller.SurfSharkInfoController;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
import com.zxs.surfshark.service.impl.SurfSharkInfoServiceImpl;
import com.zxs.surfshark.util.NetTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.NetworkInterface;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SurfSharkInfoSchedule {

    @Autowired
    private SurfSharkInfoController surfSharkInfoController;

    @Autowired
    private SurfSharkInfoService surfSharkInfoService;

    @Scheduled(cron = "0 0 0 * * *")
    public void schedule() {
        try {
            surfSharkInfoController.insertBatch();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新访问延迟
     */
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void updateResponseTime(){
        List<String> ip = surfSharkInfoService.queryIp();
        List<SurfSharkInfo> surfSharkInfos = new ArrayList<>();
        NetTool netTool = new NetTool();
        for (String s : ip) {
            try {
                SurfSharkInfo surfSharkInfo = new SurfSharkInfo();
                surfSharkInfo.setIpaddress(s);
                String time = String.valueOf(netTool.responseTime(s));
                if (time.equals("0")){
                    time = "延迟大于3000ms";
                }
                System.out.print(s+":"+time+"\n");
                surfSharkInfo.setPing(time);
                surfSharkInfos.add(surfSharkInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        surfSharkInfoService.updatePing(surfSharkInfos);
    }
}
