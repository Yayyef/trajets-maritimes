package equipe4.atlanticshippingmasters.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import equipe4.atlanticshippingmasters.service.UserService;



@Controller
public class UserController {
	
    @Autowired
    private UserService userService;


        @GetMapping({"/dashboard"})
        public String dashboard(Model model, Authentication authentication) {
            final String name = authentication.getName();
            model.addAttribute("username", name);
            return "dashboard";
        }

}