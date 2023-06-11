package retail.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class LineItem {
    @Id
    @GeneratedValue
    private long lineId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Item Item;
    private int Quantity = 1;
    @JsonIgnore
    private String status = "NEW";
    private double DiscountValue;
    private boolean Checked = true;
    @ManyToOne
    @JsonIgnore
    private ShoppingCart cart;

}
