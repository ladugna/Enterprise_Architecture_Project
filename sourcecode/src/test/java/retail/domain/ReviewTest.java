package retail.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewTest {

    Review review;
    @BeforeEach
    public void setup() {
        review = new Review("Good Product", 5, LocalDateTime.now());
    }
    @AfterEach
    public void cleanup(){
        review = null;
    }
    @Test
    public void testCreateReview(){
        assertThat(review.getTitle()).isEqualTo("Good Product");
        assertThat(review.getNumberOfStars()).isEqualTo(5);
    }
}
