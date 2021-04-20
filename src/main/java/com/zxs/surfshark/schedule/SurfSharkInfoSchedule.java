package com.zxs.surfshark.schedule;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;


@JobHandler(value = "MyJobHandler")
@Component
public class SurfSharkInfoSchedule extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("4444");
        return SUCCESS;
    }

    public void updateResponseTime() {
    }

    public void schedule() {
    }
}

