package ca.sheridancollege.elzeind.MediaShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping ("/secure/home")
    public String home(Model model){
        return "secure/home";
    }
    @GetMapping("/admin/admin-home")
    public String adminHome(Model model){
        return "admin/admin-home";
    }
}
