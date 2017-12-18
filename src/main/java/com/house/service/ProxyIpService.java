package com.house.service;

import com.house.dao.ProxyIpDao;
import com.house.model.ProxyIp;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    public List<ProxyIp> findAllByIdInOrderByCreateTimeDesc(List<Integer> list){
        return proxyIpDao.findAllByIdInOrderByCreateTimeDesc(list);
    }

    public List<ProxyIp> findByIdBetweenOrderByCreateTimeDesc(int start, int end){
        return proxyIpDao.findByIdBetweenOrderByCreateTimeDesc(start, end);
    }

    public ProxyIp findOne(int id){

        return proxyIpDao.findOne(id);

    }

    public static void main(String [] args){

        ProxyIpService service = new ProxyIpService();

        service.init();
        List<ProxyIp> result = service.findAllByIdInOrderByCreateTimeDesc(list);

        for(ProxyIp proxyIp : result)
            System.out.println(proxyIp.getId() + ":"
                    + proxyIp.getPort() + ":"
                    + proxyIp.getIpAddr() + ":"
                    + proxyIp.getCreateTime() + ":"
                    + proxyIp.getLastActive());

    }


}
