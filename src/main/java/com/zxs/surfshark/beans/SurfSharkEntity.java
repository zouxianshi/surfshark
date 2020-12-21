package com.zxs.surfshark.beans;

import lombok.Data;

@Data
public class SurfSharkEntity {
    String id;
    String connectionName;
    String region;
    String country;
    String location;
    String regionCode;
    String countryCode;
    int load;
    double latitude;
    double longitude;
    String type;
}
