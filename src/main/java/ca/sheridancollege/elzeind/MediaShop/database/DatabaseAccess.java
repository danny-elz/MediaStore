package ca.sheridancollege.elzeind.MediaShop.database;

import ca.sheridancollege.elzeind.MediaShop.beans.User;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUserList() {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "SELECT * FROM users";
            return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            System.out.println("Error retrieving user list: " + e);
            return null;
        }
    }
    public User findUserAccount(String email) {
        try {
            String query = "SELECT * FROM sec_user WHERE email = :email";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("email", email);
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            // This exception is thrown when the result is empty, i.e., user not found
            return null;
        }
    }
    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId = sec_role.roleId "
                + "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters, String.class);
    }

    public String addUser(String email, String password) {

        try {
            String encryptedPassword = passwordEncoder.encode(password);

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES (:email, :encryptedPassword, 1)";
            namedParameters.addValue("email", email);
            namedParameters.addValue("encryptedPassword", encryptedPassword);

            jdbc.update(query, namedParameters);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() != null) {
                return "EmailExists";
            }
            throw e;

        }
        return null;
    }


    public boolean addRole(Long userId, String roleName) {
        Long roleId = findRoleIdByRoleName(roleName);
        if (roleId == null) {
            System.out.println("Role not found for role name: " + roleName);
            return false;
        }

        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "INSERT INTO user_role (userId, roleId) VALUES (:userId, :roleId)";
            namedParameters.addValue("userId", userId);
            namedParameters.addValue("roleId", roleId);
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
           System.out.println("Error adding role to user: " + e);
            return false;
        }
    }
    public Long findRoleIdByRoleName(String roleName) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "SELECT roleId FROM sec_role WHERE roleName = :roleName";
            namedParameters.addValue("roleName", roleName);
            return jdbc.queryForObject(query, namedParameters, Long.class);
        } catch (Exception e) {
            System.out.println("Error finding role ID by role name: " +  e);
            return null;
        }
    }


}
