
package retail.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retail.contract.AddressResponse;
import retail.domain.Address;
import retail.error.CustomErrorType;
import retail.service.AddressService;
import java.util.Optional;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private ModelMapper mapper;
    @GetMapping("")
    public ResponseEntity<?> getAllAddresses(Pageable pageable) {
        Page<AddressResponse> addresses = addressService.getAllAddresses(pageable);
        if (addresses == null) {
            return new ResponseEntity<>(new CustomErrorType("No address exists"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressResponse addressResponse) {
       /* Address address = addressService.createNewAddress(addressResponse.getAddressLine(),
                addressResponse.getAptNo(), addressResponse.getCity(),
                addressResponse.getStateProvinceResponse().getCode(),
                addressResponse.getStateProvinceResponse().getName(),
                addressResponse.getStateProvinceResponse().getTaxPercent(),
                addressResponse.getStateProvinceResponse().getCountryRegionResponse().getName(),
                addressResponse.getPostalCode(),
                addressResponse.getPhoneTypeResponse().getName(),
                addressResponse.getPhoneTypeResponse().get,
                addressResponse.getAddressTypeResponse().getName());*/
        Address address=addressService.createNewAddress(addressResponse);
        if (address == null) {
            return new ResponseEntity<>(new CustomErrorType("No address Found "), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

   /* @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@RequestBody AddressResponse addressResponse, @PathVariable("addressId") Long addressId) {
        Address address= addressService.findById(addressId);

        if (address == null) {
            return new ResponseEntity<>(new CustomErrorType("No address Found "), HttpStatus.NOT_FOUND);
        }
        address = addressService.modifyAddressByAddressId(addressResponse.getAddressLine(),
                addressResponse.getAptNo(), addressResponse.getCity(),
                addressResponse.getStateProvince().getCode(),
                addressResponse.getStateProvince().getName(),
                addressResponse.getStateProvince().getTaxPercent(),
                addressResponse.getStateProvince().getCountryRegion().getName(),
                addressResponse.getPostalCode(),
                addressResponse.getPhone().getPhoneNumber(),
                addressResponse.getPhone().getPhoneType().getName(),
                addressResponse.getAddressType().getName(), addressId);
              return new ResponseEntity<>(address, HttpStatus.OK);
    }*/
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddressById(@PathVariable("addressId") Long addressId) {
        boolean deleted = addressService.deleteAddressById(addressId);
        if (!deleted) {
            return new ResponseEntity<>(new CustomErrorType("Address with id no: "+ addressId+"   not found or already deleted"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted: "+deleted, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable("addressId") Long addressId){
       AddressResponse addressResponse=  addressService.getAddressByAddressId(addressId);
        if(addressResponse==null){
            return new ResponseEntity<>(new CustomErrorType("Address with id no: "+ addressId+"   not found or already deleted"), HttpStatus.NOT_FOUND);
        }
       return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }
    @GetMapping("/by/{city}")
    public ResponseEntity<?> getAddressesByCity(@PathVariable("city") String city){
      Optional<AddressResponse> addressResponses= addressService.getAddressByCity(city);

        if(addressResponses.isEmpty()){
      return  new ResponseEntity<>(new CustomErrorType("Address with city: "+ city+"  " +
              " not found or already deleted"), HttpStatus.NOT_FOUND);


        }
        return new ResponseEntity<>(addressResponses.get(), HttpStatus.OK);
    }
}


