package equipe4.atlanticshippingmasters.controllers;


import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import equipe4.atlanticshippingmasters.computation.TravelDistanceCalculator;
import equipe4.atlanticshippingmasters.model.Journey;
import equipe4.atlanticshippingmasters.service.JourneyService;
import equipe4.atlanticshippingmasters.service.PortService;

@Controller
public class CalculatorController {
	
	@Autowired
	private PortService ps;
	@Autowired 
	private JourneyService js;
	
	@GetMapping("/calculator")
	public String getCalculator(Model model) {
		model.addAttribute("ports", ps.getAllPorts());
		return "calculator";
	}
	
	// Map<String, String> est un dictionnaire de clés et de valeurs. Les clés correspondent à th:name dans notre vue, les valeurs correspondent à th:value
	@PostMapping("/calculator")
	public String postCalculator(Model model, @RequestParam Map<String,String> allParams) {
		
		int totalDistance = computeTotalDistance(allParams);
		
		Journey journey = new Journey();
		journey.setTotalDistance(totalDistance);
		js.insertJourney(journey);
		
		return getCalculator(model);
	}

	private int computeTotalDistance(Map<String, String> allParams) {
		int result = 0;
		for (int i = 1; i < allParams.size(); i ++) {
//			System.out.println(allParams.get("step" + i));
			// Je créé à chaque passage dans la boucle, un objet Tdc qui récupère les valeurs d'une étape
			TravelDistanceCalculator tdc = new TravelDistanceCalculator(allParams.get("step" + (i)), allParams.get("step" + (i+1)));
			System.out.println("Step" + i + " distance is " + tdc.getDistance() + "km");
			result += tdc.getDistance();
		}
		TravelDistanceCalculator tdc = new TravelDistanceCalculator(allParams.get("step" + allParams.size()), allParams.get("step" + 1));
		System.out.println("Last step distance is " + tdc.getDistance() + "km");
		result += tdc.getDistance();
		
		System.out.println("TOTAL distance is " + result + "km");
		return result;
	}
}
