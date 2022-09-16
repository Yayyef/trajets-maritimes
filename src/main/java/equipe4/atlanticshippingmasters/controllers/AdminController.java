package equipe4.atlanticshippingmasters.controllers;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import equipe4.atlanticshippingmasters.model.User;
import equipe4.atlanticshippingmasters.service.UserService;


@Controller
public class AdminController {
   
    @Autowired
    private UserService userService;

    @GetMapping({"/adminDashboard"})
    public String admin(Model model, Authentication authentication) {
        final List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminDashboard";
    }

    @PostMapping({"/admin/removeUser"})
    public String removeUser(@RequestParam("userId") int userId) {
        final Optional<User> user = userService.findById(userId);
        userService.removeUser(user.get().getIdUser());
        return "redirect:/admin/dashboard";
    }

}