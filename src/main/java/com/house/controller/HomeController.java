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
import javax.servlet.http.HttpServletRequest;
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

    private static HttpSession session;

    @RequestMapping(value = {"/","/houseInfos"}, method = RequestMethod.GET)
    public String showAllHouseInfosWithSession(
            Model model
                    ) {
        List<HouseInformation> houseInfos = houseInfoService.findAll();
        for (HouseInformation houseInformation : houseInfos)
            System.out.println(houseInformation.toString());
        Broker broker = new Broker();
        try {
//            HttpSession session = sessions.get(username);
            broker = (Broker) session.getAttribute("broker");
        } catch (Exception exception) {
            System.out.println("Exception throws.");
        }
        System.out.println(broker.toString());
        if (broker.getUsername() != null && !broker.getUsername().equals(""))
            model.addAttribute("broker", broker);
        model.addAttribute("houseInfos", houseInfos);
        /*
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize",pageSize);
        */
        return "view/houseInfos";
    }

    @RequestMapping("/logout")
    public String logout() {
        session.invalidate();
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

/*    @RequestMapping("/selectBy")
    public ModelAndView selectBy(
            @RequestParam(value="param") String param,
            @RequestParam(value="zone") int zone
                ){
        ModelAndView mav = new ModelAndView();
        List<HouseInformation> houseInformations= new LinkedList();
        if(param.equals("Area"))
            houseInformations = houseInfoService.findByArea(zone);
        if(param.equals("Price"))
            houseInformations = houseInfoService.findByPrice(zone);
        if(param.equals("Bedroom"))
            houseInformations = houseInfoService.findByBedroom(zone);
        for(HouseInformation houseInformation : houseInformations)
            System.out.println(houseInformation.toString());
        mav.setViewName("view/houseInfos");
        mav.addObject("houseInfos", houseInformations);
        return mav;
    }*/

    @RequestMapping("/welcome")
    public String welcom() {
        return "view/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(
            HttpServletRequest request
    ) {
        System.out.println("request test");
        Map<String, String> result = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ":" + password);
        session = request.getSession();
        Broker broker = brokerService.findByUsername(username);
        if (broker == null) {
            result.put("status", "Login failed, there is no this user.");
        } else if (password.equals(broker.getPassword())) {
            session.setAttribute("broker", broker);
//            sessions.put(username, session);
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
