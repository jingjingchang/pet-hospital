package com.chris.pethospital.service;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.Pet;
import com.chris.pethospital.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PetService extends BaseService<Pet> {


    @Autowired
    PetMapper petMapper;

    @Override
    public BaseMapper<Pet> getMapper() {
        return petMapper;
    }

    public List<Pet> getListByParams(Map<String,Object> map){
        return  petMapper.getListByParams(map);
    }
}
