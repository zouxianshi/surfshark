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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SurfSharkInfo queryById(Long id) {
        return this.surfSharkInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SurfSharkInfo> queryAllByLimit(int offset, int limit) {
        return this.surfSharkInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param surfSharkInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SurfSharkInfo insert(SurfSharkInfo surfSharkInfo) {
        this.surfSharkInfoDao.insert(surfSharkInfo);
        return surfSharkInfo;
    }

    @Override
    public void insertBatch(@Param("entities") List<SurfSharkInfo> list) {
        this.surfSharkInfoDao.insertBatch(list);
    }

    /**
     * 修改数据
     *
     * @param surfSharkInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SurfSharkInfo update(SurfSharkInfo surfSharkInfo) {
        this.surfSharkInfoDao.update(surfSharkInfo);
        return this.queryById(surfSharkInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.surfSharkInfoDao.deleteById(id) > 0;
    }
}