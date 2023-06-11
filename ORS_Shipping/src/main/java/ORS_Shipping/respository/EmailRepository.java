package ORS_Shipping.respository;

import ORS_Shipping.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findAllByOrderByDateAsc();
}
