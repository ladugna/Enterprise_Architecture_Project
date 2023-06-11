package retail.domain;


import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private long Id;
    private String Name;

    public Category(String name) {
        Name = name;
    }
}
