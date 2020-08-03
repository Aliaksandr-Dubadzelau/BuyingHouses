package by.buyinghouses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyingAccommodationController {

    @GetMapping("/buyingAccommodation")
    public String main() {
        return "buyingAccommodation";
    }

}
