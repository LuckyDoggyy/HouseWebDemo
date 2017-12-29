package com.house.controller;

import com.house.dao.HouseDescDao;
import com.house.model.Broker;
import com.house.model.House;
import com.house.model.HouseDesc;
import com.house.model.HouseInfo;
import com.house.service.BrokerService;
import com.house.service.HouseInfoService;
import com.house.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BrokerController {

    @Resource
    private BrokerService brokerService;

    @Resource
    private HouseInfoService houseInfoService;

    @Resource
    private HouseDescDao houseDescDao;

    @Resource
    private HouseService houseService;

    @RequestMapping("/addHouseInfo")
    @ResponseBody
    public Map addHouseInfo(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "area") int area,
            @RequestParam(name = "price") int price,
            @RequestParam(name = "floor") int floor,
            @RequestParam(name = "totalFloor") int totalFloor,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "house") int house,
            HttpSession session
            ){
        Map<String, String> result = new HashMap<>();
        int brokerId = ((Broker)session.getAttribute("broker")).getId();
        HouseInfo houseInfo = brokerService.addNewHouseInfo(title, area,
                price,floor, totalFloor, description, house, brokerId);
        if(houseInfo != null) {
            result.put("status", "Add house information successfully.");
        }else{
            result.put("status", "Add house information failed.");
        }
        return result;
    }

    @RequestMapping("/addHouseInfoPage")
    public String addHousePage(Model model){
        List<House> houses = houseService.findAll();
        System.out.println(houses.size());
        model.addAttribute("houseOpts", houses);
        return "/view/addhouseinfos";
    }




}
