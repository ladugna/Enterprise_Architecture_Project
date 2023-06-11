package retail.service;

import retail.contract.CountryRegionResponse;
import retail.domain.CountryRegion;

public class CountryRegionAdapter {
    public static CountryRegion getCountryRegionFromCountryRegionResponse(CountryRegionResponse countryRegionResponse){
        return  new CountryRegion(countryRegionResponse.getName());
    }
    public static CountryRegionResponse getCountryRegionResponseFromCountryRegion(CountryRegion countryRegion){
        return  new CountryRegionResponse(countryRegion.getName());
    }
}
