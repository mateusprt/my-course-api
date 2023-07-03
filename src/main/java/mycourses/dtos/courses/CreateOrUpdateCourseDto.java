package mycourses.dtos.courses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrUpdateCourseDto {
	
	@JsonProperty("category_id")
	private Long categoryId;
	private String title;
	private String description;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	

}
