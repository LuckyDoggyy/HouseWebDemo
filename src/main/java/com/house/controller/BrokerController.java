package com.house.controller;

import com.house.service.BrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class BrokerController {

    @Resource
    private BrokerService brokerService;

//    @RequestMapping("/")
//    public String showHouseInfos(Model model,
//           @RequestParam(defaultValue = "1") int pageNumber,
//           @RequestParam(defaultValue = "60") int pageSize
//           @ModelAttribute(name = "")){
//
//
//    }

}
