package client.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddItemToCartRequest {
    int barcodeNumber;
    int qty;

    public AddItemToCartRequest(int barcodeNumber, int qty) {
        this.barcodeNumber = barcodeNumber;
        this.qty = qty;
    }
}
