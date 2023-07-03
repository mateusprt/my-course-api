package mycourses.services.interfaces;

import java.util.List;

import mycourses.dtos.lessons.CreateOrUpdateLessonDto;
import mycourses.dtos.lessons.LessonDto;

public interface LessonsServiceInterface {
	List<LessonDto> listAll(Long courseId);
	LessonDto createLesson(Long courseId, CreateOrUpdateLessonDto dto);
	LessonDto findLesson(Long courseId, Long id);
	LessonDto updateLesson(Long courseId, Long id, CreateOrUpdateLessonDto dto);
	void deleteLesson(Long courseId, Long id);
}
