package equipe4.atlanticshippingmasters.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import equipe4.atlanticshippingmasters.service.JourneyService;

@Controller
public class JourneyController {

		@Autowired
		private JourneyService journeyService;
		
		
		@GetMapping("/journeys")
		public String GetView(Model model, @RequestParam("page") int page) {
			int itemsPerPage = 10;

			// On convertit notre itérable issu de la bdd en Liste pour obtenir le nombre d'éléments qu'elle contient avec size(). JeON récupère la valeur en double pour que mon calcul du nombre de page fonctionne.
			double size = StreamSupport.stream(journeyService.getAllJourneys().spliterator(), false).collect(Collectors.toList()).size();
			
			// On calcule le nombre de page nécéssaire à l'affichage de tous les journey
			int numberOfPages = (int) Math.ceil( size / itemsPerPage);
			
			// A partir du nombre de pages calculé, on créé une liste d'entiers représentant chacune des pages (ex, si on a 3 pages => [1,2,3])
			if (numberOfPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, numberOfPages)
	                .boxed()
	                .collect(Collectors.toList());
	            
	            // Je passe ma liste de numéro de page à la vue
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
			
			model.addAttribute("journeys", journeyService.getPage(page, itemsPerPage));
			
			return "journeys";
			
		}
		
		@GetMapping("/details/{id}")
		public String JourneyView(@PathVariable Integer id ,Model model) {
			model.addAttribute("journey",journeyService.getJourney(id).orElse(null));
			 
			return "details";
		}
		
	
}
