package com.house.security;

import com.house.model.Broker;
import org.apache.shiro.authc.AuthenticationToken;

public class BrokerToken implements AuthenticationToken{


    private final int code;
    private Broker broker;

    public BrokerToken(int code){
        this.code = code;
    }


    public void setBroker(Broker broker){
        this.broker = broker;
    }

    public int getCode(){
        return this.code;
    }

    public Broker getBroker() {
        return broker;
    }

    @Override
    public Object getPrincipal(){
        return this.broker;
    }

    @Override
    public Object getCredentials() {
        return this.code;
    }
}

