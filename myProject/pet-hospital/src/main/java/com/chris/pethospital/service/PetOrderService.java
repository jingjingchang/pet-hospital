package com.chris.pethospital.service;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.PetOrder;
import com.chris.pethospital.mapper.PetOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOrderService extends BaseService<PetOrder> {

    @Autowired
    PetOrderMapper petOrderMapper;

    @Override
    public BaseMapper<PetOrder> getMapper() {
        return petOrderMapper;
    }
}
