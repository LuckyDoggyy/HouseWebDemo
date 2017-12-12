package com.house.dao;

import com.house.model.House;
import com.house.model.HouseDesc;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HouseDescDao
        extends CrudRepository<HouseDesc, Integer>{


    @Override
    Optional<HouseDesc> findById(Integer integer);

    @Override
    <S extends HouseDesc> S save(S s);
}