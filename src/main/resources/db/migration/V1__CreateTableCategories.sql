CREATE TABLE IF NOT EXISTS categories(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
	
	PRIMARY KEY(id)
);