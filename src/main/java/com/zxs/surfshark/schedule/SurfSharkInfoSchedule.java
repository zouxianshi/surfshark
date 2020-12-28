package com.zxs.surfshark.schedule;

import com.zxs.surfshark.controller.SurfSharkInfoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class SurfSharkInfoSchedule {

    @Autowired
    private SurfSharkInfoController surfSharkInfoController;

    @Scheduled(cron = "0 0 0 * * ? *")
    public void schedule() {
        try {
            surfSharkInfoController.insertBatch();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void responseTime(){

    }
}
