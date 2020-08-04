package by.buyinghouses.controller;

import by.buyinghouses.domain.User;
import by.buyinghouses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user, Model model) {

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exist");
            return "registration";
        }

        return "redirect:login";
    }

}

