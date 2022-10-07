package equipe4.atlanticshippingmasters.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.model.PortRepository;

@Service
public class PortService {
	
	
	@Autowired
	private PortRepository pr;
//	private Random randomGenerator;
	
	// Chercher et retourner un port unique avec son id
	public Optional<Port> getPort(final Integer id) {
		return pr.findById(id);
	}
	
	// Chercher et retourner une collection contenant tous les objets de type port de ma bdd
	public Iterable<Port> getAllPorts() {
		return pr.findAll();
	}
	
	public void deletePort(final int id) {
		pr.deleteById(id);
	}
	public void insertPort(Port Port) {
		pr.save(Port);
	}
	
	}

//	public Port get(int randomIndex) {
//		// TODO Auto-generated method stub
//		return (Port) randomGenerator.ints(randomIndex);
//	}

