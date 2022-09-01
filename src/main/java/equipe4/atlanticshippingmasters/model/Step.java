package equipe4.atlanticshippingmasters.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "step")
public class Step {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idStep;
	private int distance;
	private int journeyOrder;
	private int idPortDeparture;
	private int idPortArrival;
	private int idJourneyFk;
	
}