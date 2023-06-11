package retail.service;

import retail.contract.PhoneResponse;
import retail.domain.Phone;

public class PhoneAdapter {
    public static Phone getPhoneFromPhoneResponse(PhoneResponse phoneResponse){
        Phone phone=new Phone(phoneResponse.getPhoneNumber());
        phone.setPhoneType(PhoneTypeAdapter.getPhoneTypeFromPhoneTypeResponse(phoneResponse.getPhoneTypeResponse()));
        return phone;
    }
    public static PhoneResponse getPhoneResponseFromPhone(Phone phone){
        PhoneResponse phoneResponse=new PhoneResponse(phone.getPhoneNumber());
        phoneResponse.setPhoneTypeResponse(PhoneTypeAdapter.getPhoneTypeResponseFromPhoneType(phone.getPhoneType()));
        return phoneResponse;
    }
}




