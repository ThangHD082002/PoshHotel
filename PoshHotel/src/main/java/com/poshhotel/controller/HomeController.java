package com.poshhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "user/home";
    }

    @GetMapping("/about")
    public String about() {
        return "user/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user/contact";
    }

    @GetMapping("/booking")
    public String booking() {
        return "user/booking";
    }

//    @PostMapping("/booking/add")
//    public String addBooking(@ModelAttribute BookingDTO bookingDTO) {
//        return
//    }

}
