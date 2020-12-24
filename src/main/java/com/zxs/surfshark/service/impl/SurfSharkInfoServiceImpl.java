package com.zxs.surfshark.service.impl;

import com.zxs.surfshark.dao.SurfSharkInfoDao;
import com.zxs.surfshark.entity.SurfSharkInfo;
import com.zxs.surfshark.service.SurfSharkInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public SurfSharkInfo insert(SurfSharkInfo surfSharkInfo) {
        this.surfSharkInfoDao.insert(surfSharkInfo);
        return surfSharkInfo;
    }

    @Override
    public void insertBatch(@Param("entities") List<SurfSharkInfo> list) {
        this.surfSharkInfoDao.insertBatch(list);
    }
}