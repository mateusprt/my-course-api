package mycourses.services.interfaces;

import mycourses.dtos.auth.AuthDto;
import mycourses.dtos.auth.AuthLoginDto;
import mycourses.dtos.auth.AuthTokenDto;
import mycourses.dtos.auth.AuthRegistrationDto;

public interface AuthServiceInterface {
	AuthDto createUser(AuthRegistrationDto dto);
	AuthDto confirmUser(String confirmationToken);
	AuthTokenDto login(AuthLoginDto dto);
}
