package com.house.controller;


import com.house.model.Broker;
import com.house.model.HouseInfo;
import com.house.model.HouseInformation;
import com.house.service.BrokerService;
import com.house.service.HouseInfoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/HouseWebDemo")
public class HomeController {

    @Resource
    private BrokerService brokerService;

    @Resource
    private HouseInfoService houseInfoService;

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
        model.addAttribute("count", count);
        model.addAttribute("pageSum", pageSum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);

        return "view/houseInfos";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("broker");
        return "redirect:/houseInfos";
    }

    @RequestMapping("/selectBy")
    public String selectBy(

            @RequestParam(name = "param", required = false) String param,
            @RequestParam(name = "zone", required = false) String zone,
            @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
            Model model
    ) {
        Map<String, String> map = new HashMap<>();
        if(!(param == null || zone == null)){
            map.put("param", param.toLowerCase());
            map.put("zone", zone);
        }
        for(String key : map.keySet())
            System.out.println(key+":"+map.get(key));
        Page<HouseInfo> houseInfos = houseInfoService.findBy(map,pageNumber - 1,pageSize);
        List<HouseInfo> list = houseInfos.getContent();
        List<HouseInformation> houseInformations = houseInfoService.getHouseInformations(houseInfos);

        model.addAttribute("houseInfos", houseInformations);
        model.addAttribute("count", houseInfos.getTotalElements());
        model.addAttribute("pageSum", houseInfos.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
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
