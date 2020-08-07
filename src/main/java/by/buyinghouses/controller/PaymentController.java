package by.buyinghouses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("{id}")
    public String getPayment(
            @PathVariable Long id
    ){
        return "payment";
    }

    @PostMapping
    public String postPayment(){

        return "redirect:/buyingAccommodation";
    }

}
