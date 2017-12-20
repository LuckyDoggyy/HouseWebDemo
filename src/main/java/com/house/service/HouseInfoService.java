package com.house.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.dao.BrokerDao;
import com.house.dao.HouseDao;
import com.house.dao.HouseDescDao;
import com.house.dao.HouseInfoDao;
import com.house.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class HouseInfoService {

    @Resource
    private HouseDescDao houseDescDao;

    @Resource
    private BrokerDao brokerDao;

    @Resource
    private HouseDao houseDao;

    @Resource
    private HouseInfoDao houseInfoDao;


    //分页显示所有房产信息
    public Page<HouseInfo> findAll(Pageable pageable){

        return houseInfoDao.findAll(pageable);

    }


    public List<HouseInformation> findAll(){
        return fromHouseInfosToHouseInformations(houseInfoDao.findAll());
    }

    //根据面积区间挑选，面积区间(1左开区间， 2闭区间，3右开区间)
    public List<HouseInformation> findByArea(int interval){

        List<HouseInfo> houseInfos = new LinkedList<>();

        switch(interval){
            case  1 :
                houseInfos = houseInfoDao.findAllByAreaBetween(0, 50);
            case 2:
                houseInfos = houseInfoDao.findAllByAreaBetween(50,99);
            case 3:
                houseInfos = houseInfoDao.findAllByAreaGreaterThan(99);
        }

        return fromHouseInfosToHouseInformations(houseInfos);

    }

    //根据价格区间挑选,价格区间(1左开区间， 2闭区间，3右开区间)
    public List<HouseInformation> findByPrice(int interval){

        List<HouseInfo> houseInfos = new LinkedList<>();

        switch(interval){
            case 1:
                houseInfos = houseInfoDao.findAllByPriceBetween(0, 99);
            case 2:
                houseInfos = houseInfoDao.findAllByPriceBetween(99, 149);
            case 3:
                houseInfos = houseInfoDao.findAllByPriceGreaterThan(149);
        }

        return fromHouseInfosToHouseInformations(houseInfos);
    }

    public List<HouseInformation> findByBedroom(int interval){

        List<House> houses = new LinkedList<>();

        if(interval <= 4)
            houses = houseDao.findAllByBedroom(interval);
        else
            houses = houseDao.findAllByBedroomGreaterThan(4);

        List<Integer> houseIds = new LinkedList<>();

        for(House house : houses)
            houseIds.add(house.getId());

        List<HouseInfo> houseInfos = houseInfoDao.findAllByHouseIdIn(houseIds);

        return fromHouseInfosToHouseInformations(houseInfos);

    }


    //??对已获得的Pageable对象排序
    public Page<HouseInfo> showBySort(Sort sort){

        return houseInfoDao.findAll(sort);

    }

    /*    public String fromListToJson(Iterable<HouseInfo> list) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String str = "{\"houseInfos\":[";
        for(HouseInfo houseInfo : list)
            str += objectMapper.writeValueAsString(houseInfo) + ",";
        str = str.substring(0, str.length()-1) + "]}";

        return str;
    }*/


    public List<HouseInformation> fromHouseInfosToHouseInformations(List<HouseInfo> houseInfos){
        List<Integer> descIds = new LinkedList<>();
        List<Integer> brokerIds = new LinkedList<>();
        List<Integer> houseIds = new LinkedList<>();
        for(HouseInfo houseInfo : houseInfos){
            descIds.add(houseInfo.getDescId());
            brokerIds.add(houseInfo.getBrokerId());
            houseIds.add(houseInfo.getHouseId());
        }
        Map<Integer, House> houseMap = new HashMap<>();
        Map<Integer, HouseDesc> descMap = new HashMap<>();
        Map<Integer, Broker> brokerMap = new HashMap<>();
        List<House> houses = houseDao.findAllByIdIn(houseIds);
        List<HouseDesc> houseDescs = houseDescDao.findAllByIdIn(descIds);
        List<Broker> brokers = brokerDao.findAllByIdIn(brokerIds);
        for(House house : houses)
            houseMap.put(house.getId(), house);
        for(HouseDesc houseDesc : houseDescs)
            descMap.put(houseDesc.getId(), houseDesc);
        for(Broker broker : brokers)
            brokerMap.put(broker.getId(), broker);

        List<HouseInformation> result = new LinkedList<>();
        for(HouseInfo houseInfo : houseInfos){
            House house = houseMap.get(houseInfo.getHouseId());
            HouseDesc houseDesc = descMap.get(houseInfo.getDescId());
            Broker broker = brokerMap.get(houseInfo.getBrokerId());
            HouseInformation houseInformation = getHouseInformation(houseInfo, broker, houseDesc, house);
            result.add(houseInformation);
        }
        return result;
    }

    public HouseInformation getHouseInformation(HouseInfo houseInfo,
                                                Broker broker, HouseDesc houseDesc, House house){
        HouseInformation result = new HouseInformation(houseInfo.getId(),
                houseInfo.getTitle(),houseInfo.getArea(),houseInfo.getPrice(),
                houseInfo.getFloor(),houseInfo.getTotalFloor(),houseDesc.getDescription(),
                house.getBedroom(),house.getLivroom(),house.getCommunity(),
                house.getAddress(),house.getBuildYear(),broker.getName(),
                broker.getPhone(),houseInfo.getPubTime());
        return result;
    }

}
