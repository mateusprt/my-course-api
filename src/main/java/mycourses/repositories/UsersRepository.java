package mycourses.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mycourses.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByConfirmationToken(String confirmationToken);
}
