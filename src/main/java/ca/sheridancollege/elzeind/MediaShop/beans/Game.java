package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Game {
    private Long id;
    private String title;
    private String genre;
    private String platform;
    private int releaseYear;
    private double price;

}

