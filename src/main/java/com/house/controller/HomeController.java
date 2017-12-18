package com.house.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.Broker;
import com.house.model.House;
import com.house.model.HouseDesc;
import com.house.model.HouseInfo;
import com.house.service.BrokerService;
import com.house.service.HouseDescService;
import com.house.service.HouseInfoService;
import com.house.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Resource
    private BrokerService brokerService;

    @Resource
    private HouseInfoService houseInfoService;

    @Resource
    private HouseDescService houseDescService;

    @Resource
    private HouseService houseService;

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public void search(/*
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "60") int pageSize,*/
            Model model){
        List<HouseInfo> houseInfos = houseInfoService.findAll();/*
        model.addAttribute("pageSum", houseInfos.getTotalPages());
        model.addAttribute("counts", houseInfos.getTotalElements());*/
        model.addAttribute("houseInfos", houseInfos);/*
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);*/
        return ;
    }

    @RequestMapping(value = "/houseInfos", method = RequestMethod.GET)
    public String showAllHouseInfos(
            /*
                         @RequestParam(value = "pageNumber" , defaultValue = "1") int pageNumber,
                         @RequestParam(value = "pageSize" , defaultValue = "60") int pageSize,
            */
                         Model model
                         ){
        List<HouseInfo> houseInfos = houseInfoService.findAll(/*new PageRequest(pageNumber,pageSize)*/);
/*        model.addAttribute("pagesSum",houseInfos.getTotalPages());
        model.addAttribute("counts", houseInfos.getTotalElements());*/
        for(HouseInfo houseInfo : houseInfos)
            System.out.println(houseInfo.toString());
        model.addAttribute("houseInfos", houseInfos);
        /*
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize",pageSize);
        */
        return "view/houseInfos";
    }


    @RequestMapping("/selectDesc")
    @ResponseBody
    public String selectDesc(@RequestParam(value = "descId") int descId
                    ) throws Exception{
        HouseDesc houseDesc = houseDescService.findByDescId(descId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(houseDesc);
    }

    @RequestMapping("/selectBroker")
    @ResponseBody
    public String selectBroker(@RequestParam(value = "brokerId") int brokerId
                    ) throws Exception{
        Broker broker = brokerService.findById(brokerId);
        return (new ObjectMapper()).writeValueAsString(broker);
    }

    @RequestMapping("/selectHouse")
    @ResponseBody
    public String selectHouse(@RequestParam(value = "houseId") int houseId
                    ) throws Exception{
        House house = houseService.findById(houseId);
        return (new ObjectMapper()).writeValueAsString(house);
    }




}
