package com.house;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.controller.HomeController;
import com.house.model.Broker;
import com.house.model.HouseInfo;
import com.house.model.HouseInformation;
import com.house.model.ProxyIp;
import com.house.service.BrokerService;
import com.house.service.HouseInfoService;
import com.house.service.ProxyIpService;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {

    @Resource
    private ProxyIpService proxyIpService;

    @Resource
    private HouseInfoService houseInfoService;

    @Resource
    private BrokerService brokerService;


    @org.junit.Test
    public void findAllByIdOrderByCreateTimeDesc(){
        List<Integer> list = new LinkedList<>();
        for(int i = 1000 ; i < 1999 ; i ++)
            list.add(i);

        List<ProxyIp> result = proxyIpService.findAllByIdInOrderByCreateTimeDesc(list);

        for(ProxyIp proxyIp : result)
            System.out.println(proxyIp.getId() + " : " + proxyIp.getCreateTime());

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");

    }

    @org.junit.Test
    public void findByIdBetweenOrderByCreateTimeDesc(){

        List<ProxyIp> result = proxyIpService.findByIdBetweenOrderByCreateTimeDesc(300,303);

        for(ProxyIp proxyIp : result)
            System.out.println(proxyIp.getId() + " : " + proxyIp.getCreateTime());

    }

    @org.junit.Test
    public void findOne(){

        ProxyIp proxyIp = proxyIpService.findOne(1000);


        ProxyIp result = proxyIp;
        System.out.println(result.getId() + " : " + result.getCreateTime());
    }

    @org.junit.Test
    public void findByUsername(){
        Broker broker = brokerService.findByUsername("xxy");
        System.out.println(broker.toString());
    }

    @org.junit.Test
    public void findByPage(){

        Map<String, String> map = new HashMap<>();
        map.put("param","bedroom");
        map.put("zone","1");
        Page<HouseInfo> houseInfoPage = houseInfoService.findBy(map,0,5);
        List<HouseInfo> houseInfos = houseInfoPage.getContent();
        for(HouseInfo houseInfo : houseInfos)
            System.out.println(houseInfo.toString());

    }

    /*@org.junit.Test
    public void findByAreaBetween() throws Exception{
        List<HouseInformation> result = houseInfoService.findByArea(2);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = "{\"houseInfos\":[";
        for(HouseInformation houseInformation : result)
            str += objectMapper.writeValueAsString(houseInformation) + ",";
        str = str.substring(0, str.length()-1) + "]}";
        System.out.println(str);
    }*/

    @org.junit.Test
    public void findByPrice(){
        Map<String, String> map = new HashMap<>();
        map.put("param", "price");
        map.put("zone","1");
        for(int pageNumber = 0 ; pageNumber < 3 ; pageNumber ++){
        Page<HouseInfo> houseInfos = houseInfoService.findBy(map,pageNumber,5);
        List<HouseInfo> list = houseInfos.getContent();
        for(HouseInfo houseInfo : houseInfos)
            System.out.println(houseInfo.toString());
        System.out.println("----------------------");
        }

    }


}
