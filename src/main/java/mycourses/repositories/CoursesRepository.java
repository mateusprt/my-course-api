package mycourses.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mycourses.entities.Course;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByTitle(String name);
}
