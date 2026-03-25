INSERT INTO users (username, email, password)
VALUES
    (
        'adminuser',
        'admin@example.com',
        '$2a$10$TKzOFW7wZwSTo3zFvuJSGeUYPYM2he7MNVJEvUBVIAsH.UVqbHDq6'
    );

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'adminuser';