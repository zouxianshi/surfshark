package com.zxs.surfshark.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * surfshark节点信息(SurfSharkInfo)实体类
 *
 * @author makejava
 * @since 2020-12-22 21:38:48
 */
@Data
public class SurfSharkInfo implements Serializable {
    private static final long serialVersionUID = 514170435909164494L;

    private long id;
    /**
     * 连接Id
     */
    private String connectid;
    /**
     * IP地址
     */
    private String ipaddress;
    /**
     * 域名或HOST
     */
    private String connectname;
    /**
     * 大洲
     */
    private String region;
    /**
     * 国家
     */
    private String country;
    /**
     * 地区
     */
    private String location;
    /**
     * 大洲编码
     */
    private String regioncode;
    /**
     * 国家编码
     */
    private String countrycode;
    /**
     * 节点编码
     */
    private Integer load;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;

    private String type;
    /**
     * 更新时间
     */
    private Date updatetime;

}