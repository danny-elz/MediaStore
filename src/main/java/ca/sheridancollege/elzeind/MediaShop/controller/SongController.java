package ca.sheridancollege.elzeind.MediaShop.controller;

import ca.sheridancollege.elzeind.MediaShop.beans.Song;
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
public class SongController {
    @Autowired
    DatabaseAccess da;
    @GetMapping("/secure/songs")
    public String showSongs(Model model) {
        model.addAttribute("songs", new Song());
        model.addAttribute("songList", da.getSongList());
        return "secure/songs";
    }
    @GetMapping("/admin/admin-songs")
    public String index(Model model) {
        List<Song> songList = da.getSongList();

        if (songList != null && !songList.isEmpty()) {
            model.addAttribute("songList", songList);
        }
        model.addAttribute("song", new Song());
        return "admin/admin-songs";
    }

    @GetMapping("/songs")
    public String songList(Model model) {
        List<Song> songs = da.getSongList();
        model.addAttribute("songs", songs);
        return "admin/admin-songs";
    }
    @GetMapping("/editSongById/{id}")
    public String editSongById(Model model, @PathVariable Long id){
        Song song = da.getSongById(id).get(0);
        da.updateSong(song);
        model.addAttribute("song", song);
        model.addAttribute("songList", da.getSongList());
        return "admin/admin-songs";
    }
    @GetMapping("/deleteSongById/{id}")
    public String deleteSongById(Model model, @PathVariable Long id) {
        da.deleteSongById(id);
        model.addAttribute("song", new Song());
        model.addAttribute("songList", da.getSongList());
        return "admin/admin-songs";
    }
    @PostMapping("/addSong")
    public String insertSong(Model model, @ModelAttribute Song song){
        List<Song> existingSongs = da.getSongById(song.getId());
        if(existingSongs.isEmpty()){
            da.insertSong(song);
        } else {
            da.updateSong(song);
        }
        model.addAttribute("song", new Song());
        model.addAttribute("songList", da.getSongList());
        return "admin/admin-songs";
    }
}
