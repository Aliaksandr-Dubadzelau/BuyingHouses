package by.buyinghouses.controller;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.User;
import by.buyinghouses.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyingAccommodation")
public class BuyingAccommodationController {

    private static final String BUYING_ACCOMMODATION = "buyingAccommodation";

    private final AccommodationService accommodationService;

    @Autowired
    public BuyingAccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public String getBuyingAccommodation(
            @AuthenticationPrincipal User user,
            Model model) {

        Iterable<Accommodation> accommodations = accommodationService.findAccommodations();
        model.addAttribute("user", user);
        model.addAttribute("accommodations", accommodations);

        return BUYING_ACCOMMODATION;
    }

    @PostMapping
    public String postBuyingAccommodation(
            @AuthenticationPrincipal User user,
            String isInternet,
            String isFurniture,
            Accommodation accommodation,
            Model model) {
        Iterable<Accommodation> accommodations = accommodationService.findAccommodations();
        accommodationService.filterAccommodation(accommodations, accommodation, isFurniture, isInternet);
        model.addAttribute("user", user);
        model.addAttribute("accommodations", accommodations);
        return BUYING_ACCOMMODATION;
    }
}
