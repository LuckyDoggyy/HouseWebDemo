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
            @RequestParam(name = "house") int houseId,
            HttpSession session
            ){
        Map<String, String> result = new HashMap<>();
        Broker broker = ((Broker)session.getAttribute("broker"));
        if(broker == null){
            result.put("status","Failed, please log in first.");
            return result;
        }

        HouseInfo houseInfo = brokerService.addNewHouseInfo(title, area,

                floor, totalFloor, price, description, houseId, broker.getId());

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

    @RequestMapping("/addHouse")
    @ResponseBody
    public Map addHouse(
            @RequestParam(name = "livroom") int livroom,
            @RequestParam(name = "bedroom") int bedroom,
            @RequestParam(name = "community") String community,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "buildYear") String buildYear,
            HttpSession session
            ){
        Map<String, String> result = new HashMap<>();
        House house = brokerService.addNewHouse(bedroom,livroom,buildYear,community,address);
        if(session.getAttribute("broker") == null){
            result.put("status","Failed, please login first");
            return result;
        }
        System.out.println(house.toString());
        if(house != null){
            result.put("status","Add new house successfully.");
        }else{
            result.put("status","Failed to add a new house.");
        }
        return result;
    }
}
