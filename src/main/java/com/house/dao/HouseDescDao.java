package com.house.dao;

import com.house.model.House;
import com.house.model.HouseDesc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseDescDao
        extends CrudRepository<HouseDesc, Integer>{


    @Override
    HouseDesc findOne(Integer id);

    List<HouseDesc> findAllByIdIn(Iterable<Integer> ids);


//    @Override
//    HouseDesc save(HouseDesc houseDesc);
}
