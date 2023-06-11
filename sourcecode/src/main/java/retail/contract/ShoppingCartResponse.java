package retail.contract;

import lombok.Data;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;

import java.util.List;
@Data

public class ShoppingCartResponse {
    long cartId;
    List<LineItem> lineItems;
}
