package com.zxs.surfshark.dao;

import com.zxs.surfshark.entity.SurfSharkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * surfshark节点信息(SurfSharkInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-22 21:38:49
 */

public interface SurfSharkInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurfSharkInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SurfSharkInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param surfSharkInfo 实例对象
     * @return 对象列表
     */
    List<SurfSharkInfo> queryAll(SurfSharkInfo surfSharkInfo);

    /**
     * 新增数据
     *
     * @param surfSharkInfo 实例对象
     */
    void insert(SurfSharkInfo surfSharkInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SurfSharkInfo> 实例对象列表
     */
    void insertBatch(@Param("entities") List<SurfSharkInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SurfSharkInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SurfSharkInfo> entities);

    /**
     * 修改数据
     *
     * @param surfSharkInfo 实例对象
     * @return 影响行数
     */
    int update(SurfSharkInfo surfSharkInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


}