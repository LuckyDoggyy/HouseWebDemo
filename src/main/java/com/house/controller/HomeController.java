package com.house.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.*;
import com.house.service.BrokerService;
import com.house.service.HouseDescService;
import com.house.service.HouseInfoService;
import com.house.service.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = {"/", "/houseInfos"}, method = RequestMethod.GET)
    public String showAllHouseInfosWithSession(
            @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
            HttpSession session, Model model) {
        Page<HouseInfo> houseInfos = houseInfoService.findAll(pageNumber - 1, pageSize);
        long count = houseInfos.getTotalElements();
        long pageSum = houseInfos.getTotalPages();
        List<HouseInformation> houseInformations = houseInfoService.getHouseInformations(houseInfos);
        Broker broker = (Broker) session.getAttribute("broker");
        if (broker != null) {
            model.addAttribute("broker", broker);
        }
        model.addAttribute("houseInfos", houseInformations);
        model.addAttribute("count",count);
        model.addAttribute("pageSum",pageSum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber",pageNumber);

        return "view/houseInfos";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("broker");
        return "redirect:/houseInfos";
    }

    @RequestMapping("/selectBy")
    public String selectBy(
            @RequestParam(value = "param") String param,
            @RequestParam(value = "zone") int zone,
            Model model
    ) {
        List<HouseInformation> houseInformations = new LinkedList();
        if (param.equals("Area"))
            houseInformations = houseInfoService.findByArea(zone);
        if (param.equals("Price"))
            houseInformations = houseInfoService.findByPrice(zone);
        if (param.equals("Bedroom"))
            houseInformations = houseInfoService.findByBedroom(zone);

        model.addAttribute("houseInfos", houseInformations);
/*
        model.addAttribute("count",count);
        model.addAttribute("pageSum",pageSum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber",pageNumber);
*/
        return "/infotable";
    }

    @RequestMapping("/welcome")
    public String welcom() {
        return "view/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpSession session
    ) {
        Map<String, String> result = new HashMap<>();
        Broker broker = brokerService.findByUsername(username);
        if (broker == null) {
            result.put("status", "Login failed, there is no this user.");
        } else if (password.equals(broker.getPassword())) {
            session.setAttribute("broker", broker);
            Broker b = (Broker) session.getAttribute("broker");
            result.put("status", "success");
        } else {
            result.put("status", "Login failed, password is not right.");
        }

        return result;
    }

}
