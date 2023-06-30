package mycourses.services.interfaces;

import java.util.List;

import mycourses.dtos.categories.CategoryDto;
import mycourses.dtos.categories.CreateOrUpdateCategoryDto;



public interface CategoriesServiceInterface {
	List<CategoryDto> listAll();
	CategoryDto createCategory(CreateOrUpdateCategoryDto dto);
	CategoryDto findCategory(Long id);
	CategoryDto updateCategory(Long id, CreateOrUpdateCategoryDto dto);
	void deleteCategory(Long id);
}
