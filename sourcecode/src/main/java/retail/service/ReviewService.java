package retail.service;

import retail.contract.ReviewRequest;
import retail.contract.ReviewResponse;
import retail.domain.Review;

import java.util.List;

public interface ReviewService {

    public ReviewResponse addReview(long customerId, long itemId, ReviewRequest reviewRequest);
    public Review updateReview(long reviewId, ReviewRequest reviewRequest);
    public boolean deleteReview( long reviewId);
    public List<ReviewResponse> getAllReviews() ;


    }
