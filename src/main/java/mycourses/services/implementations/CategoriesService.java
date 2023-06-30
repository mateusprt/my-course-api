package mycourses.services.implementations;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mycourses.dtos.categories.CategoryDto;
import mycourses.dtos.categories.CreateOrUpdateCategoryDto;
import mycourses.entities.Category;
import mycourses.exceptions.ResourceAlreadyExistsException;
import mycourses.exceptions.ResourceNotFoundException;
import mycourses.mappers.Mapper;
import mycourses.repositories.CategoriesRepository;
import mycourses.services.interfaces.CategoriesServiceInterface;

@Service
public class CategoriesService implements CategoriesServiceInterface {
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	private static Logger log = Logger.getLogger(CategoriesService.class.getName());

	@Override
	public List<CategoryDto> listAll() {
		log.info("Listing all categories");
		List<Category> listCategoriesFound = this.categoriesRepository.findAll();
		log.info("Categories found: " + listCategoriesFound.size());
		
		log.info("Mapping Category to CategoryDto");
		List<CategoryDto> listCategoriesDto = Mapper.mapAll(listCategoriesFound, CategoryDto.class);
		log.info("Categories mapped successfully");
		
		return listCategoriesDto;
	}

	@Override
	public CategoryDto createCategory(CreateOrUpdateCategoryDto dto) {
		log.info("Finding category with name: " + dto.getName());
		Category categoryToBeSaved = this.categoriesRepository.findByName(dto.getName()).orElse(null);
		
		if(categoryToBeSaved != null) {
			log.warning("Category found: " + categoryToBeSaved);
			throw new ResourceAlreadyExistsException("Category already exists");
		}
		
		log.info("Category not found");
		log.info("Mapping CategoryDto to Category");
		categoryToBeSaved = Mapper.map(dto, Category.class);
		log.info("Category mapped successfully");
		
		log.info("Saving category");
		categoryToBeSaved = this.categoriesRepository.save(categoryToBeSaved);
		log.info("Category saved successfully");
		
		log.info("Mapping Category to CategoryDto");
		CategoryDto categoryDto = Mapper.map(categoryToBeSaved, CategoryDto.class);
		log.info("CategoryDto mapped successfully");
		
		return categoryDto;
	}

	@Override
	public CategoryDto findCategory(Long id) {
		log.info("Finding category with id: " + id);
		Category categoryFound = this.categoriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		log.info("Category found: " + categoryFound);
		
		log.info("Mapping Category to CategoryDto");
		CategoryDto categoryDto = Mapper.map(categoryFound, CategoryDto.class);
		log.info("CategoryDto mapped successfully");
		
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(Long id, CreateOrUpdateCategoryDto dto) {
		log.info("Finding category with id: " + id);
		Category categoryFound = this.categoriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		log.info("Category found: " + categoryFound);
		
		log.info("Updating category");
		categoryFound.setName(dto.getName());
		this.categoriesRepository.save(categoryFound);
		log.info("Category updated successfully");
		
		log.info("Mapping Category to CategoryDto");
		CategoryDto categoryDto = Mapper.map(categoryFound, CategoryDto.class);
		log.info("CategoryDto mapped successfully");
		
		return categoryDto;
	}

	@Override
	public void deleteCategory(Long id) {
		log.info("Finding category with id: " + id);
		Category categoryFound = this.categoriesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		log.info("Category found: " + categoryFound);
		
		log.info("Deleting category");
		this.categoriesRepository.delete(categoryFound);
		log.info("Category deleted successfully");
	}

	
}
