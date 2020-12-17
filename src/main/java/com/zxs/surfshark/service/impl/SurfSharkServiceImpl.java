package com.zxs.surfshark.service.impl;

import com.zxs.surfshark.beans.SurfSharkEntity;
import com.zxs.surfshark.dao.SurfSharkEntityDAO;
import com.zxs.surfshark.service.SurfSharkService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service("surf")
@Mapper
public class SurfSharkServiceImpl implements SurfSharkService {
    @Override
    public void insertSurfshark(SurfSharkEntity surfSharkEntity) {

    }
}
