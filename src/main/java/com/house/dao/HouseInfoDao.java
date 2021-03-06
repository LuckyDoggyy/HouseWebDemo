package com.house.dao;


import com.house.model.House;
import com.house.model.HouseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.*;


@Repository
public interface HouseInfoDao extends
        CrudRepository<HouseInfo, Integer> ,
        PagingAndSortingRepository<HouseInfo, Integer>,
        JpaSpecificationExecutor<HouseInfo> {

    //分页显示
    @Override
    Page<HouseInfo> findAll(Pageable pageable);

    @Override
    Page<HouseInfo> findAll(Specification<HouseInfo> specification, Pageable pageable);


    /*

    //面积区间
    //@Query("from houseinfo as hi where hi.area between 1? and 2?")
    List<HouseInfo> findAllByAreaBetween(int start, int end);

    //价格区间
    //@Query("from houseinfo where price between 2? and 3?")
    List<HouseInfo> findAllByPriceBetween(int start, int end);

    List<HouseInfo> findAllByPriceGreaterThan(int start);

    List<HouseInfo> findAllByAreaGreaterThan(int start);

    //根据卧室数量获得houseId，再根据houseId获得房产信息
    List<HouseInfo> findAllByHouseIdIn(Iterable<Integer> iterable);
    */

}

