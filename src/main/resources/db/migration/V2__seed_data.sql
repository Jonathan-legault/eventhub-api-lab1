INSERT INTO categories (name) VALUES
                                  ('Conference'),
                                  ('Workshop'),
                                  ('Concert'),
                                  ('Business'),
                                  ('Gaming'),
                                  ('Meetup');

INSERT INTO events (name, description, ticket_price, category_id, active, event_date, created_at, updated_at)
VALUES
    (
        'Spring Boot Conference',
        'Developer conference for Spring Boot.',
        150.00,
        (SELECT id FROM categories WHERE name = 'Conference'),
        b'1',
        '2026-06-01 10:00:00',
        NOW(),
        NOW()
    ),
    (
        'Spring Boot Hands-On Workshop',
        'Practical workshop on building REST APIs with Spring Boot.',
        75.00,
        (SELECT id FROM categories WHERE name = 'Workshop'),
        b'1',
        '2026-05-10 13:00:00',
        NOW(),
        NOW()
    ),
    (
        'Summer Rock Festival',
        'Outdoor rock concert featuring multiple bands.',
        120.00,
        (SELECT id FROM categories WHERE name = 'Concert'),
        b'1',
        '2026-07-20 18:30:00',
        NOW(),
        NOW()
    ),
    (
        'Global Tech Expo',
        'Annual technology exhibition showcasing the latest innovations.',
        180.00,
        (SELECT id FROM categories WHERE name = 'Conference'),
        b'1',
        '2026-08-12 09:30:00',
        NOW(),
        NOW()
    ),
    (
        'Indie Night Live',
        'Live indie music performances from upcoming artists.',
        65.00,
        (SELECT id FROM categories WHERE name = 'Concert'),
        b'1',
        '2026-04-18 20:00:00',
        NOW(),
        NOW()
    ),
    (
        'Full Stack Coding Bootcamp',
        'Intensive workshop covering modern full-stack development.',
        95.00,
        (SELECT id FROM categories WHERE name = 'Workshop'),
        b'1',
        '2026-05-25 10:00:00',
        NOW(),
        NOW()
    ),
    (
        'Startup Pitch Day',
        'Entrepreneurs pitch their startups to investors.',
        50.00,
        (SELECT id FROM categories WHERE name = 'Business'),
        b'1',
        '2026-09-03 14:00:00',
        NOW(),
        NOW()
    ),
    (
        'Gaming Championship',
        'Competitive gaming tournament with multiple titles.',
        110.00,
        (SELECT id FROM categories WHERE name = 'Gaming'),
        b'1',
        '2026-06-22 16:00:00',
        NOW(),
        NOW()
    ),
    (
        'Electronic Dance Festival',
        'Large EDM festival featuring international DJs.',
        200.00,
        (SELECT id FROM categories WHERE name = 'Concert'),
        b'1',
        '2026-07-30 19:00:00',
        NOW(),
        NOW()
    ),
    (
        'Local Developer Meetup',
        'Networking event for software developers.',
        20.00,
        (SELECT id FROM categories WHERE name = 'Meetup'),
        b'1',
        '2026-03-28 18:00:00',
        NOW(),
        NOW()
    ),
    (
        'Data Science with Python',
        'Hands-on workshop for machine learning and data analysis.',
        130.00,
        (SELECT id FROM categories WHERE name = 'Workshop'),
        b'1',
        '2026-10-10 11:00:00',
        NOW(),
        NOW()
    );