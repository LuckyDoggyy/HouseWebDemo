package com.house.service;

import com.house.dao.HouseDao;
import com.house.model.House;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class HouseService {

    @Resource
    private HouseDao houseDao;


}
