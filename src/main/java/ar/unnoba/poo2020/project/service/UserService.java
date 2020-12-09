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
	
	static boolean isValid(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	   }
	
	@Override
	public User create(User user) {
		if (userRepository.findUserByEmail(user.getEmail())== null && isValid(user.getEmail()) == true) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user = userRepository.save(user);
		} else {
			System.out.println("No ha sido posible crear el usuario. Por favor revise sus datos e intente nuevamente.");
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