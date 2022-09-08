package equipe4.atlanticshippingmasters.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private int idJourneyFk;
	
	// Name c'est le nom de la colonne de CE modèle. ReferencedColumnname vise le modèle de l'autre table
	@ManyToOne
    @JoinColumn(name="idPortDeparture", referencedColumnName = "idPort",  nullable=false)
	private Port idPortDeparture;
	@ManyToOne
	@JoinColumn(name="idPortArrival", referencedColumnName = "idPort",  nullable=false)
	private Port idPortArrival;

}

