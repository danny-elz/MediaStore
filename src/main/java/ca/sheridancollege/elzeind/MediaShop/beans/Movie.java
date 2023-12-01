package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Movie {
    private Long id;
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private int duration;
    private double rating;
    private double price;

}

