package by.buyinghouses.controller;

import by.buyinghouses.domain.User;
import by.buyinghouses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String signIn() {
        return "signUp";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated.");
        } else {
            model.addAttribute("message", "Activation code is not found.");
        }

        return "signIn";
    }

    @PostMapping("/signUp")
    public String postSignUp(User user, Model model) {

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exist");
            return "signUp";
        }

        return "redirect:/signIn";
    }

}

