package com.house.service;

import com.house.dao.HouseDescDao;
import com.house.model.HouseDesc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HouseDescService {

    @Resource
    private HouseDescDao houseDescDao;

    public HouseDesc findByDescId(int descId){
        return houseDescDao.findOne(descId);
    }



}
