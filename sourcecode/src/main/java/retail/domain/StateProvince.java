package retail.domain;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class StateProvince {
    @Id
    @GeneratedValue
    private long Id;
    private String Code;
    private String Name;
    private double TaxPercent;

    public StateProvince(String code, String name, double taxPercent) {
        Code = code;
        Name = name;
        TaxPercent = taxPercent;
    }

    public StateProvince() {
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CountryRegion CountryRegion;
}
