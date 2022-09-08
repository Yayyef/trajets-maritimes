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
	
	
	
  //  @ManyToOne
    //@JoinColumn(name="idJourney", referencedColumnName = "idStep",  nullable=false)
    //private  Journey idJourney;
	
	//@OneToMany( targetEntity=Step.class, mappedBy="idStep" )
   // private List<Step> st = new ArrayList<>();

}
