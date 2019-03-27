package com.chris.pethospital.mapper;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.Wall;

import java.util.List;

public interface WallMapper extends BaseMapper<Wall> {
    List<Wall> getListByParams();
}
