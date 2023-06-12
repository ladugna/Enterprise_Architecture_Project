package retail.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retail.domain.AddressType;
import retail.domain.Phone;
import retail.domain.StateProvince;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private String addressLine;
    private String aptNo;
    private String city;
    private String postalCode;
    private StateProvince stateProvince;
    private Phone phone;
    private AddressType addressType;


    public AddressResponse(String addressLine, String aptNo, String city, String postalCode) {
        this.addressLine = addressLine;
        this.aptNo = aptNo;
        this.city = city;
        this.postalCode = postalCode;

    }

}
