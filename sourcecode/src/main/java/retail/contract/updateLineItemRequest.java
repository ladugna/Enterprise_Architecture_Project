package retail.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class updateLineItemRequest {
    long lineId;
    int qty;
}
