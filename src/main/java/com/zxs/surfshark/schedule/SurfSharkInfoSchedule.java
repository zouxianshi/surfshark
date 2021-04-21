package com.zxs.surfshark.schedule;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.springframework.stereotype.Component;

@Component
public class SurfSharkInfoSchedule extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) {
        System.out.println("4444");
        return SUCCESS;
    }
}

