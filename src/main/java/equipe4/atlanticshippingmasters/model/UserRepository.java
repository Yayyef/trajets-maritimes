package equipe4.atlanticshippingmasters.model;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
    List<User> findAllByRole(Role role);
    Optional<User> findById(int userId);
    void deleteById(int id);
	User findByUsername(String username);

}
