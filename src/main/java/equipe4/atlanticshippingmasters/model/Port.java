package equipe4.atlanticshippingmasters.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "port")
public class Port {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPort;
	
	private String name = "Non défini";
	private String coordinates;
	
}
