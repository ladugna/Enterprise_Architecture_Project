package retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import retail.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findById(long id);
}
