package com.house.dao;

import com.house.model.Broker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrokerDao
        extends CrudRepository<Broker, Integer>{

    @Override
    Broker findOne(Integer id);

    String findPasswordByUsername(String username);

    List<Broker> findAllByIdIn(Iterable<Integer> ids);

}
