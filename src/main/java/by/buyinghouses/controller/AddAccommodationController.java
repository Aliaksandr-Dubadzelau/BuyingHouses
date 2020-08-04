package by.buyinghouses.controller;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.AccommodationType;
import by.buyinghouses.domain.User;
import by.buyinghouses.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddAccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping("/addAccommodation")
    public String getAddAccommodation(Model model){

        model.addAttribute("types", AccommodationType.values());

        return "addAccommodation";
    }

    @PostMapping("/addAccommodation")
    public String postAddAccommodation(
            @AuthenticationPrincipal User user,
            Accommodation accommodation, Model model
    ){

        accommodation.setOwner(user);
//        accommodation.setInternet(true);
//        accommodation.setFurniture(true);

        if (!accommodationService.add(accommodation)) {
            model.addAttribute("message", "Accommodation with the same name already exists");
            return "addAccommodation";
        }

        return "redirect:buyingAccommodation";
    }

}
