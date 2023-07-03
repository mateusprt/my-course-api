package mycourses.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mycourses.dtos.courses.CourseDto;
import mycourses.dtos.courses.CreateOrUpdateCourseDto;
import mycourses.entities.Category;
import mycourses.entities.Course;
import mycourses.entities.User;
import mycourses.exceptions.ResourceAlreadyExistsException;
import mycourses.exceptions.ResourceNotFoundException;
import mycourses.mappers.Mapper;
import mycourses.repositories.CategoriesRepository;
import mycourses.repositories.CoursesRepository;
import mycourses.repositories.UsersRepository;
import mycourses.services.interfaces.CoursesServiceInterface;

@Service
public class CoursesService implements CoursesServiceInterface {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	private static Logger log = Logger.getLogger(CoursesService.class.getName());

	@Override
	public List<CourseDto> listAll() {
		log.info("Listing all courses");
		List<Course> listCoursesFound = this.coursesRepository.findAll();
		log.info("Courses found: " + listCoursesFound.size());
		
		log.info("Mapping Course to CourseDto");
		List<CourseDto> listCoursesDto = Mapper.mapAll(listCoursesFound, CourseDto.class);
		log.info("Courses mapped successfully");
		
		return listCoursesDto;
	}

	@Override
	public CourseDto createCourse(CreateOrUpdateCourseDto dto) {
		log.info("Finding course with name: " + dto.getTitle());
		Course courseToBeSaved = this.coursesRepository.findByTitle(dto.getTitle()).orElse(null);
		
		if(courseToBeSaved != null) {
			log.warning("Course found: " + courseToBeSaved);
			throw new ResourceAlreadyExistsException("Course already exists");
		}
		
		log.info("Course not found");
		
		log.info("Finding category with id: " + dto.getCategoryId());
		Category categoryFound = this.categoriesRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		log.info("Category found: " + categoryFound);
		
		log.info("Mapping CourseDto to Course");
		Course newCourse = new Course();
		newCourse.setTitle(dto.getTitle());
		newCourse.setDescription(dto.getDescription());
		newCourse.setCategory(categoryFound);
		log.info("Course mapped successfully");
		
		log.info("Setting user into Course");
		User currentUser = this.usersRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		newCourse.setUser(currentUser);
		
		log.info("Saving course");
		newCourse = this.coursesRepository.save(newCourse);
		log.info("Course saved successfully");
		
		log.info("Mapping Course to CourseDto");
		CourseDto courseDto = Mapper.map(newCourse, CourseDto.class);
		log.info("CourseDto mapped successfully");
		
		return courseDto;
	}

	@Override
	public CourseDto findCourse(Long id) {
		log.info("Finding course with id: " + id);
		Course courseFound = this.coursesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Mapping Course to CourseDto");
		CourseDto courseDto = Mapper.map(courseFound, CourseDto.class);
		log.info("CourseDto mapped successfully");
		
		return courseDto;
	}

	@Override
	public CourseDto updateCourse(Long id, CreateOrUpdateCourseDto dto) {
		log.info("Finding course with id: " + id);
		Course courseFound = this.coursesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Finding category with id: " + dto.getCategoryId());
		Category categoryFound = this.categoriesRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		log.info("Category found: " + categoryFound);
		
		
		log.info("Updating course");
		courseFound.setCategory(categoryFound);
		courseFound.setTitle(dto.getTitle());
		courseFound.setDescription(dto.getDescription());
		this.coursesRepository.save(courseFound);
		log.info("Course updated successfully");
		
		log.info("Mapping Course to CourseDto");
		CourseDto courseDto = Mapper.map(courseFound, CourseDto.class);
		log.info("CourseDto mapped successfully");
		
		return courseDto;
	}

	@Override
	public void deleteCourse(Long id) {
		log.info("Finding course with id: " + id);
		Course courseFound = this.coursesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Deleting course");
		this.coursesRepository.delete(courseFound);
		log.info("Course deleted successfully");
	}

	
}
