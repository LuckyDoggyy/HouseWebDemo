package com.house.dao;

import com.house.model.House;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseDao
        extends CrudRepository<House, Integer>{

    @Override
    House findOne(Integer id);

    @Override
    House save(House house);

    //查找1,2,3,4室的房产信息
    List<Integer> findIdByBedroom(Integer bedroom);

/*
    //正确？
    @Modifying
    @Query("insert into house hd values(?1, ?2)")
    boolean addNewDesc(int id, String desc);
    */

    //4室以上房产信息
    List<Integer> findIdByBedroomBetween(int start, int end);

}
