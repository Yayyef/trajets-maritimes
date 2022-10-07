package equipe4.atlanticshippingmasters.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import equipe4.atlanticshippingmasters.model.Role;
import equipe4.atlanticshippingmasters.model.User;
import equipe4.atlanticshippingmasters.model.UserRepository;


@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void createNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = Role.ADMIN;
        user.setRole(role);
        userRepository.save(user);
    }
    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = Role.USER;
        user.setRole(role);
        userRepository.save(user);
    }



}