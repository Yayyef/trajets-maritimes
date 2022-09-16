package equipe4.atlanticshippingmasters.service;

import java.util.HashSet;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import equipe4.atlanticshippingmasters.model.Role;
import equipe4.atlanticshippingmasters.model.User;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private equipe4.atlanticshippingmasters.model.UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getText()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    public User findByEmail(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByRole(Role.USER);
    }

	public void removeUser(int idUser) {
		userRepository.deleteById(idUser);
		
	}


	
}
