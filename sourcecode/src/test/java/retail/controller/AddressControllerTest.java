package retail.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import retail.controller.AddressController;
import retail.service.AddressService;


import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    @Autowired
    MockMvc mock;
    @Autowired
    private AddressService addressService;

    @Test
    public  void testDeleteAddressByAddressId()throws Exception{
        mock.perform(MockMvcRequestBuilders.delete("/addresses/{id}",1))
                .andExpect(status().isOk());
       Mockito.verify(addressService,times(1)).deleteAddressById(1L);
    }

}
