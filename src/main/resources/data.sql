INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('fahad.jan@sheridancollege.ca', '$2a$10$pLWhptbRF0jg47YhqMNldOr/2..12ToG4CFMyr0u81nZhPBjiYSOO', 1);


INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');


INSERT INTO user_role (userId, roleId)
VALUES (1, 2); -- 1 is the userId for 'fahad.jan@sheridancollege.ca', 3 is the roleId for 'ROLE_ADMIN'