package equipe4.atlanticshippingmasters.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "port")
public class Port {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPort;

	@Pattern(regexp = "^[a-zA-Z]{2,15}$", message="Port name should contain alphabates only")
	private String name;
	@Pattern(regexp="(\\d+)\\s?°\\s?(\\d+)\\s?'\\s?(\\d+\\.?\\,?\\d*?)\"\\s?N (\\d+)\\s?°\\s?(\\d+)\\s?'\\s?(\\d+\\.?\\,?\\d*?)\"\\s?W",message="Coordinates must contain degree,minutes,second and north south direction ")
	private String coordinates;
	
}
