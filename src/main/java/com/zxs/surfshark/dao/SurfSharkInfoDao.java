package com.zxs.surfshark.dao;

import com.zxs.surfshark.entity.SurfSharkInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * surfshark节点信息(SurfSharkInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-22 21:38:49
 */
@Repository
public interface SurfSharkInfoDao {


    /**
     * 新增数据
     *
     * @param surfSharkInfo 实例对象
     */
    void insert(SurfSharkInfo surfSharkInfo);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SurfSharkInfo> 实例对象列表
     */
    void insertBatch(@Param("entities") List<SurfSharkInfo> entities);

    List<String> queryIp();

    void updatePing(@Param("list") List<Map<String, String>> list);
}