package mycourses.services.implementations;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mycourses.dtos.auth.AuthDto;
import mycourses.dtos.auth.AuthLoginDto;
import mycourses.dtos.auth.AuthRegistrationDto;
import mycourses.dtos.auth.AuthTokenDto;
import mycourses.entities.User;
import mycourses.enums.Role;
import mycourses.exceptions.BadConfirmationTokenException;
import mycourses.exceptions.ResourceAlreadyExistsException;
import mycourses.repositories.UsersRepository;
import mycourses.services.interfaces.AuthServiceInterface;
import mycourses.services.interfaces.JWTServiceInterface;
import mycourses.utils.EmailUtils;

@Service
public class AuthService implements AuthServiceInterface, UserDetailsService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTServiceInterface jwtService;
	
	private static Logger logger = Logger.getLogger(AuthService.class.toString());
	
	@Override
	public AuthDto createUser(AuthRegistrationDto dto) {
		logger.info("Checking if email is empty");
		if(dto.getEmail() == "") {
			logger.warning("email can't be blank");
			throw new BadCredentialsException("email can't be blank");
		}
		logger.info("password isn't empty");
		
		logger.info("Checking if email is valid");
		boolean emailValid = EmailUtils.validate(dto.getEmail());
		if(!emailValid) {
			logger.info("invalid email format");
			throw new BadCredentialsException("invalid email");
		}
		logger.info("Valid email");
		
		logger.info("Find a user by email = " + dto.getEmail());
		User userFound = this.usersRepository.findByEmail(dto.getEmail()).orElse(null);
		if(userFound != null) {
			logger.warning("user already exists");
			throw new ResourceAlreadyExistsException("user already exists");
		}
		logger.info("User not found");
		
		
		logger.info("Checking if password is empty");
		if(dto.getPassword() == "") {
			logger.warning("password is empty");
			throw new BadCredentialsException("password can't be blank");
		}
		logger.info("password isn't empty");
		
		
		logger.info("Check if password has required minimum lenght");
		boolean passwordValid = this.passwordHasRequiredMinimumLenght(dto);
		if(!passwordValid) {
			logger.warning("passsword must be a minimum length of 5 characters");
			throw new BadCredentialsException("passsword must be a minimum length of 5 characters");
		}
		
		
		logger.info("Checking if password and password_confirmation match");
		boolean passwordsAndPasswordConfirmationDoesntMatch = !this.passwordAndPasswordConfirmationMatch(dto);
		if(passwordsAndPasswordConfirmationDoesntMatch) {
			logger.warning("password and password_confirmation doesn't match");
			throw new BadCredentialsException("password and password_confirmation must be equals");
		}
		
		logger.info("Saving user");
		User userToBeSaved = new User();
		userToBeSaved.setEmail(dto.getEmail().toLowerCase());
		String passwordHashed = this.hashPassord(dto.getPassword());
		userToBeSaved.setPassword(passwordHashed);
		String confirmationToken = this.generateConfirmationToken();
		userToBeSaved.setConfirmationToken(confirmationToken);
		userToBeSaved.setConfirmationTokenSentAt(new Date());
		userToBeSaved.setUnconfirmed(true);
		userToBeSaved.setRole(Role.USER);
		
		this.usersRepository.save(userToBeSaved);
		logger.info("User saved successfully");
		
		return new AuthDto("Account created successfully. A confirmation link was sent to your email.");
	}
	
	public AuthDto confirmUser(String confirmationToken) {
		logger.info("Finding user by confirmation_token");
		User userFound = this.usersRepository.findByConfirmationToken(confirmationToken).orElseThrow(() -> new BadConfirmationTokenException("account not found"));
		logger.info("User found");
		
		boolean userAlreadyConfirmed = !userFound.isUnconfirmed();
		if(userAlreadyConfirmed) {
			throw new BadCredentialsException("account already active");
		}
		
		logger.info("Updating user");
		userFound.setConfirmedAt(new Date());
		userFound.setUnconfirmed(false);
		this.usersRepository.save(userFound);
		logger.info("User updated sucessfully");
		
		return new AuthDto("Account confirmed successfully!");
	}
	
	public AuthTokenDto login(AuthLoginDto dto) {
		logger.info("Authenticate user");
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		logger.info("User authenticated");
		
		User  userFound = this.usersRepository.findByEmail(dto.getEmail()).orElseThrow();
		
		logger.info("Generating JWT token");
		String token = this.jwtService.generateJwtToken(userFound);
		logger.info("JWT token generated successfully");
		AuthTokenDto tokenDto = new AuthTokenDto(token);
		return tokenDto;
	}
	
	private boolean passwordAndPasswordConfirmationMatch(AuthRegistrationDto dto) {
		return dto.getPassword().equals(dto.getPasswordConfirmation());
	}
	
	private boolean passwordHasRequiredMinimumLenght(AuthRegistrationDto dto) {
		return dto.getPassword().length() > 4;
	}
	
	private String hashPassord(String password) {
		return this.passwordEncoder.encode(password);
	}
	
	private String generateConfirmationToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
