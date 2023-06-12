package retail.contract;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retail.domain.PhoneType;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponse {
    private String phoneNumber;

    public PhoneResponse(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private PhoneType phoneType;
}
