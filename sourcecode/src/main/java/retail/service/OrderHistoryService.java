package retail.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import retail.constant.OrderHistoryState;
import retail.domain.OrderHistory;
import retail.service.adapter.OrderHistoryDTO;

import java.util.Map;

public interface OrderHistoryService {
    Page<OrderHistory> findAll(Pageable pageable , Map<String, String> searchCriteria);
    OrderHistory findById(long id);
    OrderHistory saveOrderHistory(OrderHistoryDTO orderHistory);
    OrderHistory saveOrderHistory(OrderHistory orderHistory);
    void updateOrderHistoryStatus(Long userId, OrderHistoryState status);
}
