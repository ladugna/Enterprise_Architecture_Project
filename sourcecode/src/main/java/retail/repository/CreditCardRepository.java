package retail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import retail.domain.CreditCard;
import retail.domain.Item;
import retail.service.adapter.CreditCardDTO;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Page<CreditCard> findAll(Pageable pageable);


//    @Query("Select c from CreditCard c join fetch c.Customer cc where c.id =:ccId and cc.")
//    Optional<CreditCard> findTop1ByCreditCardIdAndCustomerId(@Param("ccId") long ccId, @Param("cId") long cId);
}
