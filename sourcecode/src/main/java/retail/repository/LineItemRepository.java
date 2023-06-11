package retail.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem,Long> {

    @Query("Select l from LineItem l where l.status != 'PROCESSED'")
    List<LineItem> findByCart(ShoppingCart cart);

}
