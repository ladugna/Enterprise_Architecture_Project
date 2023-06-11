package retail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import retail.domain.TraceRecord;

public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {
}
