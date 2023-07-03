package mycourses.services.implementations;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mycourses.dtos.lessons.CreateOrUpdateLessonDto;
import mycourses.dtos.lessons.LessonDto;
import mycourses.entities.Course;
import mycourses.entities.Lesson;
import mycourses.exceptions.ResourceNotFoundException;
import mycourses.mappers.Mapper;
import mycourses.repositories.CoursesRepository;
import mycourses.repositories.LessonsRepository;
import mycourses.services.interfaces.LessonsServiceInterface;

@Service
public class LessonsService implements LessonsServiceInterface {
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private LessonsRepository lessonsRepository;
	
	private static Logger log = Logger.getLogger(LessonsService.class.getName());

	@Override
	public List<LessonDto> listAll(Long courseId) {
		log.info("Finding course with id: " + courseId);
		Course courseFound = this.coursesRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Mapping all lessons to LessoDto");
		List<LessonDto> lessons = Mapper.mapAll(courseFound.getLessons(), LessonDto.class);
		log.info("Lessons mapped successfully");
		
		return lessons;
	}

	@Override
	public LessonDto createLesson(Long courseId, CreateOrUpdateLessonDto dto) {
		log.info("Finding course with id: " + courseId);
		Course courseFound = this.coursesRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Saving lesson");
		Lesson newLesson = new Lesson();
		newLesson.setName(dto.getName());
		newLesson.setDuration(100L);
		newLesson.setCourse(courseFound);
		
		newLesson = this.lessonsRepository.save(newLesson);
		log.info("Lesson saved successfully");
		
		log.info("Mapping Lesson to LessonDto");
		LessonDto lessonDto = Mapper.map(newLesson, LessonDto.class);
		log.info("LessonDto mapped");
		log.info("Lesson created successfully");
		
		return lessonDto;
	}

	@Override
	public LessonDto findLesson(Long courseId, Long id) {
		log.info("Finding course with id: " + courseId);
		Course courseFound = this.coursesRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Finding lesson with id: " + id);
		Lesson lessonFound = this.lessonsRepository.findLessonByCourseIdAndLessonId(courseId, id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
		log.info("Lesson found: " + lessonFound);
		
		log.info("Mapping Lesson to LessonDto");
		LessonDto lessonDto = Mapper.map(lessonFound, LessonDto.class);
		log.info("LessonDto mapped");
		
		return lessonDto;
	}

	@Override
	public LessonDto updateLesson(Long courseId, Long id, CreateOrUpdateLessonDto dto) {
		log.info("Finding course with id: " + courseId);
		Course courseFound = this.coursesRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Finding lesson with id: " + id);
		Lesson lessonFound = this.lessonsRepository.findLessonByCourseIdAndLessonId(courseId, id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
		log.info("Lesson found: " + lessonFound);
		
		log.info("Updating lesson");
		lessonFound.setName(dto.getName());
		lessonFound.setDuration(100L);
		lessonFound.setCourse(courseFound);
		
		lessonFound = this.lessonsRepository.save(lessonFound);
		log.info("Lesson updated successfully");
		
		log.info("Mapping Lesson to LessonDto");
		LessonDto lessonDto = Mapper.map(lessonFound, LessonDto.class);
		log.info("LessonDto mapped");
		log.info("Lesson updated successfully");
		
		return lessonDto;
	}

	@Override
	public void deleteLesson(Long courseId, Long id) {
		log.info("Finding course with id: " + courseId);
		Course courseFound = this.coursesRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("course not found"));
		log.info("Course found: " + courseFound);
		
		log.info("Finding lesson with id: " + id);
		Lesson lessonFound = this.lessonsRepository.findLessonByCourseIdAndLessonId(courseId, id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
		log.info("Lesson found: " + lessonFound);
		
		log.info("Deleting Lesson");
		this.lessonsRepository.delete(lessonFound);
		log.info("Lesson dleeted successfully");
	}

}
