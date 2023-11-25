package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;

@Data
public class Movie {
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private double duration;
    private double rating;

}

