package com.zxs.surfshark.service.impl;

import com.zxs.surfshark.dao.SurfSharkInfoDao;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * surfshark节点信息(SurfSharkInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-12-22 21:38:49
 */
@Service("SurfSharkInfoService")
public class SurfSharkInfoServiceImpl implements SurfSharkInfoService {
    @Autowired
    private SurfSharkInfoDao surfSharkInfoDao;

    @Override
    public List<String> queryIp() {
        return this.surfSharkInfoDao.queryIp();
    }

    @Override
    public void insertBatch(@Param("entities") List<SurfSharkInfo> list) {
        this.surfSharkInfoDao.insertBatch(list);
    }

    @Override
    public void updatePing(@Param("list") List<Map<String, String>> list) {
        this.surfSharkInfoDao.updatePing(list);
    }

}