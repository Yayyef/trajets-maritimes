package equipe4.atlanticshippingmasters.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import equipe4.atlanticshippingmasters.model.Journey;
import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.model.Step;
import equipe4.atlanticshippingmasters.service.JourneyService;
import equipe4.atlanticshippingmasters.service.PortService;
import equipe4.atlanticshippingmasters.tools.ToolBox;

@Controller
public class JourneyController {

	@Autowired
	private JourneyService journeyService;
	@Autowired
	private PortService ps;

	private ToolBox tools = new ToolBox();

	@GetMapping("/journeys")
	public String getView(Model model, @RequestParam("page") int page) {
		Iterable<Port> portList = ps.getAllPorts();

		int itemsPerPage = 10;
		
		// Je passe ma liste de numéro de page à la vue
		model.addAttribute("pageNumbers", createPageList(itemsPerPage));
		model.addAttribute("portsTools", tools.shortenPortList(portList));
		model.addAttribute("journeys", journeyService.getJourneyPage(page, itemsPerPage));

		return "journeys";
	}

	@GetMapping("/details/{id}")
	public String journeyView(@PathVariable Integer id, Model model) {
		// On récupère une liste sans ordre
		Iterable<Port> portList = ps.getAllPorts();

		Journey journey = journeyService.getJourney(id).orElse(null);
		// Conversion en liste pour pouvoir utiliser sort()
		List<Step> steps = StreamSupport.stream(journey.getSteps().spliterator(), false).collect(Collectors.toList());
		// On range nos étapes grâce avec sort
		Collections.sort(steps);

		// Calcule de la distance totale grace à stream
		int totalDistance = journey.getSteps().stream().mapToInt(s -> s.getDistance()).sum();

		model.addAttribute("portsTools", tools.shortenPortList(portList));
		model.addAttribute("journey", journey);
		model.addAttribute("steps", steps);
		model.addAttribute("totalDistance", totalDistance);
		return "details";
	}

	private List<Integer> createPageList(int itemsPerPage) {
		// On convertit notre itérable issu de la bdd en Liste pour obtenir le nombre
		// d'éléments qu'elle contient avec size(). ON récupère la valeur en double pour
		// que mon calcul du nombre de page fonctionne.
		double size = StreamSupport.stream(journeyService.getAllJourneys().spliterator(), false).collect(Collectors.toList()).size();

		// On calcule le nombre de page nécéssaire à l'affichage de tous les journey
		int numberOfPages = (int) Math.ceil(size / itemsPerPage);
		List<Integer> pageNumbers = new ArrayList<>();
		
		// A partir du nombre de pages calculé, on créé une liste d'entiers représentant
		// chacune des pages (ex, si on a 3 pages => [1,2,3])
		if (numberOfPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, numberOfPages).boxed().collect(Collectors.toList());

		}
		return pageNumbers;
	}

}
