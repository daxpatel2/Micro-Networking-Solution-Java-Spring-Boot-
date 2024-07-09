package com.daxpatel.ReviewMS.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        return reviewService.addReview(companyId,review) ? new ResponseEntity<>("Added review",HttpStatus.OK) : new ResponseEntity<>("no review",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>(review,HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean status = reviewService.updateReview(reviewId,review);
        return status ? new ResponseEntity<>("Review updated",HttpStatus.OK) : new ResponseEntity<>("Could not update review", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean status = reviewService.deleteReview(reviewId);
        return status ? new ResponseEntity<>("Review deleted",HttpStatus.OK) : new ResponseEntity<>("Could not delete review", HttpStatus.BAD_REQUEST);

    }

}
