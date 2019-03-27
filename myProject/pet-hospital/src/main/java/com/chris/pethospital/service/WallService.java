package com.chris.pethospital.service;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.Wall;
import com.chris.pethospital.mapper.WallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.WindowAdapter;
import java.util.List;

@Service
public class WallService extends BaseService<Wall> {

    @Autowired
    WallMapper wallMapper;

    @Override
    public BaseMapper<Wall> getMapper() {
        return wallMapper;
    }

    public List<Wall> getListByParams(){
        return  wallMapper.getListByParams();
    }
}
