package retail.service;

import retail.contract.StateProvinceResponse;
import retail.domain.StateProvince;

public class StateProvinceAdapter {
    public static StateProvince getStateProvinceFromStateProvinceResponse(StateProvinceResponse stateProvinceResponse){
        StateProvince stateProvince=new StateProvince(stateProvinceResponse.getCode(),
                stateProvinceResponse.getName(), stateProvinceResponse.getTaxPercent());
        //stateProvince.setCountryRegion(stateProvinceResponse.getCountryRegionResponse().getName());
        stateProvince.setCountryRegion(CountryRegionAdapter.getCountryRegionFromCountryRegionResponse(stateProvinceResponse.getCountryRegionResponse()));
        return stateProvince;
    }
    public static StateProvinceResponse getStateProvinceResponseFromStateProvince(StateProvince stateProvince){
        StateProvinceResponse stateProvinceResponse=new StateProvinceResponse(stateProvince.getCode(),
                stateProvince.getName(), stateProvince.getTaxPercent());
        stateProvinceResponse.setCountryRegionResponse(CountryRegionAdapter.getCountryRegionResponseFromCountryRegion(stateProvince.getCountryRegion()));
        return stateProvinceResponse;
    }
}
