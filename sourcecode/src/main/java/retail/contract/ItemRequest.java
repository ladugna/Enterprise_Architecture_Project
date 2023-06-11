package retail.contract;


import lombok.Data;

import java.util.Collection;

@Data
public class ItemRequest {
    private long Id;
    private String Name;
    private String Description;
    private byte[] Image;
    private int BarcodeNumber;
    private double Price;
    private int QuantityInStock;
    private String Category;

    Collection<ItemRequest> subItems;

}
