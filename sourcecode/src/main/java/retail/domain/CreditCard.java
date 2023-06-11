package retail.domain;


import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue
    private long Id;
    private long Number;
    private LocalDate ExpirationDate;
    private int SecurityCode;

}
