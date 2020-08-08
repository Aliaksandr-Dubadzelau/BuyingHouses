package by.buyinghouses.controller;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.User;
import by.buyinghouses.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping("{id}")
    public String getPayment(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            Model model
    ){

        Accommodation accommodation  = accommodationService.findAccommodation(id);
        model.addAttribute("user", user);
        model.addAttribute("accommodation",accommodation);

        return "payment";
    }

    @PostMapping("{id}")
    public String postPayment(
            @PathVariable Long id,
            @RequestParam String accommodationName
    ){

        accommodationService.deleteAccommodation(accommodationName);

        return "redirect:/buyingAccommodation";
    }

}
