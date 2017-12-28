package com.house.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.Broker;
import com.house.model.House;
import com.house.model.HouseDesc;
import com.house.model.HouseInformation;
import com.house.service.BrokerService;
import com.house.service.HouseDescService;
import com.house.service.HouseInfoService;
import com.house.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = {"/","/houseInfos"}, method = RequestMethod.GET)
    public String showAllHouseInfosWithSession(HttpSession session, Model model) {
        List<HouseInformation> houseInfos = houseInfoService.findAll();
        for (HouseInformation houseInformation : houseInfos){
            System.out.println(houseInformation.toString());
        }
        System.out.println("login jsessionid : " + session.getId());
        Broker broker = (Broker)session.getAttribute("broker");
            if(broker != null){
                model.addAttribute("broker", broker);
            }
        model.addAttribute("houseInfos", houseInfos);
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
        for (HouseInformation houseInformation : houseInformations)
            System.out.println(houseInformation.toString());
        model.addAttribute("houseInfos", houseInformations);
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
            @CookieValue(name = "JSESSIONID", required = false) String jsessionid,
            HttpSession session
            ) {
        System.out.println("login jsessionid : " + jsessionid );
        Map<String, String> result = new HashMap<>();
        Broker broker = brokerService.findByUsername(username);
        if (broker == null) {
            result.put("status", "Login failed, there is no this user.");
        } else if (password.equals(broker.getPassword())) {
            session.setAttribute("broker", broker);
            Broker b = (Broker)session.getAttribute("broker");
            System.out.println(b.getId() + b.getUsername() + b.getPhone() + b.getName() + b.getPassword());
            System.out.println("\n\n\n\n\n\n\n\n\n");
            result.put("status", "success");
        } else {
            result.put("status", "Login failed, password is not right.");
        }
        System.out.println(result);

        return result;
    }


    @RequestMapping("/selectDesc")
    @ResponseBody
    public String selectDesc(@RequestParam(value = "descId") int descId
    ) throws Exception {
        HouseDesc houseDesc = houseDescService.findAllByDescId(descId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(houseDesc);
    }

    @RequestMapping("/selectBroker")
    @ResponseBody
    public String selectBroker(@RequestParam(value = "brokerId") int brokerId
    ) throws Exception {
        Broker broker = brokerService.findById(brokerId);
        return (new ObjectMapper()).writeValueAsString(broker);
    }

    @RequestMapping("/selectHouse")
    @ResponseBody
    public String selectHouse(@RequestParam(value = "houseId") int houseId
    ) throws Exception {
        House house = houseService.findById(houseId);
        System.out.println(house.toString());
        return (new ObjectMapper()).writeValueAsString(house);
    }

}
