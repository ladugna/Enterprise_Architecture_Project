package retail.contract;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retail.domain.CountryRegion;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateProvinceResponse {
    private String code;
    private String name;
    private double taxPercent;
    private CountryRegion countryRegion;

    public StateProvinceResponse(String code, String name, double taxPercent) {
       this.code = code;
        this.name = name;
        this.taxPercent = taxPercent;
    }
}
