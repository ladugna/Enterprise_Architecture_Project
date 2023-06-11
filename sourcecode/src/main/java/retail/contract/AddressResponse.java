package retail.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retail.domain.AddressType;
import retail.domain.Phone;
import retail.domain.StateProvince;
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponse {
    private String addressLine;
    private String aptNo;
    private String city;
    private String postalCode;
    private PhoneResponse phoneResponse;
    private StateProvinceResponse stateProvinceResponse;
    private PhoneTypeResponse phoneTypeResponse;
    private AddressTypeResponse addressTypeResponse;


    public AddressResponse(String addressLine, String aptNo, String city, String postalCode) {
        this.addressLine = addressLine;
        this.aptNo = aptNo;
        this.city = city;
        this.postalCode = postalCode;

    }

}
