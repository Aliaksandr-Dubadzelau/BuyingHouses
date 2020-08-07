package by.buyinghouses.controller;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.User;
import by.buyinghouses.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyingAccommodation")
public class BuyingAccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping
    public String getBuyingAccommodation(
            @AuthenticationPrincipal User user,
            Model model) {

        Iterable<Accommodation> accommodations = accommodationService.findAccommodations();
        model.addAttribute("user", user);
        model.addAttribute("accommodations", accommodations);

        return "buyingAccommodation";
    }

    @PostMapping("/buy/{id}")
    public String postBuyingAccommodation(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            Model model) {

        return "redirect:/payment/{id}";
    }
}
