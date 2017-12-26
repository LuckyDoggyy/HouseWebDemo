/*
package com.house.security;

import com.house.model.Broker;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

public class BrokerAuthenticationRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws ArithmeticException{
        BrokerToken brokerToken = (BrokerToken) token;
        Broker broker = brokerToken.getBroker();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(broker, broker.getId(), BrokerAuthenticationRealm.class.getName());
        return simpleAuthenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof BrokerToken;
    }

}
*/
