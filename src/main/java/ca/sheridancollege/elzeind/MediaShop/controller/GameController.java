package ca.sheridancollege.elzeind.MediaShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class GameController {
    @GetMapping("/secure/games")
    public String showGames() {
        // Add logic to retrieve and show games
        return "secure/games";
    }
}
