package retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.domain.OrderHistoryDetails;

@Repository
public interface OrderHistoryDetailsReppository extends JpaRepository<OrderHistoryDetails,Long> {
}
