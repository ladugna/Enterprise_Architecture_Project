package retail.service;



import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import retail.constant.OrderHistoryState;
import retail.domain.OrderHistory;
import retail.domain.OrderHistoryDetails;
import retail.repository.CustomerRepository;
import retail.repository.OrderHistoryRepository;
import retail.service.adapter.OrderHistoryAdapter;
import retail.service.adapter.OrderHistoryDTO;
import retail.util.Util;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderHistoryServiceImp implements OrderHistoryService{

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<OrderHistory> findAll(Pageable pageable , Map<String, String> searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderHistory> query = criteriaBuilder.createQuery(OrderHistory.class);
        Root<OrderHistory> root = query.from(OrderHistory.class);

        Predicate predicate = criteriaBuilder.conjunction();
        Join<OrderHistory, OrderHistoryDetails> orderHistoryDetailsJoin = root.join("orderHistoryDetails");

        predicate = criteriaBuilder.and(predicate , criteriaBuilder.equal(root.get("customer") , customerRepository.findByUsername(Util.getLoggedInUserName()).getId()));
        if (searchCriteria.get("name") != null && !searchCriteria.get("name").isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(orderHistoryDetailsJoin.get("Name"), "%" + searchCriteria.get("name") + "%"));
        }

        if (searchCriteria.get("maxPrice") != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.le(orderHistoryDetailsJoin.get("Price"), Integer.valueOf(searchCriteria.get("maxPrice"))));
        }

        if (searchCriteria.get("minPrice") != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.ge(orderHistoryDetailsJoin.get("Price"), Integer.valueOf(searchCriteria.get("minPrice"))));
        }

        if (searchCriteria.get("description") != null && !searchCriteria.get("description").isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(orderHistoryDetailsJoin.get("Description"), "%" + searchCriteria.get("description") + "%"));
        }

        query.where(predicate);

        TypedQuery<OrderHistory> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<OrderHistory> resultList = typedQuery.getResultList();

        return new PageImpl<>(resultList, pageable, resultList.size());
    }

    @Override
    public OrderHistory findById(long id) {
        return orderHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public OrderHistory saveOrderHistory(OrderHistoryDTO orderHistory){
        orderHistory.setCustomer(customerRepository.findByUsername(Util.getLoggedInUserName()));
        System.out.println(OrderHistoryAdapter.getOrderHistoryFromDTO(orderHistory));
        return orderHistoryRepository.save(OrderHistoryAdapter.getOrderHistoryFromDTO(orderHistory));
    }

    @Override
    public OrderHistory saveOrderHistory(OrderHistory orderHistory) {
        orderHistory.setCustomer(customerRepository.findByUsername(Util.getLoggedInUserName()));
        return orderHistoryRepository.save(orderHistory);
    }

    @Override
    @Transactional
    public void updateOrderHistoryStatus(Long orderHistoryId, OrderHistoryState status) {
        orderHistoryRepository.updateOrderHistoryStatus(orderHistoryId, status);
    }


}
