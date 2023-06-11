package retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import retail.constant.OrderHistoryState;
import retail.domain.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory , Long> {

    @Modifying
    @Query("UPDATE OrderHistory o SET o.Status = :status WHERE o.Id = :orderId")
    void updateOrderHistoryStatus(@Param("orderId") Long orderId, @Param("status") OrderHistoryState status);
}
