package ar.unnoba.poo2020.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.unnoba.poo2020.project.model.User;
import ar.unnoba.poo2020.project.repository.UserRepository;

@Service
public class UserService implements IUserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User create(User user) {
		if (userRepository.findUserByEmail(user.getEmail())== null) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user = userRepository.save(user);
		}	
		return user;
	}

	@Override
	public List<User> getUsers() {
		
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User find(Long id) {

		return userRepository.findById(id).get();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findUserByEmail(email);
	}

	
}