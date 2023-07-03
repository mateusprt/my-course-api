package mycourses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mycourses.dtos.courses.CourseDto;
import mycourses.dtos.courses.CreateOrUpdateCourseDto;
import mycourses.dtos.lessons.CreateOrUpdateLessonDto;
import mycourses.dtos.lessons.LessonDto;
import mycourses.services.interfaces.CoursesServiceInterface;
import mycourses.services.interfaces.LessonsServiceInterface;


@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {
	
	@Autowired
	private CoursesServiceInterface coursesService;
	
	@Autowired
	private LessonsServiceInterface lessonsService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CourseDto>> listAllCourses() {
		List<CourseDto> allCourses = this.coursesService.listAll();
		return new ResponseEntity<>(allCourses, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CourseDto> createCourse(@RequestBody CreateOrUpdateCourseDto dto) {
		CourseDto courseCreated = this.coursesService.createCourse(dto);
		return new ResponseEntity<>(courseCreated, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseDto> getCourse(@PathVariable(value = "id") Long id) {
		CourseDto courseFound = this.coursesService.findCourse(id);
		return new ResponseEntity<>(courseFound, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseDto> updateCourse(@PathVariable(value = "id") Long id, @RequestBody CreateOrUpdateCourseDto dto) {
		CourseDto categoryUpdated = this.coursesService.updateCourse(id, dto);
		return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteCourse(@PathVariable(value = "id") Long id) {
		this.coursesService.deleteCourse(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{courseId}/lessons")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<LessonDto>> listAllLessonsOfCourse(@PathVariable(value = "courseId") Long courseId) {
		List<LessonDto> lessonsDto = this.lessonsService.listAll(courseId);
		return new ResponseEntity<>(lessonsDto, HttpStatus.OK);
	}
	
	@PostMapping("/{courseId}/lessons")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LessonDto> listAllLessonsOfCourse(@PathVariable(value = "courseId") Long courseId, @RequestBody CreateOrUpdateLessonDto dto) {
		LessonDto lessonCreated = this.lessonsService.createLesson(courseId, dto);
		return new ResponseEntity<>(lessonCreated, HttpStatus.CREATED);
	}
	
	@GetMapping("/{courseId}/lessons/{lessonId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LessonDto> getLessonOfCourse(@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "lessonId") Long lessonId) {
		LessonDto lessonFound = this.lessonsService.findLesson(courseId, lessonId);
		return new ResponseEntity<>(lessonFound, HttpStatus.OK);
	}
	
	@PutMapping("/{courseId}/lessons/{lessonId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LessonDto> updateLesson(@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "lessonId") Long lessonId, @RequestBody CreateOrUpdateLessonDto dto) {
		LessonDto lessonUpdated = this.lessonsService.updateLesson(courseId, lessonId, dto);
		return new ResponseEntity<>(lessonUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{courseId}/lessons/{lessonId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteLesson(@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "lessonId") Long lessonId) {
		this.lessonsService.deleteLesson(courseId, lessonId);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	
}
