package com.house.dao;

import com.house.model.HouseDesc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseDescDao 
        extends CrudRepository<HouseDesc, Integer> {
    
    @Override
    HouseDesc findOne(Integer id);

    List<HouseDesc> findAllByIdIn(Iterable<Integer> ids);

}
