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

import mycourses.dtos.categories.CategoryDto;
import mycourses.dtos.courses.CourseDto;
import mycourses.dtos.courses.CreateOrUpdateCourseDto;
import mycourses.services.interfaces.CoursesServiceInterface;


@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {
	
	@Autowired
	private CoursesServiceInterface coursesService;
	
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
	public ResponseEntity<CategoryDto> deleteCourse(@PathVariable(value = "id") Long id) {
		this.coursesService.deleteCourse(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
}
