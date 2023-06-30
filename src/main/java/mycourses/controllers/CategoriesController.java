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
import mycourses.dtos.categories.CreateOrUpdateCategoryDto;
import mycourses.services.interfaces.CategoriesServiceInterface;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
	
	@Autowired
	private CategoriesServiceInterface categoriesService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CategoryDto>> listAllCategories() {
		List<CategoryDto> allCategories = this.categoriesService.listAll();
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateOrUpdateCategoryDto dto) {
		CategoryDto categoryCreated = this.categoriesService.createCategory(dto);
		return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CategoryDto> getCategory(@PathVariable(value = "id") Long id) {
		CategoryDto categoryFound = this.categoriesService.findCategory(id);
		return new ResponseEntity<>(categoryFound, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "id") Long id, @RequestBody CreateOrUpdateCategoryDto dto) {
		CategoryDto categoryUpdated = this.categoriesService.updateCategory(id, dto);
		return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable(value = "id") Long id) {
		this.categoriesService.deleteCategory(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
}
