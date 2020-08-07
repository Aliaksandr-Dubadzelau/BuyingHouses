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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AddAccommodationController {

    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private FileService fileService;

    @GetMapping("/addAccommodation")
    public String getAddAccommodation(
            @AuthenticationPrincipal User user,
            Model model){

        model.addAttribute("user", user);
        model.addAttribute("types", AccommodationType.values());

        return "addAccommodation";
    }

    @PostMapping("/addAccommodation")
    public String postAddAccommodation(
            @AuthenticationPrincipal User user,
            boolean isInternet,
            boolean isFurniture,
            MultipartFile file,
            @Valid Accommodation accommodation,
            BindingResult bindingResult,
            Model model
    ) throws IOException {

        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);

            return "addAccommodation";
        }
        else {

            accommodationService.fillAccommodation(accommodation, user, isFurniture, isInternet);

            if (file != null) {
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

}
