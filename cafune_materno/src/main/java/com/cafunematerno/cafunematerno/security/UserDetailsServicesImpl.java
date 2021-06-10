package com.cafunematerno.cafunematerno.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.repository.UsuariosRepository;

@Service
public class UserDetailsServicesImpl implements UserDetailsService{
	
	@Autowired
	private UsuariosRepository userRepository;
	
		public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException {
		Optional<Usuarios> user = userRepository.findByEmailIgnoreCase(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "Not Found."));
		
		return user.map(UserDetailsImpl::new).get();
	}

}
