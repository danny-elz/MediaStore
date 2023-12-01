package ca.sheridancollege.elzeind.MediaShop.controller;

import ca.sheridancollege.elzeind.MediaShop.beans.Movie;
import ca.sheridancollege.elzeind.MediaShop.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    DatabaseAccess da;
    @GetMapping("/secure/movies")
    public String showMovies() {
        // Add logic to retrieve and show movies
        return "secure/movies";
    }
    @GetMapping("/admin/admin-movies")
    public String index (Model model){
        model.addAttribute("movie", new Movie());
        model.addAttribute("movieList", da.getMovieList());
        return "admin/admin-movies";
    }
    @GetMapping("/movies")
    public String movieList(Model model){
        List<Movie> movies = da.getMovieList();
        model.addAttribute("movies", movies);
        return "admin/admin-movies";
    }

    @GetMapping("/editMovieById/{id}")
    public String editMovieById(Model model, @PathVariable Long id){
        Movie movie = da.getMovieById(id).get(0);
        da.updateMovie(movie);
        model.addAttribute("movie", movie);
        model.addAttribute("movieList", da.getMovieList());
        return "admin/admin-movies";
    }

    @GetMapping("/deleteMovieById/{id}")
    public String deleteMovieById(Model model, @PathVariable Long id) {
        da.deleteMovieById(id);
        model.addAttribute("movie", new Movie());
        model.addAttribute("movieList", da.getMovieList());
        return "admin/admin-movies";
    }

    @PostMapping("/addMovie")
    public String addMovie(Model model, @ModelAttribute Movie movie){
        List<Movie> existingMovies = da.getMovieById(movie.getId());
        if(existingMovies.isEmpty()){
            da.insertMovie(movie);
        } else {
            da.updateMovie(movie);
        }
        model.addAttribute("movie", new Movie());
        model.addAttribute("movieList", da.getMovieList());
        return "admin/admin-movies";
    }


}
