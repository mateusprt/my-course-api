package mycourses.entities;

import java.util.Date;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import mycourses.enums.MediaType;

@Entity
@Table(name = "lessons")
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
	@Column
	@NotEmpty(message = "name can't be blank")
	private String name;
	
	@Column
	@NotNull(message = "duration can't be blank")
	@Min(value = 0L, message = "duration can't be a negative value")
	private Long duration;
	
	@Column(name = "media_type")
	@Enumerated(EnumType.STRING)
	private MediaType mediaType;
	
	@Column(name = "media_url")
	private String mediaUrl;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	public Lesson() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
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

	@Override
	public int hashCode() {
		return Objects.hash(course, createdAt, duration, id, mediaType, mediaUrl, name, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return Objects.equals(course, other.course) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(duration, other.duration) && Objects.equals(id, other.id)
				&& mediaType == other.mediaType && Objects.equals(mediaUrl, other.mediaUrl)
				&& Objects.equals(name, other.name) && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", course=" + course + ", name=" + name + ", duration=" + duration + ", mediaType="
				+ mediaType + ", mediaUrl=" + mediaUrl + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
