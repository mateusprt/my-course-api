CREATE TABLE IF NOT EXISTS lessons(
	id INT NOT NULL AUTO_INCREMENT,
	course_id INT NOT NULL,
	name VARCHAR(100) NOT NULL,
	duration INT NOT NULL,
	media_type VARCHAR(50),
	media_url VARCHAR(255),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
	
	PRIMARY KEY(id),
	CONSTRAINT FK_course_lessons FOREIGN KEY (course_id) REFERENCES courses (id)
);