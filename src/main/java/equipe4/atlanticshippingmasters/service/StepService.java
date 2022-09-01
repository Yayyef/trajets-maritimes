package equipe4.atlanticshippingmasters.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import equipe4.atlanticshippingmasters.model.Step;
import equipe4.atlanticshippingmasters.model.StepRepository;

@Service
public class StepService {

	@Autowired
	private StepRepository pr;
	
	// Chercher et retourner un Step unique avec son id
	public Optional<Step> getStep(final Integer id) {
		return pr.findById(id);
	}
	
	// Chercher et retourner une collection contenant tous les objets de type Step de ma bdd
	public Iterable<Step> getAllSteps() {
		return pr.findAll();
	}
	
	public void deleteStep(final int id) {
		pr.deleteById(id);
	}
	public void insertStep(Step Step) {
		pr.save(Step);
	}
}
