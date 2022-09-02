package equipe4.atlanticshippingmasters.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.service.PortService;

@Controller
public class CalculatorController {
	
	@Autowired
	private PortService ps;
	
	@GetMapping("/calculator")
	public String getCalculator(Model model) {
		
		Iterable<Port> portList = ps.getAllPorts();
		model.addAttribute("ports", portList);
		return "calculator";
	}
}
