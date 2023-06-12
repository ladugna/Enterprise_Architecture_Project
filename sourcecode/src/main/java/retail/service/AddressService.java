package retail.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import retail.contract.AddressResponse;
import retail.domain.*;

import java.util.Optional;

public interface AddressService {

    public Address createNewAddress(String addressLine, String aptNo, String city,
                                    String stateCode, String stateName, double percent, String countryRegion, String postalCode,
                                    String phoneNumber, String phoneType, String addressType);
    public Optional<AddressResponse> getAddressByCity(String city);
    public Page<AddressResponse> getAllAddresses(Pageable pageable);
    public Address modifyAddressByAddressId(String addressLine, String aptNo, String city,
                                            String stateCode, String stateName, double percent, String countryRegion, String postalCode,
                                            String phoneNumber,String phoneType, String addressType, Long addressId);
    //public boolean deleteAddressByAddressLineAndApt(String addressLine1, String aptNo);
   public boolean deleteAddressById(Long id);
    public Address findById(Long addressId);
    public AddressResponse getAddressByAddressId(Long addressId);


}
