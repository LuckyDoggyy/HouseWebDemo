package com.house.dao;

import com.house.model.ProxyIp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProxyIpDao extends
        CrudRepository<ProxyIp, Integer>,
        PagingAndSortingRepository<ProxyIp, Integer>{

    @Override
    List<ProxyIp> findAll();

    @Override
    List<ProxyIp> findAllById(Iterable<Integer> ids);

    List<ProxyIp> findAllByIdInOrderByCreateTimeDesc(Iterable<Integer> ids);

    List<ProxyIp> findByIdBetweenOrderByCreateTimeDesc(int start, int end);

    List<ProxyIp> findDistinctPortByIdOrderByCreateTimeDesc(Iterable<Integer> ids);

}
