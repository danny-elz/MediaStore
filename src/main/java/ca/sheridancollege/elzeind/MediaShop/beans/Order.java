package ca.sheridancollege.elzeind.MediaShop.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Date orderDate;
    private BigDecimal totalPrice;
    private String status;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
}
