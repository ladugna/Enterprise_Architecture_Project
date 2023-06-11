package retail.service;

import retail.contract.PhoneTypeResponse;
import retail.domain.PhoneType;

public class PhoneTypeAdapter {
    public static PhoneType getPhoneTypeFromPhoneTypeResponse(PhoneTypeResponse phoneTypeResponse){
        return new PhoneType(phoneTypeResponse.getName());
    }
    public static PhoneTypeResponse getPhoneTypeResponseFromPhoneType(PhoneType phoneType){
        return new PhoneTypeResponse(phoneType.getName());
    }
}
