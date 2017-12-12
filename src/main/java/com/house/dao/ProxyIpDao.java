package com.house.dao;

import com.house.model.ProxyIp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProxyIpDao extends
        CrudRepository<ProxyIp, Integer>,
        PagingAndSortingRepository<ProxyIp, Integer>,
        JpaSpecificationExecutor<ProxyIp>{

    @Override
    List<ProxyIp> findAll();

    @Override
    List<ProxyIp> findAllById(Iterable<Integer> proxyIps);

    List<ProxyIp> findAllByIdOrOrderByCreateTimeDesc(Iterable<Integer> proxyIps);

    List<ProxyIp> findDistinctPortByIdOrOrderByCreateTimeDesc(Iterable<Integer> proxyIps);



}
