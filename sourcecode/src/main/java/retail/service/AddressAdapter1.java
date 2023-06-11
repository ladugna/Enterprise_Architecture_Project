package retail.service;

import retail.contract.AddressResponse;
import retail.domain.Address;

public class AddressAdapter1 {
    public static Address getAddressFromAddressResponse(AddressResponse addressResponse){
        Address address=new Address();
        address.setAddressLine(addressResponse.getAddressLine());
        address.setAptNo(addressResponse.getAptNo());
        address.setCity(addressResponse.getCity());
        address.setPostalCode(addressResponse.getPostalCode());
       // address.setAddressType(addressResponse.getAddressTypeResponse().getName());
        address.setAddressType(AddressTypeAdapter.getAddressTypeFromAddressTypeResponse(addressResponse.getAddressTypeResponse()));
        address.setStateProvince(StateProvinceAdapter.getStateProvinceFromStateProvinceResponse(addressResponse.getStateProvinceResponse()));
        address.getStateProvince().setCountryRegion(CountryRegionAdapter.getCountryRegionFromCountryRegionResponse(addressResponse.
                getStateProvinceResponse().getCountryRegionResponse()));
      //  StateProvince stateProvince=new StateProvince(addressResponse.)
        address.setPhone(PhoneAdapter.getPhoneFromPhoneResponse(addressResponse.getPhoneResponse()));
        address.getPhone().setPhoneType(PhoneTypeAdapter.getPhoneTypeFromPhoneTypeResponse(addressResponse
                .getPhoneTypeResponse()));
        return address;
    }
    public static AddressResponse getAddressResponseFromAddress(Address address){
        AddressResponse addressResponse= new AddressResponse(address.getAddressLine(), address.getAptNo(), address.getCity(), address.getPostalCode());
        addressResponse.setAddressTypeResponse(AddressTypeAdapter.getAddressTypeResponseFromAddressType(address.getAddressType()));
        addressResponse.setStateProvinceResponse(StateProvinceAdapter.getStateProvinceResponseFromStateProvince(address.getStateProvince()));
        addressResponse.getStateProvinceResponse().setCountryRegionResponse(CountryRegionAdapter.getCountryRegionResponseFromCountryRegion(
                address.getStateProvince().getCountryRegion()));
        addressResponse.setPhoneResponse(PhoneAdapter.getPhoneResponseFromPhone(address.getPhone()));
        addressResponse.getPhoneResponse().setPhoneTypeResponse(PhoneTypeAdapter.getPhoneTypeResponseFromPhoneType(
                address.getPhone().getPhoneType()
        ));
        return addressResponse;
    }
}
