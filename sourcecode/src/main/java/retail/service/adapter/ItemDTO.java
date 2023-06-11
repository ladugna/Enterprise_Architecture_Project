package retail.service.adapter;




import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@NoArgsConstructor
public class ItemDTO {
    private long Id;
    private String Name;
    private String Description;
    private byte[] Image;
    private int BarcodeNumber;
    private double Price;
    private int QuantityInStock;
    private String Category;

    Collection<ItemDTO> subItems;
}
