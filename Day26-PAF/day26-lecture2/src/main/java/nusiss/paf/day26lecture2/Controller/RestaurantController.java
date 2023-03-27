package nusiss.paf.day26lecture2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nusiss.paf.day26lecture2.Repository.RestaurantRepository;
import nusiss.paf.day26lecture2.model.Restaurant;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @GetMapping("/foodtype")
    public String findTypeOfFood(Model model) {

        List<String> typeOfFoodList = restaurantRepo.findTypeOfFood();
        model.addAttribute("typeOfFoods", typeOfFoodList);
        return "view1";
    }

    @GetMapping("/food-details")
    public String showFoodDetails(@RequestParam("typeOfFood") String typeOfFood, Model model) {
        List<Restaurant> restaurantList = restaurantRepo.findRestaurantsByTypeOfFood(typeOfFood);
        model.addAttribute("restaurants", restaurantList);
        model.addAttribute("selectedTypeOfFood", typeOfFood);
        return "view2";
    }

}
