package retail.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class PhoneType {
    @Id
    @GeneratedValue
    private long Id;
    private String Name;

    public PhoneType(String name) {
        Name = name;
    }

    public PhoneType() {
    }
}
