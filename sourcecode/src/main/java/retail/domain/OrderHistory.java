package retail.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retail.constant.OrderHistoryState;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
    @Id
    @GeneratedValue
    private long Id;
    private double TotalPrice;
    @Enumerated(EnumType.STRING)
    private OrderHistoryState Status = OrderHistoryState.PLACED;
    private LocalDateTime DateOrdered = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_history_id")
    private Collection<OrderHistoryDetails> orderHistoryDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="addressLine1", column = @Column(name = "bill_addressLine1")),
            @AttributeOverride(name="addressLine2", column = @Column(name = "bill_addressLine2")),
            @AttributeOverride(name="city", column = @Column(name = "bill_city")),
            @AttributeOverride(name="state", column = @Column(name = "bill_state")),
            @AttributeOverride(name="country", column = @Column(name = "bill_country")),
            @AttributeOverride(name="postalCode", column = @Column(name = "bill_postalCode")),
            @AttributeOverride(name="phoneNumber", column = @Column(name = "bill_phoneNumber"))
    })
    private AddressEmbed BillingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="addressLine1", column = @Column(name = "ship_addressLine1")),
            @AttributeOverride(name="addressLine2", column = @Column(name = "ship_addressLine2")),
            @AttributeOverride(name="city", column = @Column(name = "ship_city")),
            @AttributeOverride(name="state", column = @Column(name = "ship_state")),
            @AttributeOverride(name="country", column = @Column(name = "ship_country")),
            @AttributeOverride(name="postalCode", column = @Column(name = "ship_postalCode")),
            @AttributeOverride(name="phoneNumber", column = @Column(name = "ship_phoneNumber"))
    })
    private AddressEmbed ShippingAddress;

    private String PaymentMethods;
    private double Subtotal;
    private double ShippingAndHandling;
    private double TaxAmount;
}
