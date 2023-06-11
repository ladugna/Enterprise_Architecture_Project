package retail.service;

import org.springframework.stereotype.Service;
import retail.domain.OrderHistoryDetails;

@Service
public interface OrderHistoryDetailsService {
    public OrderHistoryDetails save(OrderHistoryDetails orderHistoryDetails);

    public OrderHistoryDetails findById(Long Id);
}
