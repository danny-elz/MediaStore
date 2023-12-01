package ca.sheridancollege.elzeind.MediaShop.database;

import ca.sheridancollege.elzeind.MediaShop.beans.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.List;
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

    public boolean insertGame(Game game) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "INSERT INTO games (title, genre, platform, price, releaseYear) VALUES (:title, :genre, :platform, :price, :releaseYear)";
            namedParameters.addValue("title", game.getTitle());
            namedParameters.addValue("genre", game.getGenre());
            namedParameters.addValue("platform", game.getPlatform());
            namedParameters.addValue("price", game.getPrice());
            namedParameters.addValue("releaseYear", game.getReleaseYear());
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error inserting game: " + e);
            return false;
        }
    }

    public boolean insertMovie(Movie movie) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "INSERT INTO movies (title, director, genre, releaseYear, duration, rating) VALUES (:title, :director, :genre, :releaseYear, :duration, :rating)";
            namedParameters.addValue("title", movie.getTitle());
            namedParameters.addValue("director", movie.getDirector());
            namedParameters.addValue("genre", movie.getGenre());
            namedParameters.addValue("releaseYear", movie.getReleaseYear());
            namedParameters.addValue("duration", movie.getDuration());
            namedParameters.addValue("rating", movie.getRating());
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error inserting movie: " + e);
            return false;
        }
    }

    public boolean insertPodcast(Podcast podcast) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "INSERT INTO podcasts (title, host, genre, releaseYear, duration, language) VALUES (:title, :host, :genre, :releaseYear, :duration, :language)";
            namedParameters.addValue("title", podcast.getTitle());
            namedParameters.addValue("host", podcast.getHost());
            namedParameters.addValue("genre", podcast.getGenre());
            namedParameters.addValue("releaseYear", podcast.getReleaseYear());
            namedParameters.addValue("duration", podcast.getDuration());
            namedParameters.addValue("language", podcast.getLanguage());
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error inserting podcast: " + e);
            return false;
        }
    }

    public boolean insertSong(Song song) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "INSERT INTO songs (title, artist, genre, releaseYear, duration, album) VALUES (:title, :artist, :genre, :releaseYear, :duration, :album)";
            namedParameters.addValue("title", song.getTitle());
            namedParameters.addValue("artist", song.getArtist());
            namedParameters.addValue("genre", song.getGenre());
            namedParameters.addValue("releaseYear", song.getReleaseYear());
            namedParameters.addValue("duration", song.getDuration());
            namedParameters.addValue("album", song.getAlbum());
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error inserting song: " + e);
            return false;
        }
    }

    public User findUserAccount(String email) {
        try {
            String query = "SELECT * FROM sec_user WHERE email = :email";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("email", email);
            return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
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
            System.out.println("Error finding role ID by role name: " + e);
            return null;
        }
    }

    public boolean deleteGameById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "DELETE from games WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error deleting book: " + e);
            return false;
        }
    }

    public List<Game> getGameById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "SELECT * FROM games WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Game.class));
        } catch (Exception e) {
            System.out.println("Error retrieving book by id: " + e);
            return null;
        }
    }

    public boolean updateGame(Game updatedGame) {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "UPDATE games SET title = :title, genre = :genre, platform = :platform, price = :price, releaseYear = :releaseYear  WHERE id = :id";
            namedParameter.addValue("title", updatedGame.getTitle());
            namedParameter.addValue("genre", updatedGame.getGenre());
            namedParameter.addValue("platform", updatedGame.getPlatform());
            namedParameter.addValue("price", updatedGame.getPrice());
            namedParameter.addValue("releaseYear", updatedGame.getReleaseYear());
            namedParameter.addValue("id", updatedGame.getId());
            return jdbc.update(query, namedParameter) > 0;
        } catch (Exception e) {
            System.out.println("Error updating Game: " + e);
            return false;
        }
    }

    public List<Game> getGameList() {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "SELECT * FROM games";
            return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Game.class));
        } catch (Exception e) {
            System.out.println("Error retrieving book list: " + e);
            return null;
        }
    }

    public boolean deleteSongById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "DELETE FROM songs WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error deleting song: " + e);
            return false;
        }
    }

    public List<Song> getSongById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "SELECT * FROM songs WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Song.class));
        } catch (Exception e) {
            System.out.println("Error retrieving song by id: " + e);
            return null;
        }
    }

    public boolean updateSong(Song updatedSong) {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "UPDATE songs SET title = :title, artist = :artist, genre = :genre, releaseYear = :releaseYear, duration = :duration, album = :album WHERE id = :id";
            namedParameter.addValue("title", updatedSong.getTitle());
            namedParameter.addValue("artist", updatedSong.getArtist());
            namedParameter.addValue("genre", updatedSong.getGenre());
            namedParameter.addValue("releaseYear", updatedSong.getReleaseYear());
            namedParameter.addValue("duration", updatedSong.getDuration());
            namedParameter.addValue("album", updatedSong.getAlbum());
            namedParameter.addValue("id", updatedSong.getId());
            return jdbc.update(query, namedParameter) > 0;
        } catch (Exception e) {
            System.out.println("Error updating song: " + e);
            return false;
        }
    }

    public List<Song> getSongList() {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "SELECT * FROM songs";
            return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Song.class));
        } catch (Exception e) {
            System.out.println("Error retrieving song list: " + e);
            return null;
        }
    }


    public boolean deleteMovieById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "DELETE FROM movies WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error deleting movie: " + e);
            return false;
        }
    }

    public List<Movie> getMovieById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "SELECT * FROM movies WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Movie.class));
        } catch (Exception e) {
            System.out.println("Error retrieving movie by id: " + e);
            return null;
        }
    }

    public boolean updateMovie(Movie updatedMovie) {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "UPDATE movies SET title = :title, director = :director, genre = :genre, releaseYear = :releaseYear, duration = :duration, rating = :rating WHERE id = :id";
            // Add parameters
            namedParameter.addValue("title", updatedMovie.getTitle());
            namedParameter.addValue("director", updatedMovie.getDirector());
            namedParameter.addValue("genre", updatedMovie.getGenre());
            namedParameter.addValue("releaseYear", updatedMovie.getReleaseYear());
            namedParameter.addValue("duration", updatedMovie.getDuration());
            namedParameter.addValue("rating", updatedMovie.getRating());
            namedParameter.addValue("id", updatedMovie.getId());
            return jdbc.update(query, namedParameter) > 0;
        } catch (Exception e) {
            System.out.println("Error updating movie: " + e);
            return false;
        }
    }
    public List<Movie> getMovieList() {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "SELECT * FROM movies";
            return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Movie.class));
        } catch (Exception e) {
            System.out.println("Error retrieving movie list: " + e);
            return null;
        }
    }

    public boolean deletePodcastById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "DELETE FROM podcasts WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.update(query, namedParameters) > 0;
        } catch (Exception e) {
            System.out.println("Error deleting podcast: " + e);
            return false;
        }
    }
    public List<Podcast> getPodcastById(Long id) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            String query = "SELECT * FROM podcasts WHERE id = :id";
            namedParameters.addValue("id", id);
            return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Podcast.class));
        } catch (Exception e) {
            System.out.println("Error retrieving podcast by id: " + e);
            return null;
        }
    }
    public boolean updatePodcast(Podcast updatedPodcast) {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "UPDATE podcasts SET title = :title, host = :host, genre = :genre, releaseYear = :releaseYear, duration = :duration, language = :language WHERE id = :id";
            namedParameter.addValue("title", updatedPodcast.getTitle());
            namedParameter.addValue("host", updatedPodcast.getHost());
            namedParameter.addValue("genre", updatedPodcast.getGenre());
            namedParameter.addValue("releaseYear", updatedPodcast.getReleaseYear());
            namedParameter.addValue("duration", updatedPodcast.getDuration());
            namedParameter.addValue("language", updatedPodcast.getLanguage());
            namedParameter.addValue("id", updatedPodcast.getId());
            return jdbc.update(query, namedParameter) > 0;
        } catch (Exception e) {
            System.out.println("Error updating podcast: " + e);
            return false;
        }
    }
    public List<Podcast> getPodcastList() {
        try {
            MapSqlParameterSource namedParameter = new MapSqlParameterSource();
            String query = "SELECT * FROM podcasts";
            return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Podcast.class));
        } catch (Exception e) {
            System.out.println("Error retrieving podcast list: " + e);
            return null;
        }
    }




}





