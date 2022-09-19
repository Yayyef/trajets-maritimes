package equipe4.atlanticshippingmasters.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "journey")
public class Journey {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idJourney;
	private int totalDistance;
	
	@OneToMany (mappedBy = "idJourneyFk") 
	private Set<Step> steps;
	
	// Détermination du port d'attache
	public Port getHomePort() {
		Set<Step> steps = this.getSteps();
		// Si la liste d'étapes est vide on a une erreur au niveau de thymeleaf. On donne donc une valeur par défaut
		if (this.getSteps().isEmpty()) {
			Port defaultValue = new Port();
			defaultValue.setName("Non défini");
			return defaultValue;
		} else {
//			On cherche le port de départ de la première étape (laquelle est trouvée grâce au journeyOrder)
			return steps.stream().filter(step -> step.getJourneyOrder() == 1).findFirst().orElse(null).getIdPortDeparture();
		}
	}
	
}
