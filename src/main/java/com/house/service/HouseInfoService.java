package com.house.service;

import com.house.dao.BrokerDao;
import com.house.dao.HouseDao;
import com.house.dao.HouseDescDao;
import com.house.dao.HouseInfoDao;
import com.house.model.HouseDesc;
import com.house.model.HouseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

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

    //根据价格区间挑选,价格区间(1左开区间， 2闭区间，3右开区间)
    public List<HouseInfo> findByArea(int interval){

        switch(interval){
            case  1 :
                return houseInfoDao.findAllByAreaBetween(0, 100);
            case 2:
                return houseInfoDao.findAllByAreaBetween(100, 150);
            case 3:
                return houseInfoDao.findAllByAreaBetween(150, Integer.MAX_VALUE);
        }

        return null;

    }

    //根据面积区间挑选，面积区间(1左开区间， 2闭区间，3右开区间)
    List<HouseInfo> findByPrice(int interval){

        switch(interval){
            case 1:
                return houseInfoDao.findAllByPriceBetween(0, 50);
            case 2:
                return houseInfoDao.findAllByPriceBetween(50, 100);
            case 3:
                return houseInfoDao.findAllByPriceBetween(100, Integer.MAX_VALUE);
        }

        return null;
    }

    //根据卧室数量选择
    public List<HouseInfo> find(int bedroom){

        List<Integer> houseIds = new LinkedList<>();
        if(bedroom < 5)
            houseIds = houseDao.findIdByBedroom(bedroom);
        else
            houseIds = houseDao.findIdByBedroomBetween(bedroom, Integer.MAX_VALUE);

        return houseInfoDao.findAllByHouseIdIn(houseIds);

    }

    //根据属性排序
    //??对已获得的Pageable对象排序
    public Page<HouseInfo> showBySort(Sort sort){

        return houseInfoDao.findAll(sort);

    }

}
