package retail.domain;


import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue
    private long id;
    private String PhoneNumber;

    public Phone(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Phone() {
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private PhoneType PhoneType;
}
