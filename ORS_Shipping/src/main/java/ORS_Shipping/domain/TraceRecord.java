package ORS_Shipping.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class TraceRecord {
    @Id
    @GeneratedValue
    private long id;
    private String method;
    private String type;
    private String message;
    private LocalDateTime Date;

    public TraceRecord(String method, String type, String message, LocalDateTime date) {
        this.method = method;
        this.type = type;
        this.message = message;
        Date = date;
    }
}
