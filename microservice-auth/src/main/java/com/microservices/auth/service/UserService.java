package com.microservices.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.auth.domain.User;
import com.microservices.auth.repositories.UserRepository;

@Service(value = "userService")
public class UserService implements UserDetailsService {

	private String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(userId);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority(ROLE_ADMIN));
	}
	
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	public void delete(long id) {
		userRepository.deleteById(id);
	}
	
	public User save(User user) {
        return userRepository.save(user);
    }
}
