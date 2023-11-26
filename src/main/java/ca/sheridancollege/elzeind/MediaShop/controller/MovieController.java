package ca.sheridancollege.elzeind.MediaShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MovieController {
    @GetMapping("/secure/movies")
    public String showMovies() {
        // Add logic to retrieve and show movies
        return "secure/movies";
    }
}
