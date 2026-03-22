CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT uk_users_username UNIQUE (username),
                       CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE categories (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id),
                            CONSTRAINT uk_categories_name UNIQUE (name)
);

CREATE TABLE events (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        description TEXT,
                        ticket_price DECIMAL(10,2) NOT NULL,
                        category_id BIGINT NOT NULL,
                        active BIT(1) NOT NULL,
                        event_date DATETIME(6) NOT NULL,
                        created_at DATETIME(6),
                        updated_at DATETIME(6),
                        PRIMARY KEY (id),
                        CONSTRAINT fk_events_category FOREIGN KEY (category_id) REFERENCES categories(id),
                        CONSTRAINT uk_events_name_date UNIQUE (name, event_date)
);

CREATE TABLE registrations (
                               id BIGINT NOT NULL AUTO_INCREMENT,
                               registration_date DATETIME(6) NOT NULL,
                               user_id BIGINT NOT NULL,
                               PRIMARY KEY (id),
                               CONSTRAINT fk_registrations_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE registration_items (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    quantity INT NOT NULL,
                                    registration_id BIGINT NOT NULL,
                                    event_id BIGINT NOT NULL,
                                    PRIMARY KEY (id),
                                    CONSTRAINT fk_registration_items_registration FOREIGN KEY (registration_id) REFERENCES registrations(id),
                                    CONSTRAINT fk_registration_items_event FOREIGN KEY (event_id) REFERENCES events(id)
);