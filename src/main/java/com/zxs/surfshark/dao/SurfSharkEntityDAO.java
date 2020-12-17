package com.zxs.surfshark.dao;

import com.zxs.surfshark.beans.SurfSharkEntity;
import org.apache.ibatis.session.SqlSession;

public class SurfSharkEntityDAO {
    private final SqlSession sqlSession;

    public SurfSharkEntityDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insertSurfSharkInfo(SurfSharkEntity surfSharkEntity) {
        sqlSession.insert("SurfSharkInfo.insert",surfSharkEntity);
    }
}
