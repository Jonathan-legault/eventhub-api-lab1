INSERT INTO users (username, email, password)
VALUES
    ('testuser', 'testuser@example.com', '2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824');

INSERT INTO registrations (registration_date, user_id)
VALUES
    (
        '2026-03-22 11:00:00',
        (SELECT id FROM users WHERE username = 'testuser')
    );

INSERT INTO registration_items (quantity, registration_id, event_id)
VALUES
    (
        2,
        (SELECT id FROM registrations WHERE user_id = (SELECT id FROM users WHERE username = 'testuser') ORDER BY id ASC LIMIT 1),
    (SELECT id FROM events WHERE name = 'Spring Boot Conference' ORDER BY id ASC LIMIT 1)
    );