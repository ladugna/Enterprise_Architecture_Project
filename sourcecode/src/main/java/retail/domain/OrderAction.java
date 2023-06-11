package retail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retail.constant.Action;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAction {
    private Action action;
    private String username;
    private String email;
}

