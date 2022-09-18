package equipe4.atlanticshippingmasters.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import equipe4.atlanticshippingmasters.computation.StepForge;
import equipe4.atlanticshippingmasters.model.Journey;
import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.model.Step;
import equipe4.atlanticshippingmasters.service.JourneyService;
import equipe4.atlanticshippingmasters.service.PortService;
import equipe4.atlanticshippingmasters.service.StepService;

@Controller
public class CalculatorController {

	@Autowired
	private PortService ps;
	@Autowired
	private JourneyService js;
	@Autowired
	private StepService ss;

	@GetMapping("/calculator")
	public String getCalculator(Model model, Journey journey) {
		// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARRRRRRG!
		Iterable<Port> portList = ps.getAllPorts();

		for (Port p : portList) {
			p.setCoordinates(p.getCoordinates().replace("\"", ""));
		}
		// On tranforme en json la liste pour la passer à mon javascript
		String portsJson = new Gson().toJson(ps.getAllPorts());
//		System.out.println(portsJson);
		// On enlève tous les guillemets de nos coordonnéés pour
//		portsJson.replace("\"", "");
		model.addAttribute("ports", ps.getAllPorts());
		model.addAttribute("portsJson", portsJson);
		model.addAttribute("journey", journey);
		return "calculator";
	}

	// Map<String, String> est un dictionnaire de clés et de valeurs. Les clés
	// correspondent à th:name dans notre vue, les valeurs correspondent à th:value
	@PostMapping("/calculator")
	public String postCalculator(Model model, @RequestParam Map<String, String> allParams) {

		// Création/insertion du Journey après le calcul des étapes. Dès l'insertion du son id est accessible pour les Steps dans la méthode generateSteps 
		Journey journey = new Journey();
		js.insertJourney(journey);
		generateSteps(allParams, journey);

		return getCalculator(model, journey);
	}

	// Cette méthode identifie et récupère les ports d'après les paramètres du formulaire html pour créer des objets Step qui sont ajoutés à la base de données avec l'id du Journey auquel elle appartiennent
	private int generateSteps(Map<String, String> allParams, Journey journey) {
		int result = 0;
		int idJourney = journey.getIdJourney();
		
		List<Integer> paramValues = filterCsrfToken(allParams);

		for (int i = 0; i < paramValues.size() -1; i++) {
			// À chaque passage dans la boucle je récupère les ports de départ et d'arrivée
			// d'une étape. Je créé un objet step via la StepForge. Enfin, j'insère cet
			// objet dans la base de données avec l'id du journey fourni en paramètre.
			Port departurePort = getRequestPort(paramValues.get(i));
			Port arrivalPort = getRequestPort(paramValues.get(i + 1));

			Step step = new StepForge(departurePort, arrivalPort).buildStep(idJourney, i + 1);

			ss.insertStep(step);
		}

		Port firstPort = getRequestPort(paramValues.get(0));
		Port lastPort = getRequestPort(paramValues.get(paramValues.size() - 1));
		Step lastStep = new StepForge(lastPort, firstPort).buildStep(idJourney, paramValues.size());
		
		ss.insertStep(lastStep);

		return result;
	}

	private Port getRequestPort(int id) {
		return ps.getPort(Integer.valueOf(id)).orElse(null);
	}
	
	// FILTRE POUR RETIRER LE TOKEN CSRF (Cross Site Request Forgery) Ajouté par défaut par Spring Security
	private List<Integer> filterCsrfToken(Map<String, String> allParams) {
		List<Integer> paramValues = new ArrayList<>();
		allParams.forEach((k,v) -> {
			if (k.contains("step")) {
				paramValues.add(Integer.valueOf(v));
			}
		});
		return paramValues;
	}

}
