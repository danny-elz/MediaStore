package ca.sheridancollege.elzeind.MediaShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PodcastController {
    @GetMapping("/secure/podcasts")
    public String showPodcasts() {
        // Add logic to retrieve and show podcasts
        return "secure/podcasts";
    }
}
