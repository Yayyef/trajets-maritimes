package equipe4.atlanticshippingmasters.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.service.PortService;

@Controller
public class AdminController {
   
    @Autowired
    private PortService portService;

    @GetMapping({"admin/adminDashboard"})
    public String admin(Model model, Authentication authentication) {
    	final Iterable<Port> ports=portService.getAllPorts();
    	model.addAttribute("portList",ports);
        return "adminDashboard";
    }

    @GetMapping({"/admin/port/{id}"})
    public String ajoutePort(@PathVariable Integer id,Model model) {
    	model.addAttribute("port",portService.getPort(id).orElse(null));
		return "editport";
    	
    }
    
    @PostMapping({"/admin/port/{id}"})
    public String updatePort(@PathVariable Integer id,Model model,@Valid Port port,BindingResult result) {
    	//checking the values if the are correctly inserted or not
    	if(result.hasErrors()) {
    		return "editport";
    	}
    	else {
    		portService.insertPort(port);
    		model.addAttribute("message","You have successfully inserted port");
    	}

		return "editport";
    }


}