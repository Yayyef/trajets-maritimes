package equipe4.atlanticshippingmasters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import equipe4.atlanticshippingmasters.model.Journey;
import equipe4.atlanticshippingmasters.service.JourneyService;

@Controller
public class JourneyController {

		@Autowired
		private JourneyService journeyService;
		
		
		@GetMapping("/journeys")
		public String GetView(Model model) {
			model.addAttribute("journeys", journeyService.getAllJourneys());
			return "journeys";
			
		}
		
		@GetMapping("/details/{id}")
		public String JourneyView(@PathVariable Integer id ,Model model) {
			model.addAttribute("journey",journeyService.getJourney(id).orElse(null));
			 
			return "details";
		}
		
	
}
