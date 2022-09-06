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
import equipe4.atlanticshippingmasters.service.PortService;

@Controller
public class CalculatorController {
	
	@Autowired
	private PortService ps;
	
	@GetMapping("/calculator")
	public String getCalculator(Model model) {
		
//		List<Port> portList = StreamSupport.stream(ps.getAllPorts().spliterator(), false)
//			    .collect(Collectors.toList()).subList(0, 8);
		
		model.addAttribute("ports", ps.getAllPorts());
		return "calculator";
	}
	
	@PostMapping("/calculator")
	public String postCalculator(Model model, @RequestParam Map<String,String> allParams) {
		System.out.println("Parameters are " + allParams.entrySet());
		
		int totalDistance = 0;
		
		for (int i = 2; i <= allParams.size(); i ++) {
//			System.out.println(allParams.get("step" + i));
			TravelDistanceCalculator tdc = new TravelDistanceCalculator(allParams.get("step" + (i - 1)), allParams.get("step" + i));
			System.out.println("Step distance is " + tdc.getDistance() + "km");
			totalDistance += tdc.getDistance();
		}
		TravelDistanceCalculator tdc = new TravelDistanceCalculator(allParams.get("step" + allParams.size()), allParams.get("step" + 1));
		System.out.println("Last step distance is " + tdc.getDistance() + "km");
		totalDistance += tdc.getDistance();
		System.out.println("TOTAL distance is " + totalDistance + "km");
		
		return getCalculator(model);
	}
}
