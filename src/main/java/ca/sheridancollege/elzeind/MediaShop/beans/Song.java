package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;

@Data
public class Song {
    private String title;
    private String artist;
    private String genre;
    private int releaseYear;
    private double duration;
    private String album;

}

