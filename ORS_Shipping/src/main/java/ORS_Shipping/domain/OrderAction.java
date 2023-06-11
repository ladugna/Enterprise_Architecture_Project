package ORS_Shipping.domain;

import lombok.Data;

@Data
public class OrderAction {
    private Action action;
    private String username;
    private String email;
}
