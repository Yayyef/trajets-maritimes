package equipe4.atlanticshippingmasters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import equipe4.atlanticshippingmasters.service.StepService;

@Controller
public class TestRelationsTables {
	
	@Autowired
	private StepService stepService;
	
	@GetMapping("/testRelationsTables")
	public String GetView(Model model) {
		model.addAttribute("steps", stepService.getAllSteps());
		return "testRelationsTables";
	}
	
//	@GetMapping("/testRelationsTables/{id}")
//	public String portView(@PathVariable Integer id, Model model) {
//		
//		// Chaîner la méthode orElse permet de renvoyer comme variable un Port et non un Optional<Port>
//		model.addAttribute("port", ps.getPort(id).orElse(null));
//		return "testRelationsTables";
//	}
}
