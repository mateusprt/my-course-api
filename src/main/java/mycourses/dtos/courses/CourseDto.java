package mycourses.dtos.courses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseDto {
	
	private Long id;
	
	@JsonProperty("category_id")
	private Long categoryId;
	private String title;
	private String description;
	private Long duration;
	
	@JsonProperty("cover_img")
	private String coverImg;
	
	@JsonProperty("created_at")
	private Date createdAt;
	@JsonProperty("updated_at")
	private Date updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getcategoryId() {
		return categoryId;
	}
	public void setcategoryId(Long categoryId) {
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
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
	
	
}
