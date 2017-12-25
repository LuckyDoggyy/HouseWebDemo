package com.house.security;

import com.house.model.Broker;
import com.house.service.BrokerService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BrokerFilter extends AuthenticatingFilter {

    @Resource
    private BrokerService brokerService;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response){
//        String password = request.getParameter("password");
        String username = request.getParameter("uesrname");
        Broker broker = brokerService.findByUsername(username);


        BrokerToken brokerToken = new BrokerToken(broker.getId());
        brokerToken.setBroker(broker);

        return brokerToken;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, org.apache.shiro.subject.Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request,response);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception{
        return executeLogin(request, response);
    }

}
