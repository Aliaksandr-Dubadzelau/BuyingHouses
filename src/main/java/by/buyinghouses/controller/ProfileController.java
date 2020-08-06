package by.buyinghouses.controller;

import by.buyinghouses.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfile(
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute( "user", user);
        return "userProfile";

    }

    @PostMapping("/profile")
    public String getProfile(){

        return "";

    }

}
