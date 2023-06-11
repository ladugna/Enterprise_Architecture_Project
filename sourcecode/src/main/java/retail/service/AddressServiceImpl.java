package retail.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retail.contract.*;
import retail.domain.*;
import retail.repository.AddressRepository;
import retail.repository.CustomerRepository;
import retail.util.Util;

import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private ModelMapper mapper;
   @Autowired
   private AddressRepository addressRepository;
   @Autowired
   private CustomerRepository customerRepository;

/*@Override
public AddressResponse createNewAddress(String addressLine, String aptNo, String city,
                                String stateCode, String stateName, double percent, String countryRegion, String postalCode,
                                String phoneNumber, String phoneType, String addressType) {
    AddressResponse address = new Address(addressLine, aptNo, city, postalCode);
    AddressType addressType1 = new AddressType(addressType);
    PhoneType phoneType1 = new PhoneType(phoneType);

    Phone phone = new Phone(phoneNumber);
    CountryRegion countryRegion1 = new CountryRegion(countryRegion);
    StateProvince stateProvince1 = new StateProvince(stateCode, stateName, percent);
    stateProvince1.setCountryRegion(countryRegion1);
    phone.setPhoneType(phoneType1);
    address.setAddressType(addressType1);
    address.setPhone(phone);
  Customer customer= customerRepository.findByUsername(Util.getLoggedInUserName());
    address.setStateProvince(stateProvince1);

    addressRepository.save(address);
    customer.getShippingAddresses().add(address);
    return address;
}*/

    @Override
    public Address createNewAddress(AddressResponse addressResponse1) {
    Address address= AddressAdapter1.getAddressFromAddressResponse(addressResponse1);
     // AddressResponse addressResponse1=new AddressResponse();
      /* address.setAddressLine(addressResponse1.getAddressLine());
       address.setAptNo(addressResponse1.getAptNo());
       address.setCity(addressResponse1.getCity());*/
        Customer customer= customerRepository.findByUsername(Util.getLoggedInUserName());
        addressRepository.save(address);
        customer.getShippingAddresses().add(address);
        return address;
    /*    addressResponse1.setAddressLine(addressResponse1.getAddressLine());
        addressResponse1.setAptNo(addressResponse1.getAptNo());
        addressResponse1.setCity(addressResponse1.getCity());

        AddressTypeResponse addressTypeResponse=new AddressTypeResponse();
        addressTypeResponse.setName(addressTypeResponse.getName());
        PhoneTypeResponse phoneTypeResponse=new PhoneTypeResponse();
        phoneTypeResponse.setName(phoneTypeResponse.getName());
        CountryRegionResponse countryRegionResponse=new CountryRegionResponse();
        countryRegionResponse.setName(countryRegionResponse.getName());
        StateProvinceResponse stateProvinceResponse=new StateProvinceResponse();
        stateProvinceResponse.setCode(stateProvinceResponse.getCode());
        stateProvinceResponse.setName(stateProvinceResponse.getName());
        stateProvinceResponse.setTaxPercent(stateProvinceResponse.getTaxPercent());
        stateProvinceResponse.setCountryRegionResponse(countryRegionResponse);
        PhoneResponse phoneResponse=new PhoneResponse();
        phoneResponse.setPhoneNumber(phoneResponse.getPhoneNumber());
        phoneResponse.setPhoneTypeResponse(phoneTypeResponse);
        //address.setAddressTypeRe(addressTypeResponse);
        addressResponse1.setStateProvinceResponse(stateProvinceResponse);
        addressResponse1.setAddressTypeResponse(addressTypeResponse);
        addressResponse1.setPhoneResponse(phoneResponse);

        Customer customer= customerRepository.findByUsername(Util.getLoggedInUserName());

        addressRepository.save(addressResponse1);
        customer.getShippingAddresses().add(address);
        return addressResponse1;*/


    }


    @Override
  public Optional<AddressResponse> getAddressByCity(String city) {
      return addressRepository.findAddressByCity(city)
              .map(ent -> mapper.map(ent, AddressResponse.class));
  }


    @Override
    public Page<AddressResponse> getAllAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable).map(entity->mapper.map(entity, AddressResponse.class));
    }



   /* @Override
    public Address modifyAddressByAddressId(String addressLine, String aptNo, String city,
                                 String stateCode, String stateName, double percent, String countryRegion, String postalCode,
                                 String phoneNumber,String phoneType, String addressType, Long addressId) {

        //Address address= addressRepository.findAddressesByAddressLineAndAptNo(addressLine, aptNo);
        Address address=addressRepository.findById(addressId).get();
        if(address==null){
            return null;
        }
        address.setAddressLine(addressLine);
        address.setAptNo(aptNo);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.getAddressType().setName(addressType);
        address.getPhone().getPhoneType().setName(phoneType);
        address.getPhone().setPhoneNumber(phoneNumber);
        address.getStateProvince().setCode(stateCode);
        address.getStateProvince().setName(stateName);
        address.getStateProvince().setTaxPercent(percent);
        address.getStateProvince().getCountryRegion().setName(countryRegion);
        addressRepository.save(address);
        return address;

    }*/

   /* @Override
    public boolean deleteAddressByAddressLineAndApt(String addressLine1, String aptNo) {
        Address address= addressRepository.findAddressesByAddressLineAndAptNo(addressLine1,aptNo);
        if(address==null){
            return false;
        }
         addressRepository.delete(address);
        return true;
    }*/

    @Override
    public boolean deleteAddressById(Long id) {
       addressRepository.deleteById(id);
       return true;
    }
    public Address findById(Long addressId){
        return addressRepository.findById(addressId).get();
    }

    @Override
    public AddressResponse getAddressByAddressId(Long addressId) {
        return addressRepository.findById(addressId).map(entity->mapper.map(entity, AddressResponse.class)).get();
    }
}
