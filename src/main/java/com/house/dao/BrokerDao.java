package com.house.dao;

import com.house.model.Broker;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrokerDao
        extends CrudRepository<Broker, Integer>{

    @Override
    Optional<Broker> findById(Integer id);

    String findPasswordByUsername(String username);

}
