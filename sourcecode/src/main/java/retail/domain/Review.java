package retail.domain;


import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private long Id;
    private String Title;
    private int NumberOfStars;
    private LocalDateTime Date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;
    @ManyToOne
    private Item item;

    public Review(String title, int numberOfStars, LocalDateTime date) {
        Title = title;
        NumberOfStars = numberOfStars;
        Date = date;
    }
}
