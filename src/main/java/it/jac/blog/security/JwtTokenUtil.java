package it.jac.blog.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.jac.blog.model.User;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	@Value("${jwt.expiration}")
	public long expiration;

	@Value("${jwt.secret}")
	private String secret;

//generate token for user
	public String generateToken(User userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", userDetails.getRole());
		return doGenerateToken(claims, userDetails.getUsername());
	}

//validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getUsernameFromToken(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring("Bearer ".length());
		return getClaimFromToken(jwtToken, Claims::getSubject);
	}

	public String getUsernameFromToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring("Bearer ".length());
		return getClaimFromToken(jwtToken, Claims::getSubject);
	}

//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

//while creating the token -
//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//2. Sign the JWT using the HS512 algorithm and secret key.
//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

}