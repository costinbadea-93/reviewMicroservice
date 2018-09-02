package mainAPI.repository;

import mainAPI.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by cbadea on 4/2/2018.
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
