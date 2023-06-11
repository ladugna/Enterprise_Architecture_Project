package retail.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewRequest {
    private String Title;
    private int NumberOfStars;
    private LocalDateTime Date;
}
