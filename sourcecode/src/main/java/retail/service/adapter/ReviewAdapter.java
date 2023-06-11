package retail.service.adapter;

import retail.domain.Review;

public class ReviewAdapter {
    public static Review getReviewFromReviewDTO(ReviewDTO reviewDTO){
        return new Review(reviewDTO.getTitle(), reviewDTO.getNumberOfStars(), reviewDTO.getDate());
    }

    public static ReviewDTO getReviewDTOFromReview(Review review){
        return new ReviewDTO(review.getTitle(), review.getNumberOfStars(), review.getDate());
    }
}
