package com.zxs.surfshark.schedule;

import com.zxs.surfshark.controller.SurfSharkInfoController;
import com.zxs.surfshark.service.SurfSharkInfoService;
import com.zxs.surfshark.util.NetTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Scheduled(cron = "0/10 * * * * *")
    @Transactional
    public void updateResponseTime(){
        List<String> ip = surfSharkInfoService.queryIp();
        List<Map<String, String>> list = new ArrayList<>();
        NetTool netTool = new NetTool();
//        for (String s : ip) {
            try {
                Map<String, String> map = new HashMap<>();
                String time = String.valueOf(netTool.responseTime(ip.get(0)));
                if (time.equals("0")){
                    time = "延迟大于3000ms";
                }
                System.out.print(ip.get(0)+":"+time+"\n");
                map.put(ip.get(0),time);
                list.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
        System.out.println(list);
        surfSharkInfoService.updatePing(list);
    }
}
