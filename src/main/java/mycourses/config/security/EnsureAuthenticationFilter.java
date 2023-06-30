package mycourses.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mycourses.entities.User;
import mycourses.exceptions.ResourceNotFoundException;
import mycourses.repositories.UsersRepository;
import mycourses.services.interfaces.JWTServiceInterface;

@Component
public class EnsureAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTServiceInterface jwtService;
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authHeader.substring(7);
		
		if(!this.jwtService.validateToken(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		User userDetails = this.usersRepository.findByEmail(this.jwtService.extractUsername(token)).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getEmail(), null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);		
	}

}