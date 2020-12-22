package com.zxs.surfshark.service;

import com.zxs.surfshark.entity.SurfSharkInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * surfshark节点信息(SurfSharkInfo)表服务接口
 *
 * @author makejava
 * @since 2020-12-22 21:38:49
 */
@Service
public interface SurfSharkInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurfSharkInfo queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SurfSharkInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param surfSharkInfo 实例对象
     * @return 实例对象
     */
    SurfSharkInfo insert(SurfSharkInfo surfSharkInfo);

    /**
     * 批量新增数据
     *
     */
    void insertBatch(List<SurfSharkInfo> list);

    /**
     * 修改数据
     *
     * @param surfSharkInfo 实例对象
     * @return 实例对象
     */
    SurfSharkInfo update(SurfSharkInfo surfSharkInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}