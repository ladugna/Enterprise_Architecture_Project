package retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retail.contract.ReviewRequest;
import retail.contract.ReviewResponse;
import retail.error.CustomErrorType;
import retail.service.ReviewServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@RequestMapping("/customers/{customerId}/reviews")
public class ReviewController {
    @Autowired
    ReviewServiceImpl reviewService;

    //add review
    @GetMapping("/reviews")
    public ResponseEntity<?>getAllReviews(){
        List<ReviewResponse> list = reviewService.getAllReviews();
        /*if(list == null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with Id = "
                    + customerId + " is not available"), HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<List<ReviewResponse>>(list, HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<?> addReview(@RequestParam long customerId, @RequestParam long itemId,
                                       @RequestBody ReviewRequest reviewRequest){
        ReviewResponse reviewResponse=  reviewService.addReview(customerId, itemId, reviewRequest);
        if(reviewResponse== null)
        {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with Id = "
                    + customerId + "or itemId = " + itemId + " is not available"), HttpStatus.NOT_FOUND);
        }
        //reviewResponse.setDate(LocalDateTime.now());
        return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.OK);
    }
    //update review
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable long reviewId,
                                          @RequestBody ReviewRequest reviewRequest){
        if(reviewService.updateReview( reviewId, reviewRequest) == null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Review with Id = "
                    + reviewId + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Update Review Successfully", HttpStatus.OK);

    }
    //delete review
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable long reviewId) {

        if(reviewService.deleteReview(reviewId) == false){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Review with Id = "
                     + " reviewId:  " + reviewId + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Delete Review Successfully", HttpStatus.OK);
    }

    class ReviewRequests{
        List<ReviewRequest> list;
        public ReviewRequests(){};
        public ReviewRequests(List<ReviewRequest> list){
            this.list = list;
        }
    }
}
