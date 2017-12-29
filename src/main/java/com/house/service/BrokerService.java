package com.house.service;

import com.house.dao.BrokerDao;
import com.house.dao.HouseDao;
import com.house.dao.HouseDescDao;
import com.house.dao.HouseInfoDao;
import com.house.model.Broker;
import com.house.model.House;
import com.house.model.HouseDesc;
import com.house.model.HouseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BrokerService {

    @Resource
    private BrokerDao brokerDao;

    @Resource
    private HouseDao houseDao;

    @Resource
    private HouseInfoDao houseInfoDao;

    @Resource
    private HouseDescDao houseDescDao;

    public boolean login(String username, String password){

        return password.equals(brokerDao.findPasswordByUsername(username));

    }

    //添加房型
    public House addNewHouse(
            int bedroom, int livroom, String buildYear,
            String community, String address){
        House house = new House();
        house.setBedroom(bedroom);
        house.setLivroom(livroom);
        house.setBuildYear(buildYear);
        house.setCommunity(community);
        house.setAddress(address);

        return houseDao.save(house);
    }

    //添加房产信息
    public HouseInfo addNewHouseInfo(String title,
                                     int area, int floor, int totalFloor, int price,
                                     String description, int houseId, int brokerId){
        HouseDesc houseDesc = new HouseDesc(description);
        houseDesc = houseDescDao.save(houseDesc);
        HouseInfo houseInfo = new HouseInfo(
                title, area, price,floor, totalFloor,
                houseDesc.getId(), houseId,
                brokerId, new Date());

        return houseInfoDao.save(houseInfo);

    }

    public Broker findById(int brokerId){
        return brokerDao.findOne(brokerId);
    }

    public String findPasswordByUsername(String username){
        return brokerDao.findPasswordByUsername(username);
    }

    public Broker findByUsername(String username){
        return brokerDao.findByUsername(username);
    }

}
