package equipe4.atlanticshippingmasters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import equipe4.atlanticshippingmasters.model.Step;
import equipe4.atlanticshippingmasters.service.StepService;

@Controller
public class StepController {
	
	@Autowired
	private StepService stepService;
	
	@GetMapping("/steps")
	public String GetView(Model model) {
		model.addAttribute("steps", stepService.getAllSteps());
		return "steps";
	}
	
	
	/*@GetMapping("/steps/{id}")
	public String VueStep(@PathVariable Integer id, Model model) {
		model.addAttribute("steps", stepService.getStep(id));
		return "steps";*/

		//List <Step> st = stepService.getStep(id)
		//model.addAttribute("steps", st );
		//return "steps";
		
	

	
}
