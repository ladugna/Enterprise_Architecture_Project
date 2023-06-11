package retail.service;

import retail.contract.AddressTypeResponse;
import retail.domain.AddressType;

public class AddressTypeAdapter {
    public static AddressType getAddressTypeFromAddressTypeResponse(AddressTypeResponse addressTypeResponse){
        return new AddressType(addressTypeResponse.getName());
    }
    public static AddressTypeResponse getAddressTypeResponseFromAddressType(AddressType addressType){
        return new AddressTypeResponse(addressType.getName());
    }
}
