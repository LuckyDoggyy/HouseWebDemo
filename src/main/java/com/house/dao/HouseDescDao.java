package com.house.dao;

import com.house.model.House;
import com.house.model.HouseDesc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseDescDao
        extends CrudRepository<HouseDesc, Integer>{


    @Override
    HouseDesc findOne(Integer id);


//    @Override
//    HouseDesc save(HouseDesc houseDesc);
}
