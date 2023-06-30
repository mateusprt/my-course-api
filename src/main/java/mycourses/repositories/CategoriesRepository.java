package mycourses.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mycourses.entities.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);
}
