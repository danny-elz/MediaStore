CREATE TABLE sec_user (userId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(75) NOT NULL UNIQUE,
                       encryptedPassword VARCHAR(128) NOT NULL,
                       enabled BIT NOT NULL
);

CREATE TABLE games (id LONG PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR (255),
                    genre VARCHAR (255),
                    platform VARCHAR (255),
                    price DECIMAL (4,2),
                    releaseYear VARCHAR (255));


CREATE TABLE movies(id LONG PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR (255),
                    director VARCHAR (255),
                    genre VARCHAR (255),
                    releaseYear INT,
                    duration INT,
                    rating DECIMAL (2,1));

CREATE TABLE podcasts(id LONG PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR (255),
                      host VARCHAR(255),
                      genre VARCHAR (255),
                      releaseYear INT,
                      duration INT,
                      language VARCHAR (255));

CREATE TABLE songs (id LONG PRIMARY KEY  AUTO_INCREMENT,
                    title VARCHAR(255),
                    artist VARCHAR (255),
                    genre VARCHAR (255),
                    releaseYear INT,
                    duration DECIMAL(4,2),
                    album VARCHAR (255));


CREATE TABLE cart (id LONG PRIMARY KEY AUTO_INCREMENT,
                   userId LONG,
                   bookId LONG,
                   quantity int);



CREATE TABLE sec_role(roleId   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role( id     BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        userId BIGINT NOT NULL,
                        roleId BIGINT NOT NULL
);


ALTER TABLE user_role
    ADD CONSTRAINT user_role_uk UNIQUE (userId, roleId);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_fk1 FOREIGN KEY (userId)
        REFERENCES sec_user (userId);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_fk2 FOREIGN KEY (roleId)
        REFERENCES sec_role (roleId);


ALTER TABLE cart
    ADD CONSTRAINT cart_fk1 FOREIGN KEY (userId)
        REFERENCES sec_user (userId);

/*ALTER TABLE cart
    ADD CONSTRAINT cart_fk2 FOREIGN KEY (bookId)
        REFERENCES books (id);

 */