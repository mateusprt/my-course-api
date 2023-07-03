package mycourses.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mycourses.entities.Lesson;

@Repository
public interface LessonsRepository extends JpaRepository<Lesson, Long> {
	
	@Query("SELECT l FROM Lesson l WHERE l.course.id = :course_id AND l.id = :lesson_id")
	Optional<Lesson> findLessonByCourseIdAndLessonId(@Param("course_id") Long courseId, @Param("lesson_id") Long id);
}
