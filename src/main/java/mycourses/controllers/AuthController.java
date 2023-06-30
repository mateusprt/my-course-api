package mycourses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mycourses.dtos.auth.AuthDto;
import mycourses.dtos.auth.AuthLoginDto;
import mycourses.dtos.auth.AuthTokenDto;
import mycourses.dtos.auth.AuthRegistrationDto;
import mycourses.services.interfaces.AuthServiceInterface;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthServiceInterface authService;
	
	@PostMapping("/registration")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AuthDto> registration(@RequestBody AuthRegistrationDto dto) {
		AuthDto responseDto = this.authService.createUser(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
	
	@PostMapping("/confirmation/{confirmationToken}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AuthDto> registration(@PathVariable("confirmationToken") String confirmationToken) {
		AuthDto responseDto = this.authService.confirmUser(confirmationToken);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AuthTokenDto> loginUser(@RequestBody AuthLoginDto dto) {
		AuthTokenDto tokenDto = this.authService.login(dto);
		return new ResponseEntity<>(tokenDto, HttpStatus.OK);
	}
	
}
