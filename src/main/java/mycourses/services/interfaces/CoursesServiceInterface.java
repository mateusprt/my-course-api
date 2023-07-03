package mycourses.services.interfaces;

import java.util.List;

import mycourses.dtos.courses.CourseDto;
import mycourses.dtos.courses.CreateOrUpdateCourseDto;

public interface CoursesServiceInterface {
	List<CourseDto> listAll();
	CourseDto createCourse(CreateOrUpdateCourseDto dto);
	CourseDto findCourse(Long id);
	CourseDto updateCourse(Long id, CreateOrUpdateCourseDto dto);
	void deleteCourse(Long id);
}
