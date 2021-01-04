package com.zxs.surfshark.schedule;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SurfSharkInfoScheduleTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void schedule(){
        SurfSharkInfoSchedule surfSharkInfoSchedule =new SurfSharkInfoSchedule();
        surfSharkInfoSchedule.schedule();
    }


    @Test
    void updateResponseTimeTest(){
        SurfSharkInfoSchedule surfSharkInfoSchedule =new SurfSharkInfoSchedule();
        surfSharkInfoSchedule.updateResponseTime();
    }
}