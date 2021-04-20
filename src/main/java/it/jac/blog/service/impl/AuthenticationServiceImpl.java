package it.jac.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.jac.blog.model.User;
import it.jac.blog.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements UserDetailsService, AuthenticationManager {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	private static final SimpleGrantedAuthority USER= new SimpleGrantedAuthority("ROLE_USER");
	private static final SimpleGrantedAuthority WRITER= new SimpleGrantedAuthority("ROLE_WRITER");
	private static final SimpleGrantedAuthority REVIEWER= new SimpleGrantedAuthority("ROLE_REVIEWER");
	private static final SimpleGrantedAuthority ADMIN= new SimpleGrantedAuthority("ROLE_ADMIN");

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		switch (user.getRole()) {
			case ADMIN:
				grantedAuths.add(USER);
				grantedAuths.add(WRITER);
				grantedAuths.add(REVIEWER);
				grantedAuths.add(ADMIN);
				break;
			case REVIEWER:
				grantedAuths.add(USER);
				grantedAuths.add(WRITER);
				grantedAuths.add(REVIEWER);
				break;
			case WRITER:
				grantedAuths.add(USER);
				grantedAuths.add(WRITER);
				break;
			default:
				grantedAuths.add(USER);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				bCryptPasswordEncoder.encode(user.getPassword()), grantedAuths);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		User user = userRepository.findByUsername(username);
		if (user == null || !(password.equals(user.getPassword()))) {
			throw new BadCredentialsException("1000");
		}
		return authentication;
	}

}