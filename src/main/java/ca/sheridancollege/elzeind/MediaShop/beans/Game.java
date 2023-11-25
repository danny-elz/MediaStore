package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Game {
    private String title;
    private String genre;
    private String platform;
    private double price;
    private int releaseYear;

}

