package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Podcast {
    private Long id;
    private String title;
    private String host;
    private String genre;
    private int releaseYear;
    private int duration;
    private String language;
}
