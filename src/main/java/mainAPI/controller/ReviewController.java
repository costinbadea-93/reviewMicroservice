package mainAPI.controller;

import io.swagger.annotations.*;
import mainAPI.model.Review;
import mainAPI.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cbadea on 4/2/2018.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
@Api("reviews")
public class ReviewController {

    private static final Logger LOGGER = (Logger) LoggerFactory.
            getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;

    @CrossOrigin("http://localhost:9000/")
    @GetMapping(value = "/getEventReview/{reviewId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${ReviewController.getReviews}", response = Review.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Review> getReviews(@ApiParam("ReviewId") @PathVariable(value = "reviewId") int reviewId){
        List<Review> reviews = reviewService.getSpecificReviews(reviewId);
        return reviews;
    }

    @CrossOrigin("http://localhost:9000/")
    @PostMapping(value = "/addReview")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${ReviewController.getReviews}", response = Review.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Review addReview(@ApiParam("ReviewId") @RequestBody Review review) {
        Review reviewSaved = reviewService.saveReview(review);
        return reviewSaved;
    }

    @CrossOrigin("http://localhost:9000/")
    @GetMapping(value = "/deleteReview/{reviewId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${ReviewController.getReviews}", response = Review.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<?> deleteReview(@ApiParam("ReviewId") @RequestBody Review review) {
        reviewService.saveReview(review);
        return ResponseEntity.ok().body(HttpStatus.FOUND);
    }
}
