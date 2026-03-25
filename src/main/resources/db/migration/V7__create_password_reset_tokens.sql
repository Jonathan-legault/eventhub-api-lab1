CREATE TABLE password_reset_tokens (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       token VARCHAR(255) NOT NULL UNIQUE,
                                       expiry_date DATETIME NOT NULL,
                                       user_id BIGINT NOT NULL UNIQUE,
                                       PRIMARY KEY (id),
                                       CONSTRAINT fk_password_reset_token_user
                                           FOREIGN KEY (user_id) REFERENCES users(id)
                                               ON DELETE CASCADE
);