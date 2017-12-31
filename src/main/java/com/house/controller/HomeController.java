package com.house.controller;

<<<<<<< Updated upstream
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.model.Broker;
import com.house.model.House;
import com.house.model.HouseDesc;
=======
import com.house.model.Broker;
import com.house.model.HouseInfo;
>>>>>>> Stashed changes
import com.house.model.HouseInformation;
import com.house.service.BrokerService;
import com.house.service.HouseDescService;
import com.house.service.HouseInfoService;
import com.house.service.HouseService;
<<<<<<< Updated upstream
=======
import org.springframework.data.domain.Page;
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    public String showAllHouseInfosWithSession(HttpSession session, Model model) {
        List<HouseInformation> houseInfos = houseInfoService.findAll();
        Broker broker = (Broker) session.getAttribute("broker");
        if (broker != null) {
            model.addAttribute("broker", broker);
        }
<<<<<<< Updated upstream
        model.addAttribute("houseInfos", houseInfos);
=======
        model.addAttribute("houseInfos", houseInformations);
        model.addAttribute("count", count);
        model.addAttribute("pageSum", pageSum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);

>>>>>>> Stashed changes
        return "view/houseInfos";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("broker");
        return "redirect:/houseInfos";
    }

    @RequestMapping("/selectBy")
    public String selectBy(
            @RequestParam(name = "param") String param,
            @RequestParam(name = "zone") String zone,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
            Model model
    ) {
<<<<<<< Updated upstream
        List<HouseInformation> houseInformations = new LinkedList();
        if (param.equals("Area"))
            houseInformations = houseInfoService.findByArea(zone);
        if (param.equals("Price"))
            houseInformations = houseInfoService.findByPrice(zone);
        if (param.equals("Bedroom"))
            houseInformations = houseInfoService.findByBedroom(zone);
        model.addAttribute("houseInfos", houseInformations);
=======
        Map<String, String> map = new HashMap<>();
        map.put("param", param.toLowerCase());
        map.put("zone", zone);
        Page<HouseInfo> houseInfos = houseInfoService.findBy(map, pageNumber, pageSize);
        List<HouseInformation> houseInformations = houseInfoService.getHouseInformations(houseInfos);
        model.addAttribute("houseInfos", houseInformations);
        model.addAttribute("count", houseInfos.getTotalElements());
        model.addAttribute("pageSum", houseInfos.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        /*
        model.addAttribute("count", houseInfos.getTotalElements());
        model.addAttribute("pageSum", houseInfos.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        */
>>>>>>> Stashed changes
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
        return (new ObjectMapper()).writeValueAsString(house);
    }

}
