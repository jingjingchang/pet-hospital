package com.chris.pethospital.service;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.Doctor;
import com.chris.pethospital.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends BaseService<Doctor> {

    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public BaseMapper<Doctor> getMapper() {
        return doctorMapper;
    }
}
