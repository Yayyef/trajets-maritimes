package equipe4.atlanticshippingmasters.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends CrudRepository<Port, Integer> {

	

}
