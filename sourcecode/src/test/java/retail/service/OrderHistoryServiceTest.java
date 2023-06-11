package retail.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import retail.constant.OrderHistoryState;
import retail.repository.OrderHistoryRepository;
import retail.service.OrderHistoryServiceImp;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderHistoryServiceTest {

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @InjectMocks
    private OrderHistoryServiceImp orderHistoryService;


    @Test
    public void testUpdateOrderHistoryStatusWithAuthentication() {
        // Prepare test data
        Long orderId = 1L;
        OrderHistoryState status = OrderHistoryState.SHIPPED;


        // Call the method to be tested
        orderHistoryService.updateOrderHistoryStatus(orderId, status);

        // Verify that the repository method was called with the correct arguments
        verify(orderHistoryRepository, times(1)).updateOrderHistoryStatus(orderId, status);
    }

}
