package com.zxs.surfshark.service;

import com.zxs.surfshark.entity.SurfSharkInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * surfshark节点信息(SurfSharkInfo)表服务接口
 *
 * @author makejava
 * @since 2020-12-22 21:38:49
 */
@Service
public interface SurfSharkInfoService {

    List<String> queryIp();

    /**
     * 批量新增数据
     *
     */
    void insertBatch(List<SurfSharkInfo> list);

    void updatePing(List<SurfSharkInfo> list);
}