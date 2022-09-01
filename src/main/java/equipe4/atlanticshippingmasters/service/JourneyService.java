package equipe4.atlanticshippingmasters.service;

import java.util.Optional;

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
	
	// Chercher et retourner une collection contenant tous les objets de type Journey de ma bdd
	public Iterable<Journey> getAllJourneys() {
		return pr.findAll();
	}
	
	public void deleteJourney(final int id) {
		pr.deleteById(id);
	}
	public void insertJourney(Journey Journey) {
		pr.save(Journey);
	}
}
