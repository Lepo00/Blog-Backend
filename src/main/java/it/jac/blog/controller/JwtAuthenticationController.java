package it.jac.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.blog.model.ResponseMessage;
import it.jac.blog.model.User;
import it.jac.blog.repository.UserRepository;
import it.jac.blog.security.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/token")
	public ResponseEntity<ResponseMessage> createAuthenticationToken(@RequestBody User authentication){
		String username = authentication.getUsername();
		String password = authentication.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			User user = userRepository.findByUsername(username);
			final String token = jwtTokenUtil.generateToken(user);

			return ResponseEntity.ok(new ResponseMessage(token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Invalid credentials"));
		}
	}
}