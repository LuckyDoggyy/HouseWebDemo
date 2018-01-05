package com.house.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.dao.BrokerDao;
import com.house.dao.HouseDao;
import com.house.dao.HouseDescDao;
import com.house.dao.HouseInfoDao;
import com.house.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.swing.text.html.HTMLDocument;
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

    private static final Logger log = LoggerFactory.getLogger(HouseInfoService.class);

    //分页显示所有房产信息
    public Page<HouseInfo> findAll(int page, int size){

        Pageable pageable = new PageRequest(page, size);
        return houseInfoDao.findAll(pageable);

    }

    public List<HouseInformation> getHouseInformations(Page<HouseInfo> page){
        return fromHouseInfosToHouseInformations(page);
    }



    public List<Integer> findByBedroom(int interval){

        List<House> houses = new LinkedList<>();

        if(interval <= 4)
            houses = houseDao.findAllByBedroom(interval);
        else
            houses = houseDao.findAllByBedroomGreaterThan(4);

        List<Integer> houseIds = new LinkedList<>();


        return houseIds;

    }

    public List<HouseInformation> fromHouseInfosToHouseInformations(Page<HouseInfo> houseInfoPages){
        List<Integer> descIds = new LinkedList<>();
        List<Integer> brokerIds = new LinkedList<>();
        List<Integer> houseIds = new LinkedList<>();
        List<HouseInfo> houseInfos = houseInfoPages.getContent();
        for(HouseInfo houseInfo : houseInfos){
            descIds.add(houseInfo.getDescId());
            brokerIds.add(houseInfo.getBrokerId());
            houseIds.add(houseInfo.getHouseId());
            log.info(houseInfo.toString());
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
            log.info(houseInformation.toString());
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

    public Page<HouseInfo> findBy(Map<String,String> map, int pageNumber, int pageSize){

        if(map.size() == 0){
            return houseInfoDao.findAll(new PageRequest(pageNumber,pageSize));
        }
        Sort.Order [] orders = new Sort.Order[2];
        orders[0] = new Sort.Order(Sort.Direction.DESC,"pubTime");
        orders[1] = new Sort.Order(Sort.Direction.ASC, "brokerId");
        Pageable pageable = new PageRequest(pageNumber,pageSize, new Sort(orders));

        Specification<HouseInfo> spec = this.buildPageCondition(map);
        return houseInfoDao.findAll(spec, pageable);
    }

    private Specification<HouseInfo> buildPageCondition(Map<String, String> map){
        return new Specification<HouseInfo>() {
            @Override
            public Predicate toPredicate(Root<HouseInfo> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {

                    Predicate predicate = null;
                    List<Predicate> list = new ArrayList<>();
                    if(map.get("param").equals("price")){
                        Path<Integer> expression = root.get("price");
                        if(map.get("zone").equals("1")){
                            predicate = /*list.add(*/cb.between(expression, 0, 99);
                        }else if(map.get("zone").equals("2")){
                            predicate = /*list.add(*/cb.between(expression, 100,149);
                        }else {
                            predicate = /*list.add(*/cb.greaterThan(expression, 149);
                        }
                    }else if(map.get("param").equals("area")){
                        Path<Integer> expression = root.get("area");
                        if(map.get("zone").equals("1")){
                            predicate = /* list.add(*/cb.between(expression, 0, 49);
                        }else if(map.get("zone").equals("2")){
                            predicate = /* list.add(*/cb.between(expression, 50, 99);
                        }else {
                            predicate = /*list.add(*/cb.greaterThan(expression, 99);

                        }
                    }else{
                        Path<Integer> expression = root.get("houseId");
                        switch (map.get("zone")){
                            case "1" : {

                                predicate = /* list.add(*/expression.in(findByBedroom(1));
                                break;
                            }case "2" : {
                                predicate = /* list.add(*/expression.in(findByBedroom(2));
                                break;
                            }case "3" : {
                                predicate = /* list.add(*/expression.in(findByBedroom(3));
                                break;
                            }case "4" : {
                                predicate = /* list.add(*/expression.in(findByBedroom(4));
                                break;
                            }case "5" : {
                                predicate = /* list.add(*/expression.in(findByBedroom(5));
                                break;
                            }
                        }
                    }

//                    Predicate [] predicates = list.toArray(new Predicate[list.size()]);
                    query.where(predicate)/*.orderBy(cb.desc(root.get("pubTime"))).orderBy(cb.asc(root.get("id")))*/;

                return null;
            }
        };

    }


    /*    public String fromListToJson(Iterable<HouseInfo> list) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String str = "{\"houseInfos\":[";
        for(HouseInfo houseInfo : list)
            str += objectMapper.writeValueAsString(houseInfo) + ",";
        str = str.substring(0, str.length()-1) + "]}";

        return str;
    }*/

    /*//根据价格区间挑选,价格区间(1左开区间， 2闭区间，3右开区间)
    public List<HouseInformation> findByPrice(int interval){

        List<HouseInfo> houseInfos = new LinkedList<>();

        switch(interval){
            case 1:{
                houseInfos = houseInfoDao.findAllByPriceBetween(0, 99);
                for(HouseInfo houseInfo : houseInfos)
                    System.out.println(houseInfo.toString());
                break;
            }
            case 2:{
                houseInfos = houseInfoDao.findAllByPriceBetween(99, 149);
                for(HouseInfo houseInfo : houseInfos)
                    System.out.println(houseInfo.toString());
                break;
            }
            case 3:{
                houseInfos = houseInfoDao.findAllByPriceGreaterThan(149);
                for(HouseInfo houseInfo : houseInfos)
                    System.out.println(houseInfo.toString());
                break;
            }
        }

        return fromHouseInfosToHouseInformations(houseInfos);
    }*/

    /*//根据面积区间挑选，面积区间(1左开区间， 2闭区间，3右开区间)
    public List<HouseInformation> findByArea(int interval){

        List<HouseInfo> houseInfos = new LinkedList<>();

        switch(interval){
            case 1: {
                houseInfos = houseInfoDao.findAllByAreaBetween(0, 50);
                for(HouseInfo houseInfo : houseInfos)
                    System.out.println(houseInfo.toString());
                break;
            }
            case 2:{
                houseInfos = houseInfoDao.findAllByAreaBetween(50,99);
                for(HouseInfo houseInfo : houseInfos)
                    System.out.println(houseInfo.toString());
                break;
            }
            case 3:{
                houseInfos = houseInfoDao.findAllByAreaGreaterThan(99);
                for(HouseInfo houseInfo : houseInfos)
                    System.out.println(houseInfo.toString());
                break;
            }
        }

        return fromHouseInfosToHouseInformations(houseInfos);

    }*/

}
