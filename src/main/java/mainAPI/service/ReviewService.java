package mainAPI.service;

import mainAPI.model.Review;
import mainAPI.repository.ReviewRepository;
import mainAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cbadea on 4/2/2018.
 */

@Service
@Transactional
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    public List<Review> getSpecificReviews(int eventId) {
        return reviewRepository.findAll()
                .stream().filter(review -> review.getEventId() == eventId)
                .collect(Collectors.toList());

    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(int reviewId) {
         reviewRepository.delete(reviewId);
    }

}

