package mycourses.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private Long duration;
	
	@Column(name = "cover_img")
	private String coverImg;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@OneToMany(mappedBy = "course")
	public List<Lesson> lessons;
	
	public Course() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
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


	public List<Lesson> getLessons() {
		return lessons;
	}


	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}


	@Override
	public int hashCode() {
		return Objects.hash(category, coverImg, createdAt, description, duration, id, title, updatedAt, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(category, other.category) && Objects.equals(coverImg, other.coverImg)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& Objects.equals(duration, other.duration) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title) && Objects.equals(updatedAt, other.updatedAt)
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", user=" + user + ", category=" + category + ", title=" + title + ", description="
				+ description + ", duration=" + duration + ", coverImg=" + coverImg + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}	
	

}
