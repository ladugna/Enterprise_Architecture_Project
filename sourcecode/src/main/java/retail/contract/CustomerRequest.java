package retail.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public CustomerRequest(String firstName, String lastName,
                           String username, String email){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
