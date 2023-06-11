package retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.domain.OrderHistoryDetails;
import retail.repository.OrderHistoryDetailsReppository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderHistoryDetailsImp implements OrderHistoryDetailsService{

    @Autowired
    OrderHistoryDetailsReppository orderHistoryDetailsReppository;

    @Override
    public OrderHistoryDetails save(OrderHistoryDetails orderHistoryDetails) {
        return orderHistoryDetailsReppository.save(orderHistoryDetails);
    }

    @Override
    public OrderHistoryDetails findById(Long Id) {
        return (orderHistoryDetailsReppository.findById(Id)).orElse(null);
    }
}
