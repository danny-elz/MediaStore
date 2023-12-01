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
    private Long itemId;
    private String itemName;
    private double itemPrice;
    private String itemType;
    private int quantity;

    public CartItem(Long userId, Long itemId, int quantity, String itemType) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemType = itemType;
    }
}

