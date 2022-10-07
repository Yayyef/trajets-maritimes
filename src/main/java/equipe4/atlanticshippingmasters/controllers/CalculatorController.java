package equipe4.atlanticshippingmasters.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import equipe4.atlanticshippingmasters.computation.StepForge;
import equipe4.atlanticshippingmasters.model.Journey;
import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.model.Step;
import equipe4.atlanticshippingmasters.service.JourneyService;
import equipe4.atlanticshippingmasters.service.PortService;
import equipe4.atlanticshippingmasters.service.StepService;
import equipe4.atlanticshippingmasters.tools.ToolBox;

@Controller
public class CalculatorController {

	@Autowired
	private PortService ps;
	@Autowired
	private JourneyService js;
	@Autowired
	private StepService ss;

	private ToolBox tools = new ToolBox();

	@GetMapping("/calculator")
	public String getCalculator(Model model, Journey journey) {
		Iterable<Port> portList = ps.getAllPorts();

		for (Port p : portList) {
			p.setCoordinates(p.getCoordinates().replace("\"", ""));
		}
		// On tranforme en json la liste pour la passer au javascript
		String portsJson = new Gson().toJson(portList);

		model.addAttribute("portsTools", tools.shortenPortList(portList));
		model.addAttribute("ports", portList);
		model.addAttribute("portsJson", portsJson);
		model.addAttribute("journey", journey);
		return "calculator";
	}

	// Map<String, String> est un dictionnaire de clés et de valeurs. Les clés
	// correspondent à th:name dans notre vue, les valeurs correspondent à th:value
	@PostMapping("/calculator")
	@Transactional
	public String postCalculator(Model model, @RequestParam Map<String, String> allParams) {

		// Création/insertion du Journey après le calcul des étapes. Dès l'insertion du
		// son id est accessible pour les Steps dans la méthode generateSteps
		Journey journey = new Journey();
		
		js.insertJourney(journey);
		generateSteps(allParams, journey);

		return getCalculator(model, journey);
	}

	// Cette méthode identifie et récupère les ports d'après les paramètres du formulaire html pour créer des objets Step qui sont ajoutés à la base de  données avec l'id du Journey auquel elle appartiennent
	private void generateSteps(Map<String, String> allParams, Journey journey) {
		int idJourney = journey.getIdJourney();

		// J'obtiens une liste de ports désordonnés sans token csrf. Je l'optimise avec getShorterPath
		List<Port> unorderedPortList = getPortsFromParams(allParams);
		List<Port> orderedPortList = getShorterPath(unorderedPortList);

		for (int i = 0; i < orderedPortList.size() - 1; i++) {
			// À chaque passage dans la boucle je récupère les ports de départ et d'arrivée d'une étape. Je créé un objet step via la StepForge. Enfin, j'insère cet objet dans la base de données avec l'id du journey fourni en paramètre.
			Port departurePort = orderedPortList.get(i);
			Port arrivalPort = orderedPortList.get(i + 1);

			Step step = new StepForge(departurePort, arrivalPort).buildStep(idJourney, i + 1);

			ss.insertStep(step);
		}
	}

	private List<Port> getShorterPath(List<Port> list) {

		Port homePort = list.get(0);

		// On a une liste de ports non triés
		List<Port> unsettledPorts = list;
		// Et une liste de ports triés à remplir
		List<Port> settledPorts = new ArrayList<>();
		// On initialise en retirant le premier port (port d'attache) de la liste non-triée pour le mettre dans la liste triée
		settledPorts.add(unsettledPorts.remove(0));

		// On boucle tant qu'il reste des ports à trier
		while (unsettledPorts.size() > 0) {
			
			// C'est à partir du dernier port ajouté à liste des ports trié qu'on commence notre tri
			Port stagedPort = settledPorts.get(settledPorts.size() - 1);
			Port closestPort = new Port();
			// La distance permet de déterminer si un port est plus proche ou plus loin
			int distance = Integer.MAX_VALUE;
			for (int i = 0; i < unsettledPorts.size(); i++) {
				StepForge sf = new StepForge(stagedPort, unsettledPorts.get(i));
				int tempDistance = sf.getDistance();
				System.out.println("Temporaire = " + tempDistance);
				// Si un port est plus proche, on le définit comme le plus proche jusqu'ici
				if (tempDistance < distance) {
					distance = tempDistance;
					closestPort = sf.getArrivalPort();
				}
			}
			// Une fois la boucle finie, le port déterminé comme étant le plus proche est retiré de la liste 
			unsettledPorts.remove(closestPort);
			settledPorts.add(closestPort);
			System.out.println("Final = " + distance + ". Closest port from " + stagedPort.getName() + " is "
					+ closestPort.getName());
		}
		// Le port d'attache est ajouté comme étant le dernier port de la liste (on y retourne à chaque fois)
		settledPorts.add(homePort);

		return settledPorts;
	}

	// On récupère les ports à partir de la liste d'Ids après UN FILTRE POUR RETIRER LE TOKEN CSRF (Cross Site Request Forgery) Ajouté par défaut par Spring Security
	private List<Port> getPortsFromParams(Map<String, String> allParams) {
		List<Port> portsFromParams = new ArrayList<>();
		allParams.forEach((k, v) -> {
			if (k.contains("port")) {
				Port currentPort = getRequestPort(Integer.valueOf(v));
				// On n'ajoute le port à la liste que s'il ne s'y trouve pas déjà: on ne passe pas deux fois par le même port.
				if (!portsFromParams.contains(currentPort)) {
					portsFromParams.add(currentPort);
				}
			}
		});
		return portsFromParams;
	}

//this method takes the port from database by id and convert it to integer
	private Port getRequestPort(int id) {
		return ps.getPort(Integer.valueOf(id)).orElse(null);
	}

}
