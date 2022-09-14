package equipe4.atlanticshippingmasters.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import equipe4.atlanticshippingmasters.model.Journey;
import equipe4.atlanticshippingmasters.model.JourneyRepository;

@Service
public class JourneyService {
	@Autowired
	private JourneyRepository pr;

	// Chercher et retourner un Journey unique avec son id
	public Optional<Journey> getJourney(final Integer id) {
		return pr.findById(id);
	}

	// Chercher et retourner une collection contenant tous les objets de type
	// Journey de ma bdd
	public Iterable<Journey> getAllJourneys() {
		return pr.findAll();
	}

	// Méthode pour récupérer une page de journeys
	public List<Journey> getPage(int pageNumber, int itemsPerPage) {
		// On converti l'itérable récupéré en liste pour avoir accès à get()
		List<Journey> allJourneys = StreamSupport.stream(pr.findAll().spliterator(), false).collect(Collectors.toList());
		List<Journey> pageList = new ArrayList<>();
		
		for (int i = (pageNumber*itemsPerPage) - itemsPerPage; i < pageNumber*itemsPerPage; i++) {
			if (i >= allJourneys.size()) break;
			pageList.add(allJourneys.get(i));
		}
				
		return pageList;
	}

	public void deleteJourney(final int id) {
		pr.deleteById(id);
	}

	public void insertJourney(Journey Journey) {
		pr.save(Journey);
	}

}
