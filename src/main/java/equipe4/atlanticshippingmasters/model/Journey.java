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
	
	public Port getHomePort() {
//		Journey journey = pr.findById(id).orElse(null);
		Set<Step> steps = this.getSteps();
//		Port homePort = this.getSteps().stream().filter(step -> step.getJourneyOrder() == 1).findFirst().orElse(null).getIdPortDeparture();
		if (this.getSteps().isEmpty()) {
			Port defaultValue = new Port();
			defaultValue.setName("Non défini");
			return defaultValue;
		} else {
			return steps.stream().filter(step -> step.getJourneyOrder() == 1).findFirst().orElse(null).getIdPortDeparture();
		}
	}
	
}
