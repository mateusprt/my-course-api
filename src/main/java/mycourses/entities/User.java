package mycourses.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.management.relation.Role;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String password;
	
	@Column(name = "confirmation_token")
	private String confirmationToken;
	
	@Column(name = "confirmation_token_sent_at")
	private Date confirmationTokenSentAt;
	
	@Column(name = "confirmed_at")
	private Date confirmedAt;
	
	@Column(name = "password_reset_token")
	private String passwordResetToken;
	
	@Column(name = "password_reset_token_sent_at")
	private Date passwordResetTokenSentAt;
	
	@Column
	private boolean unconfirmed;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	@OneToMany(mappedBy = "user")
	private List<Course> courses;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getConfirmationTokenSentAt() {
		return confirmationTokenSentAt;
	}

	public void setConfirmationTokenSentAt(Date confirmationTokenSentAt) {
		this.confirmationTokenSentAt = confirmationTokenSentAt;
	}

	public Date getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(Date confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Date getPasswordResetTokenSentAt() {
		return passwordResetTokenSentAt;
	}

	public void setPasswordResetTokenSentAt(Date passwordResetTokenSentAt) {
		this.passwordResetTokenSentAt = passwordResetTokenSentAt;
	}

	public boolean isUnconfirmed() {
		return unconfirmed;
	}

	public void setUnconfirmed(boolean unconfirmed) {
		this.unconfirmed = unconfirmed;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmationToken, confirmationTokenSentAt, confirmedAt, createdAt, email, id, password,
				passwordResetToken, passwordResetTokenSentAt, role, unconfirmed, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(confirmationToken, other.confirmationToken)
				&& Objects.equals(confirmationTokenSentAt, other.confirmationTokenSentAt)
				&& Objects.equals(confirmedAt, other.confirmedAt) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password)
				&& Objects.equals(passwordResetToken, other.passwordResetToken)
				&& Objects.equals(passwordResetTokenSentAt, other.passwordResetTokenSentAt)
				&& Objects.equals(role, other.role) && unconfirmed == other.unconfirmed
				&& Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", confirmationToken="
				+ confirmationToken + ", confirmationTokenSentAt=" + confirmationTokenSentAt + ", confirmedAt="
				+ confirmedAt + ", passwordResetToken=" + passwordResetToken + ", passwordResetTokenSentAt="
				+ passwordResetTokenSentAt + ", unconfirmed=" + unconfirmed + ", role=" + role + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
	
	
}
