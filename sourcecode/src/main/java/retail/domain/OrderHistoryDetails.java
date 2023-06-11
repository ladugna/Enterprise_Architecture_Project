package retail.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDetails {
    @Id
    @GeneratedValue
    private long Id;
    private String Name;
    private String Description;
    private double Price;
    private double DiscountValue;
    private int Quantity;

    public OrderHistoryDetails(String name, String description, double price, double discountValue, int quantity) {
        Name = name;
        Description = description;
        Price = price;
        DiscountValue = discountValue;
        Quantity = quantity;
    }
}
