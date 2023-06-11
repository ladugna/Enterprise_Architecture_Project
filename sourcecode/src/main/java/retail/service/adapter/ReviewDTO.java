package retail.service.adapter;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {

    private String Title;
    private int NumberOfStars;
    private LocalDateTime Date;
    private long customerId;
    private long itemId;
    public ReviewDTO(String title, int numberOfStars, LocalDateTime date) {
        Title = title;
        NumberOfStars = numberOfStars;
        Date = date;
    }

    public ReviewDTO() {
    }
}
