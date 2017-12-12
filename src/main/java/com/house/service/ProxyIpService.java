package com.house.service;

import com.house.dao.ProxyIpDao;
import com.house.model.ProxyIp;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProxyIpService {

    @Resource
    private ProxyIpDao proxyIpDao;

    static List<Integer> list = new LinkedList();

    @PostConstruct
    public void init(){/*
        for(int i = 1000; i < 1999; i ++)
            list.add(i);*/
    }

    public List<ProxyIp> findAllByIdOrOrderByCreateTimeDesc(List<Integer> list){
        return proxyIpDao.findAllByIdOrOrderByCreateTimeDesc(list);
    }

    public static void main(String [] args){

        ProxyIpService service = new ProxyIpService();

        service.init();
        List<ProxyIp> result = service.findAllByIdOrOrderByCreateTimeDesc(list);

        for(ProxyIp proxyIp : result)
            System.out.println(proxyIp.getId() + ":"
                    + proxyIp.getPort() + ":"
                    + proxyIp.getIpAddr() + ":"
                    + proxyIp.getCreateTime() + ":"
                    + proxyIp.getLastActive());

    }


}
