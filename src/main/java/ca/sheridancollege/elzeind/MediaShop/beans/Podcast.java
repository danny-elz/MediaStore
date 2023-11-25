package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;

@Data
public class Podcast {
    private String title;
    private String host;
    private String genre;
    private int releaseYear;
    private double duration;
    private String language;

}
