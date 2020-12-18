package com.zxs.surfshark.service.impl;

import com.zxs.surfshark.beans.SurfSharkEntity;
import com.zxs.surfshark.dao.SurfSharkEntityMapper;
import com.zxs.surfshark.service.SurfSharkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurfSharkServiceImpl implements SurfSharkService {
    @Autowired
    private SurfSharkEntityMapper surfSharkEntityMapper;

    @Override
    public void insertSurfshark(SurfSharkEntity surfSharkEntity) {\
        surfSharkEntityMapper.in
    }
}
