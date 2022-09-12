package equipe4.atlanticshippingmasters.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
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

	
	public int getIdStep() {
		return idStep;
	}
	public void setIdStep(int idStep) {
		this.idStep = idStep;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getJourneyOrder() {
		return journeyOrder;
	}
	public void setJourneyOrder(int journeyOrder) {
		this.journeyOrder = journeyOrder;
	}
	public int getIdJourneyFk() {
		return idJourneyFk;
	}
	public void setIdJourneyFk(int idJourneyFk) {
		this.idJourneyFk = idJourneyFk;
	}
	public Port getIdPortDeparture() {
		return idPortDeparture;
	}
	public void setIdPortDeparture(Port idPortDeparture) {
		this.idPortDeparture = idPortDeparture;
	}
	public Port getIdPortArrival() {
		return idPortArrival;
	}
	public void setIdPortArrival(Port idPortArrival) {
		this.idPortArrival = idPortArrival;
	}
}

