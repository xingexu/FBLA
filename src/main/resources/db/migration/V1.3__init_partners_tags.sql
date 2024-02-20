-- Associating partners with tags
INSERT INTO partner_tag (partner_id, tag_id) VALUES
-- Partner 1 associated with tags 1 (Food/Health) and 5 (Tech)
(1, 1),
(1, 5),

-- Partner 2 associated with tags 2 (Shop) and 6 (Donation)
(2, 2),
(2, 6),

-- Partner 3 associated with tags 3 (Club) and 7 (Game)
(3, 3),
(3, 7),

-- Partner 4 associated with tags 4 (Environment) and 8 (Tutor)
(4, 4),
(4, 8),

-- Partner 5 associated with tags 5 (Tech) and 9 (Internship)
(5, 5),
(5, 9),

-- Partner 6 associated with tags 1 (Food/Health) and 3 (Club)
(6, 1),
(6, 3),

-- Partner 7 associated with tags 2 (Shop) and 4 (Environment)
(7, 2),
(7, 4),

-- Partner 8 associated with tags 6 (Donation) and 8 (Tutor)
(8, 6),
(8, 8),

-- Partner 9 associated with tags 7 (Game) and 9 (Internship)
(9, 7),
(9, 9),

-- Partner 10 associated with a variety of tags for a broad reach
(10, 1),
(10, 2),
(10, 3),
(10, 4),
(10, 5),
(10, 6),
(10, 7),
(10, 8),
(10, 9);
