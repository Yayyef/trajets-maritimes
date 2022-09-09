package equipe4.atlanticshippingmasters.computation;

import java.util.ArrayList;
import java.util.Map;

import equipe4.atlanticshippingmasters.model.Step;

public class JourneyCalculator {
	ArrayList<Step> steps = new ArrayList<>(); 
	
	public JourneyCalculator(ArrayList<Step> stepsList) {
		this.steps = stepsList;
	}
	
	public ArrayList<Step> populate(Map<String,String> allParams) {
		// Comment peupler ma liste? Est ce que je dois le faire ici? Je vais avoir besoin du step service...
		return new ArrayList<Step>();
	}
	
}
