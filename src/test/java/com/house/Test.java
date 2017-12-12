package com.house;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.service.ProxyIpService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {

    @Resource(name = "mapper")
    private ObjectMapper mapper;

    @Resource
    private ProxyIpService proxyIpService;

    @org.junit.Test
    public void findAllByIdOrOrderByCreateTimeDesc(){
        List<Integer> list = new LinkedList<Integer>();
        for(int i = 1000 ; i < 1999 ; i ++)
            list.add(i);

        proxyIpService.findAllByIdOrOrderByCreateTimeDesc(list);

    }

}
