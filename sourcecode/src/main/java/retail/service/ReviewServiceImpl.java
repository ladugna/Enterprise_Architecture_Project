package retail.service;

import javax.transaction.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.contract.ReviewRequest;
import retail.contract.ReviewResponse;
import retail.domain.Customer;
import retail.domain.Item;
import retail.domain.Review;
import retail.logging.ILogger;
import retail.repository.CustomerRepository;
import retail.repository.ItemRepository;
import retail.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ReviewServiceImpl {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ILogger logger;

    public ReviewResponse addReview(long customerId, long itemId, ReviewRequest reviewRequest) {
        try {
            Review review = mapper.map(reviewRequest, Review.class);
            review.setDate(LocalDateTime.now());
            Customer customer = customerRepository.findById(customerId).get();
            review.setCustomer(customer);
            Item item = itemRepository.findById(itemId).get();
            review.setItem(item);
            if(item == null || customer == null){
                return null;
            }
            reviewRepository.save(review);
            logger.log("add Review for customerId = " + customerId + " and itemId = " + itemId);

            ReviewResponse reviewResponse = new ReviewResponse(review.getTitle(),
                    review.getNumberOfStars(), review.getDate());
            reviewResponse.setCustomerId(customerId);
            reviewResponse.setItemId(itemId);
            reviewResponse.setId(review.getId());
            return reviewResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

    public Review updateReview( long reviewId, ReviewRequest reviewRequest) {
        try {
            Review review = reviewRepository.findById(reviewId);
            if (review == null) return null;
            //review = mapper.map(reviewRequest, Review.class);
            review.setTitle(reviewRequest.getTitle());
            review.setNumberOfStars(review.getNumberOfStars());
            //review.setDate(LocalDateTime.now());
            reviewRepository.save(review);
            logger.log("update Review with id = " + reviewId );
            return review;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

    public boolean deleteReview( long reviewId) throws RuntimeException{
        try {
            Review review = reviewRepository.findById(reviewId);
            if (review == null) return false;
            reviewRepository.deleteById(reviewId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }
    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for(Review review: reviews){
            ReviewResponse reviewResponse = new ReviewResponse(review.getTitle(),
                    review.getNumberOfStars(), review.getDate());
            Customer customer = review.getCustomer();
            Item item = review.getItem();
            long customerId = (customer==null) ? 0 : customer.getId() ;
            long itemId = (item == null) ? 0 : item.getId();
            reviewResponse.setCustomerId(customerId);
            reviewResponse.setItemId(itemId);
            reviewResponse.setId(review.getId());
            reviewResponses.add(reviewResponse);
        }
        return reviewResponses;
    }
}
