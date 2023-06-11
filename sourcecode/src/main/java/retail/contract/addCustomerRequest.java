package retail.contract;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class addCustomerRequest {
    String firstName;
    String lastName;
    String password;
}
