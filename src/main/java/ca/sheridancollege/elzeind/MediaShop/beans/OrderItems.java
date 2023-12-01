package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class OrderItems {
    Long id;
    Long orderId;
    Long itemId;
    String itemType;
    int quantity;
    double price;

}
