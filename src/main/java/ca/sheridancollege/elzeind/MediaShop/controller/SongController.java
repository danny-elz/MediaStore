package ca.sheridancollege.elzeind.MediaShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class SongController {
    @GetMapping("/secure/songs")
    public String showMovies() {
        // Add logic to retrieve and show songs
        return "secure/songs";
    }
}
