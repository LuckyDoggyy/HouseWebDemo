package com.house.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.*;
import com.house.service.BrokerService;
import com.house.service.HouseDescService;
import com.house.service.HouseInfoService;
import com.house.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String search(/*
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "60") int pageSize,*/
            Model model){
        List<HouseInformation> houseInformations = houseInfoService.findAll();/*
        model.addAttribute("pageSum", houseInfos.getTotalPages());
        model.addAttribute("counts", houseInfos.getTotalElements());*/
        model.addAttribute("houseInfos", houseInformations);/*
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);*/
        return "view/houseInfos";
    }

    @RequestMapping(value = "/houseInfos", method = RequestMethod.GET)
    public String showAllHouseInfos(
            /*
                         @RequestParam(value = "pageNumber" , defaultValue = "1") int pageNumber,
                         @RequestParam(value = "pageSize" , defaultValue = "60") int pageSize,
            */
                         Model model
                         ){
        List<HouseInformation> houseInfos = houseInfoService.findAll(/*new PageRequest(pageNumber,pageSize)*/);
/*        model.addAttribute("pagesSum",houseInfos.sgetTotalPages());
        model.addAttribute("counts", houseInfos.getTotalElements());*/
        for(HouseInformation houseInformation : houseInfos)
            System.out.println(houseInformation.toString());
        model.addAttribute("houseInfos", houseInfos);
        /*
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize",pageSize);
        */
        return "view/houseInfos";
    }

    @RequestMapping("/selectBy")
    public String selectBy(
            @RequestParam(value="param") String param,
            @RequestParam(value="zone") int zone,
            Model model
            ) throws Exception{
        List<HouseInformation> houseInformations= new LinkedList();
        if(param.equals("Area")){
            houseInformations = houseInfoService.findByArea(zone);
            for(HouseInformation houseInformation : houseInformations)
                System.out.println(houseInformation.toString());
        }
        if(param.equals("Price"))
            houseInformations = houseInfoService.findByPrice(zone);
        if(param.equals("Bedroom"))
            houseInformations = houseInfoService.findByBedroom(zone);
        model.addAttribute("houseInfos", houseInformations);
        return "view/houseInfos";

    }






    @RequestMapping("/selectDesc")
    @ResponseBody
    public String selectDesc(@RequestParam(value = "descId") int descId
                    ) throws Exception{
        HouseDesc houseDesc = houseDescService.findAllByDescId(descId);
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
        System.out.println(house.toString());
        return (new ObjectMapper()).writeValueAsString(house);
    }




}
