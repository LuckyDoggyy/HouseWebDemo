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
    public boolean addNewHouse(
            int bedroom, int livroom, String buildYear,
            String community, String address){
        House house = new House();
        house.setBedroom(bedroom);
        house.setLivroom(livroom);
        house.setBuildYear(buildYear);
        house.setCommunity(community);
        house.setAddress(address);

        return houseDao.save(house).equals(null);
    }

    //添加房产信息
    public boolean addNewHouseInfo(
            int area, int floor, int totalFloor, int price,
            String description, String title
            ,House house){
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setArea(area);

        //添加代理人id
        //houseInfo.setBrokerId();



        houseInfo.setArea(area);
        houseInfo.setFloor(floor);
        houseInfo.setTotalFloor(totalFloor);
        houseInfo.setPrice(price);
        houseInfo.setTitle(title);

        //添加描述
        HouseDesc houseDesc = new HouseDesc();
        houseDesc.setDescription(description);
        houseDesc = houseDescDao.save(houseDesc);
        houseInfo.setDescId(houseDesc.getId());

        return !houseInfoDao.save(houseInfo).equals(null);

    }

    public Broker findById(int brokerId){
        return brokerDao.findOne(brokerId);
    }


}
