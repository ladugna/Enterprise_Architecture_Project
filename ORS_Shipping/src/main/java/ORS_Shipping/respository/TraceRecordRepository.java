package ORS_Shipping.respository;

import ORS_Shipping.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {
}
