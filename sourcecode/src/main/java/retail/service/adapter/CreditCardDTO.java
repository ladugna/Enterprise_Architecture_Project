package retail.service.adapter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreditCardDTO {

    private long Id;
    private long Number;
    private LocalDate ExpirationDate;
    private int SecurityCode;
}

