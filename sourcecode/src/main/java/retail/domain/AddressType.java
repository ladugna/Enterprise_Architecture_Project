package retail.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AddressType {
    @Id
    @GeneratedValue
    private long Id;
    private String Name;

    public AddressType(String name) {
        Name = name;
    }

    public AddressType() {
    }
}
