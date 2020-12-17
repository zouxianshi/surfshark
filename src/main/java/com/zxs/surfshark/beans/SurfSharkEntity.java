package com.zxs.surfshark.beans;

import com.alibaba.druid.support.monitor.annotation.MTable;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@EntityScan
@MTable(name = "surfshark_info")
public class SurfSharkEntity {
    String connectionName;
    String region;
    String country;
    String location;
    String regionCode;
    String countryCode;
    int loads;
    double latitude;
    double longitude;
    String type;
}
