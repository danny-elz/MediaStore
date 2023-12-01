package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Song {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private int releaseYear;
    private double duration;
    private String album;
    private double price;

}

