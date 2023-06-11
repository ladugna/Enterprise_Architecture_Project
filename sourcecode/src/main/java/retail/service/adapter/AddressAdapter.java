package retail.service.adapter;

import retail.domain.Address;
import retail.domain.AddressEmbed;
import retail.domain.Category;

public class AddressAdapter {
    public static AddressEmbed getCategoryDTOFromCategory(Address address){
        AddressEmbed temp = new AddressEmbed();
        temp.setCity(address.getCity());
        temp.setState(address.getStateProvince().getName());
        temp.setAddressLine1(address.getAddressLine());
        temp.setAddressLine2(address.getAptNo());
        temp.setPhoneNumber(address.getPhone().getPhoneNumber());
        temp.setCountry(address.getStateProvince().getCountryRegion().getName());
        return temp;
    }
}
