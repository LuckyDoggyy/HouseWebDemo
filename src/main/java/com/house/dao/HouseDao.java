package com.house.dao;

import com.house.model.House;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HouseDao
        extends CrudRepository<House, Integer>{

    @Override
    Optional<House> findById(Integer integer);

    @Override
    <S extends House> S save(S s);

    //查找1,2,3,4室的房产信息
    List<Integer> findIdByBedroom(Integer id);

/*
    //正确？
    @Modifying
    @Query("insert into house hd values(?1, ?2)")
    boolean addNewDesc(int id, String desc);
    */

    //4室以上房产信息
    List<Integer> findIdByBedroomBetween(int start, int end);

}
