package com.house.service;

import com.house.dao.HouseDao;
import com.house.model.House;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Resource
    private HouseDao houseDao;

    public House findById(int houseId){
        return houseDao.findOne(houseId);
    }

    public List<House> findAll(){
        return houseDao.findAll();
    }
}
