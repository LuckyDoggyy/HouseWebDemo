package com.house;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.ProxyIp;
import com.house.service.ProxyIpService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {

    @Resource
    private ProxyIpService proxyIpService;

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

}
