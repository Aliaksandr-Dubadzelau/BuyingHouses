package by.buyinghouses.controller;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.User;
import by.buyinghouses.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/adminPanel")
public class AdminController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping
    public String getAdminPanel(
            @AuthenticationPrincipal User user,
            Model model){

        Iterable<Accommodation> accommodations = accommodationService.findAccommodations();
        model.addAttribute("user", user);
        model.addAttribute("accommodations", accommodations);

        return "adminPanel";
    }

    @PostMapping("/accept")
    public String postAccess(
            @RequestParam String accommodationName){

        accommodationService.acceptAccommodation(accommodationName);

        return "redirect:/adminPanel";
    }

    @PostMapping("/delete")
    public String postDelete(
            @RequestParam String accommodationName){

        accommodationService.deleteAccommodation(accommodationName);

        return "redirect:/adminPanel";
    }
}
