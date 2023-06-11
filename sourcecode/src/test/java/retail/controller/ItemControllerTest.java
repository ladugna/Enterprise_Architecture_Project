package retail.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import retail.contract.ItemRequest;
import retail.controller.ItemController;
import retail.service.adapter.ItemDTO;
import retail.service.ItemServiceImpl;

public class ItemControllerTest {

    @Mock
    private ItemServiceImpl itemService;

    @InjectMocks
    private ItemController itemController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveItem() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setName("Apple1");
        itemRequest.setPrice(10.0);
        itemRequest.setCategory("fruit");

        ItemDTO itemResponse = new ItemDTO();
        itemResponse.setId(1L);
        itemResponse.setName("Apple1");
        itemResponse.setPrice(10.0);
        itemResponse.setCategory("Apple1");

        when(itemService.saveItem(itemRequest)).thenReturn(itemResponse);

        ResponseEntity<?> responseEntity = itemController.SaveItem(itemRequest);

        verify(itemService, times(1)).saveItem(itemRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ItemDTO responseBody = (ItemDTO) responseEntity.getBody();
        assertEquals(1L, responseBody.getId());
        assertEquals("Apple1", responseBody.getName());
        assertEquals(10.0, responseBody.getPrice(), 0.0);
        assertEquals("Apple1", responseBody.getCategory());
    }
}