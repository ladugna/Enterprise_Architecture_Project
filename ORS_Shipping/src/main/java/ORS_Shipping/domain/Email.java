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
public class Email {
    @Id
    @GeneratedValue
    private long id;
    private String address;
    private String subject;
    private String body;
    private LocalDateTime date;

    public Email(String address, String subject, String body, LocalDateTime date) {
        this.address = address;
        this.subject = subject;
        this.body = body;
        this.date = date;
    }
}
