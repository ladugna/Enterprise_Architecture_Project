package client.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LineItem {

    private long lineId;
    private Item Item;
    private int Quantity = 1;
    @JsonIgnore
    private String status = "NEW";
    private double DiscountValue;
    private boolean Checked = true;
    private ShoppingCart cart;

}
