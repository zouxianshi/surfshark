package com.zxs.surfshark.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxs.surfshark.beans.SurfSharkEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurfSharkEntityMapper extends BaseMapper<SurfSharkEntity> {
    int insertSurfSharkInfo (@Param("surfSharkEntityList") List<SurfSharkEntity> surfSharkEntityList);
}
