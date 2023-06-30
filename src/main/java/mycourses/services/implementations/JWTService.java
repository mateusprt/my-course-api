package mycourses.services.implementations;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mycourses.entities.User;
import mycourses.exceptions.ResourceNotFoundException;
import mycourses.repositories.UsersRepository;
import mycourses.services.interfaces.JWTServiceInterface;

@Service
public class JWTService implements JWTServiceInterface {
	
	@Value("${security.jwt.secret}")
	private  String SECRET;
	private Long JWT_EXPIRATION = 86400000L;
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public String generateJwtToken(User user) {
		return Jwts
				.builder()
				.setIssuer("")
				.setSubject(user.getEmail())
				.claim("authorities", user.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION))
				.signWith(this.getKey())
				.compact();
	}

	@Override
	public boolean validateToken(String token) {
		User userFound = this.usersRepository.findByEmail(this.extractUsername(token)).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		boolean confirmedAccount = !userFound.isUnconfirmed();
		boolean tokenNotExpired = !isTokenExpired(token);
		return confirmedAccount && tokenNotExpired;
	}
	
	public String extractUsername(String token) {
		return this.extractClaim(token, Claims::getSubject);
	}
	
	private boolean isTokenExpired(String token) {
		return this.extractExpirationDate(token).before(new Date());
	}
	
	private Date extractExpirationDate(String token) {
		return this.extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = this.extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
					.parserBuilder()
					.setSigningKey(this.getKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
	}
	
	private Key getKey() {
		byte[] secretInByteFormat = this.SECRET.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(secretInByteFormat);
	}

}
