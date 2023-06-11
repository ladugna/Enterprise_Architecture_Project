package retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.domain.Item;

@Repository
public interface OrderRepository extends JpaRepository<Item, Integer> {
}
