package retail.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class createLineItemRequest {

    int barcodeNumber;
    int qty;
}
