package ca.sheridancollege.elzeind.MediaShop.beans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartItem {
    private Long id;
    private Long userId;
    private Long bookId;
    private int quantity;
    private String bookName;
    private double bookPrice;

    public CartItem(Long userId, Long bookId, int quantity) {
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }
}

