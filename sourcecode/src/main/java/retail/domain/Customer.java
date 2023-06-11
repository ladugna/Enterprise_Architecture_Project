package retail.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="Customer")
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String passwordHash;
    private String passwordSalt;
    @Column(nullable = false)
    private String email;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ShoppingCart Cart = new ShoppingCart();
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address BillingAddressId;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address PreferredShippingAddressId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Address> ShippingAddresses;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Collection<CreditCard> CreditCards;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Collection<OrderHistory> OrderHistories;
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;
/*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CustomerItemReview",
            joinColumns = @JoinColumn(name="CustomerID"),
            inverseJoinColumns = @JoinColumn(name="ReviewID"))
    private Collection<Review> Reviews = new ArrayList<>();*/

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    public Customer(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        //username = "user_"+firstName+"_"+lastName;
    }
    public Customer(){super();};

    /*public void addReview(Review review){
        this.Reviews.add(review);
    }*/



}
