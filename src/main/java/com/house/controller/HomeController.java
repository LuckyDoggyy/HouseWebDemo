package com.house.controller;



import com.house.service.BrokerService;
import com.house.service.HouseInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class HomeController {

    @Resource
    private BrokerService brokerService;

    @Resource
    private HouseInfoService houseInfoService;

//    @RequestMapping()
//    public

}
