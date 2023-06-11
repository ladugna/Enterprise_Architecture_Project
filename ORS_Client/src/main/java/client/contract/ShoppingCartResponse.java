package client.contract;

import client.Domain.LineItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ShoppingCartResponse {
    long cartId;
    List<LineItem> lineItems;
}
