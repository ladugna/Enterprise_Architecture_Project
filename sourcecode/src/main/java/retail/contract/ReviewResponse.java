package retail.contract;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private long id;
    private String title;
    private int numberOfStars;
    private LocalDateTime date;
    private long customerId;
    private long itemId;
    public ReviewResponse(String title, int numberOfStars, LocalDateTime date){
        this.title = title;
        this.numberOfStars = numberOfStars;
        this.date = date;
    }
}
