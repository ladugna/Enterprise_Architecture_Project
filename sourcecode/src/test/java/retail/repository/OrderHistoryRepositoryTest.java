package retail.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retail.constant.OrderHistoryState;
import retail.repository.OrderHistoryRepository;
import retail.service.OrderHistoryServiceImp;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderHistoryRepositoryTest {

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @InjectMocks
    private OrderHistoryServiceImp orderService;

    @Test
    public void testUpdateOrderHistoryStatus() {
        // Prepare test data
        Long orderId = 1L;
        OrderHistoryState status = OrderHistoryState.SHIPPED;

        // Mock the method call
        doNothing().when(orderHistoryRepository).updateOrderHistoryStatus(orderId, status);

        // Call the method to be tested
        orderService.updateOrderHistoryStatus(orderId, status);

        // Verify the method was called with the correct arguments
        verify(orderHistoryRepository, times(1)).updateOrderHistoryStatus(orderId, status);
    }
}
