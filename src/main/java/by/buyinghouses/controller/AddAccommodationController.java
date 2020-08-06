package by.buyinghouses.controller;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.AccommodationType;
import by.buyinghouses.domain.User;
import by.buyinghouses.service.AccommodationService;
import by.buyinghouses.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class AddAccommodationController {

    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private FileService fileService;

    @GetMapping("/addAccommodation")
    public String getAddAccommodation(Model model){

        model.addAttribute("types", AccommodationType.values());

        return "addAccommodation";
    }

    @PostMapping("/addAccommodation")
    public String postAddAccommodation(
            @AuthenticationPrincipal User user,
            boolean isInternet,
            boolean isFurniture,
            MultipartFile file,
            Accommodation accommodation, Model model
    ) throws IOException {

        accommodationService.fillAccommodation(accommodation, user, isFurniture, isInternet);

        if(file != null){
            fileService.saveImage(accommodation, file);
        }

        if (!accommodationService.addAccommodation(accommodation)) {
            model.addAttribute("message", "Accommodation with the same name already exists");
            model.addAttribute("types", AccommodationType.values());
            return "addAccommodation";
        }

        return "redirect:/buyingAccommodation";
    }

}
