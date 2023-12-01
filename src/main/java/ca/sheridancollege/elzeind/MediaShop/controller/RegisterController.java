package ca.sheridancollege.elzeind.MediaShop.controller;

import ca.sheridancollege.elzeind.MediaShop.beans.User;
import ca.sheridancollege.elzeind.MediaShop.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private DatabaseAccess da;

    @GetMapping("/register")
    public String getRegister () {
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(Model model, @RequestParam String email, @RequestParam String password) {
        String result = da.addUser(email, password);

        if ("EmailExists".equals(result)) {
            model.addAttribute("errorMessage", "User with this email already exists.");
            return "register";
        }

        User newUser = da.findUserAccount(email);
        if (newUser != null) {
            boolean roleAdded = da.addRole(newUser.getUserId(), "ROLE_USER");
            if (!roleAdded) {
                return "redirect:/registration-failed";
            }
            return "redirect:/login";
        } else {
            return "redirect:/registration-failed";
        }
    }
}