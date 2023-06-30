package mycourses.services.interfaces;

import mycourses.entities.User;

public interface JWTServiceInterface {
	String generateJwtToken(User user);
	boolean validateToken(String token);
	String extractUsername(String token);
}
