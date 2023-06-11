package retail.contract;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditCardRequest {
    private long Id;
    private long Number;
    private LocalDate ExpirationDate;
    private int SecurityCode;
}
