package retail.domain;


import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class CountryRegion {
    @Id
    @GeneratedValue
    private long Id;
    private String Name;

    public CountryRegion(String name) {
        Name = name;
    }

    public CountryRegion() {
    }
}
