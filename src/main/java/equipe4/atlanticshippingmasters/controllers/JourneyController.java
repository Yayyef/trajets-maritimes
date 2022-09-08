package equipe4.atlanticshippingmasters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
