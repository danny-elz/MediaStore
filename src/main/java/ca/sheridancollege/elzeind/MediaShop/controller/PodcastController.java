package ca.sheridancollege.elzeind.MediaShop.controller;

import ca.sheridancollege.elzeind.MediaShop.beans.Movie;
import ca.sheridancollege.elzeind.MediaShop.beans.Order;
import ca.sheridancollege.elzeind.MediaShop.beans.Podcast;
import ca.sheridancollege.elzeind.MediaShop.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class PodcastController {
    @Autowired
    DatabaseAccess da;
    @GetMapping("/secure/podcasts")
    public String showPodcasts(Model model) {
        model.addAttribute("podcast", new Podcast());
        model.addAttribute("podcastList", da.getPodcastList());
        return "secure/podcasts";
    }
    @GetMapping("/admin/admin-podcasts")
    public String index(org.springframework.ui.Model model) {
        model.addAttribute("podcast", new Podcast());
        model.addAttribute("podcastList", da.getPodcastList());
        return "admin/admin-podcasts";
    }
    @GetMapping("/podcasts")
    public String podcastList(org.springframework.ui.Model model) {
        List<Podcast> podcasts = da.getPodcastList();
        model.addAttribute("podcasts", podcasts);
        return "admin/admin-podcasts";
    }
    @GetMapping("/editPodcastById/{id}")
    public String updatePodcastById(org.springframework.ui.Model model, @PathVariable Long id) {
        Podcast podcast = da.getPodcastById(id).get(0);
        da.updatePodcast(podcast);
        model.addAttribute("podcast", podcast);
        model.addAttribute("podcastList", da.getPodcastList());
        return "admin/admin-podcasts";
    }
    @GetMapping("/deletePodcastById/{id}")
    public String deletePodcastById(org.springframework.ui.Model model, @PathVariable Long id) {
        da.deletePodcastById(id);
        model.addAttribute("podcast", new Podcast());
        model.addAttribute("podcastList", da.getPodcastList());
        return "admin/admin-podcasts";
    }

    @PostMapping("/addPodcast")
    public String addPodcast(Model model, @ModelAttribute Podcast podcast){
        List<Podcast> existingPodcasts = da.getPodcastById(podcast.getId());
        if(existingPodcasts.isEmpty()){
            da.insertPodcast(podcast);
        } else {
            da.updatePodcast(podcast);
        }
        model.addAttribute("podcast", new Podcast());
        model.addAttribute("podcastList", da.getPodcastList());
        return "admin/admin-podcasts";
    }

    @GetMapping("/admin/view-orders")
    public String viewOrders(Model model) {
        List<Order> orders = da.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/view-orders";
    }

}
