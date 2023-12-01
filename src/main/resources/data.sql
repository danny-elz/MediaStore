INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('fahad.jan@sheridancollege.ca', '$2a$10$pLWhptbRF0jg47YhqMNldOr/2..12ToG4CFMyr0u81nZhPBjiYSOO', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('elzeindanny5@gmail.com', '$2a$10$/sDxkoQbV9Qv1unisrZn.eNfr4OYBXWV.nv5ZX/Mk3TOkO6aYReXG', 1);

INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');


INSERT INTO user_role (userId, roleId)
VALUES (1, 2);

INSERT INTO user_role (userId, roleId)
VALUES (2, 1);

INSERT INTO games (title, genre, platform, price, releaseYear) VALUES
                                                                   ('The Legend of Zelda: Breath of the Wild', 'Action-adventure', 'Switch', 59.99, '2017'),
                                                                   ('Super Mario Odyssey', 'Platform', 'Switch', 59.99, '2017'),
                                                                   ('Red Dead Redemption 2', 'Action-adventure', 'PS4', 59.99, '2018'),
                                                                   ('The Witcher 3: Wild Hunt', 'RPG', 'PS4', 39.99, '2015'),
                                                                   ('God of War', 'Action', 'PS4', 49.99, '2018'),
                                                                   ('Overwatch', 'FPS', 'PC', 39.99, '2016'),
                                                                   ('Minecraft', 'Sandbox', 'PC', 26.95, '2011'),
                                                                   ('Fortnite', 'Battle Royale', 'PC', 0.00, '2017'),
                                                                   ('Stardew Valley', 'Simulation', 'PC', 14.99, '2016'),
                                                                   ('Celeste', 'Platform', 'Switch', 19.99, '2018'),
                                                                   ('Hollow Knight', 'Metroidvania', 'PC', 14.99, '2017'),
                                                                   ('Monster Hunter: World', 'Action RPG', 'PS4', 29.99, '2018'),
                                                                   ('Dark Souls III', 'Action RPG', 'PS4', 59.99, '2016'),
                                                                   ('Sekiro: Shadows Die Twice', 'Action-adventure', 'PS4', 59.99, '2019'),
                                                                   ('Animal Crossing: New Horizons', 'Simulation', 'Switch', 59.99, '2020'),
                                                                   ('Doom Eternal', 'FPS', 'PC', 59.99, '2020'),
                                                                   ('Death Stranding', 'Action', 'PS4', 59.99, '2019'),
                                                                   ('Cyberpunk 2077', 'RPG', 'PS4', 59.99, '2020'),
                                                                   ('Resident Evil 2', 'Horror', 'PS4', 59.99, '2019'),
                                                                   ('Genshin Impact', 'Action RPG', 'Mobile', 0.00, '2020');


INSERT INTO songs (title, artist, genre, releaseYear, duration, album, price) VALUES
                                                                                  ('Shape of You', 'Ed Sheeran', 'Pop', 2017, 3.5, '÷ (Divide)', 1.99),
                                                                                  ('Smells Like Teen Spirit', 'Nirvana', 'Rock', 1991, 5.01, 'Nevermind', 2.49),
                                                                                  ('Empire State of Mind', 'Jay-Z', 'Hip-Hop', 2009, 4.36, 'The Blueprint 3', 1.99),
                                                                                  ('Umbrella', 'Rihanna', 'R&B', 2007, 4.36, 'Good Girl Gone Bad', 1.99),
                                                                                  ('Strobe', 'Deadmau5', 'Electronic', 2009, 10.37, 'For Lack of a Better Name', 2.99),
                                                                                  ('Jolene', 'Dolly Parton', 'Country', 1974, 2.41, 'Jolene', 1.49),
                                                                                  ('Firework', 'Katy Perry', 'Pop', 2010, 3.48, 'Teenage Dream', 1.99),
                                                                                  ('Bohemian Rhapsody', 'Queen', 'Rock', 1975, 5.55, 'A Night at the Opera', 2.99),
                                                                                  ('Lose Yourself', 'Eminem', 'Hip-Hop', 2002, 5.26, '8 Mile', 2.49),
                                                                                  ('Fallin', 'Alicia Keys', 'R&B', 2001, 3.30, 'Songs in A Minor', 1.99),
                                                                                  ('Scary Monsters and Nice Sprites', 'Skrillex', 'Electronic', 2010, 4.03, 'Scary Monsters and Nice Sprites', 1.99),
                                                                                  ('Before He Cheats', 'Carrie Underwood', 'Country', 2005, 3.20, 'Some Hearts', 1.49),
                                                                                  ('Happy', 'Pharrell Williams', 'Pop', 2013, 3.53, 'G I R L', 1.99),
                                                                                  ('Enter Sandman', 'Metallica', 'Rock', 1991, 5.32, 'Metallica', 2.49),
                                                                                  ('Gold Digger', 'Kanye West', 'Hip-Hop', 2005, 3.28, 'Late Registration', 1.99),
                                                                                  ('No Scrubs', 'TLC', 'R&B', 1999, 3.38, 'FanMail', 1.99),
                                                                                  ('One More Time', 'Daft Punk', 'Electronic', 2000, 5.20, 'Discovery', 2.99),
                                                                                  ('Wagon Wheel', 'Darius Rucker', 'Country', 2013, 4.58, 'True Believers', 1.49),
                                                                                  ('Rolling in the Deep', 'Adele', 'Pop', 2010, 3.48, '21', 1.99),
                                                                                  ('Back In Black', 'AC/DC', 'Rock', 1980, 4.15, 'Back In Black', 2.49);




INSERT INTO movies (title, director, genre, releaseYear, duration, rating, price) VALUES
                                                                                      ('The Shawshank Redemption', 'Frank Darabont', 'Drama', 1994, 142, 9.3, 4.99),
                                                                                      ('The Godfather', 'Francis Ford Coppola', 'Crime, Drama', 1972, 175, 9.2, 4.99),
                                                                                      ('The Dark Knight', 'Christopher Nolan', 'Action, Crime, Drama', 2008, 152, 9.0, 4.99),
                                                                                      ('The Godfather Part II', 'Francis Ford Coppola', 'Crime, Drama', 1974, 202, 9.0, 4.99),
                                                                                      ('12 Angry Men', 'Sidney Lumet', 'Crime, Drama', 1957, 96, 9.0, 4.99),
                                                                                      ('Schindlers List', 'Steven Spielberg', 'Biography, Drama, History', 1993, 195, 8.9, 4.99),
                                                                                      ('The Lord of the Rings: The Return of the King', 'Peter Jackson', 'Action, Adventure, Drama', 2003, 201, 8.9, 4.99),
                                                                                      ('Pulp Fiction', 'Quentin Tarantino', 'Crime, Drama', 1994, 154, 8.9, 4.99),
                                                                                      ('The Good, the Bad and the Ugly', 'Sergio Leone', 'Western', 1966, 178, 8.8, 4.99),
                                                                                      ('Fight Club', 'David Fincher', 'Drama', 1999, 139, 8.8, 4.99),
                                                                                      ('Forrest Gump', 'Robert Zemeckis', 'Drama, Romance', 1994, 142, 8.8, 4.99),
                                                                                      ('Inception', 'Christopher Nolan', 'Action, Adventure, Sci-Fi', 2010, 148, 8.8, 4.99),
                                                                                      ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 'Action, Adventure, Drama', 2001, 178, 8.8, 4.99),
                                                                                      ('Star Wars: Episode V - The Empire Strikes Back', 'Irvin Kershner', 'Action, Adventure, Fantasy', 1980, 124, 8.7, 4.99),
                                                                                      ('The Lord of the Rings: The Two Towers', 'Peter Jackson', 'Action, Adventure, Drama', 2002, 179, 8.7, 4.99),
                                                                                      ('The Matrix', 'Lana Wachowski, Lilly Wachowski', 'Action, Sci-Fi', 1999, 136, 8.7, 4.99),
                                                                                      ('Goodfellas', 'Martin Scorsese', 'Biography, Crime, Drama', 1990, 146, 8.7, 4.99),
                                                                                      ('One Flew Over the Cuckoos Nest', 'Milos Forman', 'Drama', 1975, 133, 8.7, 4.99),
                                                                                      ('Seven Samurai', 'Akira Kurosawa', 'Action, Adventure, Drama', 1954, 207, 8.6, 4.99),
                                                                                      ('Se7en', 'David Fincher', 'Crime, Drama, Mystery', 1995, 127, 8.6, 4.99);


INSERT INTO podcasts (title, host, genre, releaseYear, duration, language, price) VALUES
                                                                                      ('The Daily Discovery', 'Alex Johnson', 'News', 2018, 30, 'English', 1.99),
                                                                                      ('Tech Trends', 'Samantha Green', 'Technology', 2019, 45, 'English', 1.99),
                                                                                      ('History Uncovered', 'Michael Smith', 'History', 2017, 60, 'English', 1.99),
                                                                                      ('Mystery Hour', 'Laura Jones', 'True Crime', 2020, 50, 'English', 1.99),
                                                                                      ('Global Stories', 'David Lee', 'Culture', 2016, 40, 'English', 1.99),
                                                                                      ('Health Today', 'Rachel Adams', 'Health', 2021, 35, 'English', 1.99),
                                                                                      ('Movie Magic', 'Ethan Brown', 'Movies', 2018, 55, 'English', 1.99),
                                                                                      ('Science Simplified', 'Nora White', 'Science', 2022, 50, 'English', 1.99),
                                                                                      ('All About Art', 'Oliver Martinez', 'Arts', 2019, 40, 'English', 1.99),
                                                                                      ('Business Insights', 'Gary Hughes', 'Business', 2018, 30, 'English', 1.99),
                                                                                      ('Eco Talk', 'Sarah Clark', 'Environment', 2020, 45, 'English', 1.99),
                                                                                      ('Fitness Focus', 'Jake Turner', 'Fitness', 2017, 60, 'English', 1.99),
                                                                                      ('Political Analysis', 'Emma Lopez', 'Politics', 2021, 50, 'English', 1.99),
                                                                                      ('Space Journey', 'Liam Chen', 'Space', 2019, 40, 'English', 1.99),
                                                                                      ('Culinary Corner', 'Sophia Patel', 'Cooking', 2018, 35, 'English', 1.99),
                                                                                      ('Gamers Guild', 'Ryan Kim', 'Gaming', 2022, 55, 'English', 1.99),
                                                                                      ('Music Makers', 'Isabella Garcia', 'Music', 2020, 50, 'English', 1.99);


