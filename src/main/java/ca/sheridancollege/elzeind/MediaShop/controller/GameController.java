package ca.sheridancollege.elzeind.MediaShop.controller;

import ca.sheridancollege.elzeind.MediaShop.beans.Game;
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
public class GameController {
    @Autowired
    DatabaseAccess da;
    @GetMapping("/secure/games")
    public String showGames() {
        return "secure/games";
    }

    @GetMapping("/admin/admin-games")
    public String index(Model model){
        model.addAttribute("game", new Game());
        model.addAttribute("gameList", da.getGameList());
        return "admin/admin-games";
    }
    @GetMapping("/games")
    public String bookList(Model model) {
        List<Game> games = da.getGameList();
        model.addAttribute("games", games);
        return "admin/admin-games";
    }

    @GetMapping("/editGameById/{id}")
    public String editGameById(Model model, @PathVariable Long id){
        Game game = da.getGameById(id).get(0);
        da.updateGame(game);
        model.addAttribute("game", game);
        model.addAttribute("gameList", da.getGameList());
        return "admin/admin-games";
    }

    @GetMapping("/deleteGameById/{id}")
    public String deleteBookById(Model model, @PathVariable Long id) {
        da.deleteGameById(id);
        model.addAttribute("game", new Game());
        model.addAttribute("gameList", da.getGameList());
        return "/admin/admin-games";
    }

    @PostMapping("/addGame")
    public String insertBook(Model model, @ModelAttribute Game game){
        List<Game> existingBooks = da.getGameById(game.getId());
        if(existingBooks.isEmpty()){
            da.insertGame(game);
        } else {
            da.updateGame(game);
        }
        model.addAttribute("game", new Game());
        model.addAttribute("gameList", da.getGameList());
        return "admin/admin-games";
    }
}
