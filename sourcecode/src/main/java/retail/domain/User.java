package retail.domain;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private long Id;
    private String Username;
    private String Email;
    private String Password;
    private String PasswordSalt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer Customer;
}
