package nusiss.paf.day28airbnb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nusiss.paf.day28airbnb.Model.Airbnb;
import nusiss.paf.day28airbnb.Service.AirbnbService;

@Controller
public class AirbnbController {
    
    @Autowired
    private AirbnbService airbnbSrvs;

    @PostMapping("/search")
    public String searchAirbnbListings(@RequestParam("country") String country, Model model) {
        List<Airbnb> listings = airbnbSrvs.findAirbnbByCountry(country);
        model.addAttribute("listings", listings);
        return "view1";
    }
}
