package by.buyinghouses.controller;

import by.buyinghouses.domain.User;
import by.buyinghouses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@Valid User user,
                                   @RequestParam String repeatedPassword,
                                   BindingResult bindingResult,
                                   Model model) {

        boolean isRepeatedPasswordEmpty = StringUtils.isEmpty(repeatedPassword);

        if(isRepeatedPasswordEmpty){
            model.addAttribute("repeatedPassword", "Please fill field repeated password");
            return "registration";
        }

        if(!user.getPassword().equals(repeatedPassword)){
            model.addAttribute("repeatedPassword", "Passwords are different");
            return "registration";
        }

        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("userName", "User exist");
            return "registration";
        }

        return "redirect:/login";
    }

}

