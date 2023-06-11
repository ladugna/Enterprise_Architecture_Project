package retail.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import retail.OnlineRetailSystemApplication;
import retail.domain.Customer;
import retail.domain.Review;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes= OnlineRetailSystemApplication.class)
@DataJpaTest
public class ReviewRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReviewRepository reviewRepository;
    @Test
    public void whenCreateReview_thenReturnReview(){
        //given
        Review review = new Review("title", 5 , LocalDateTime.now());
        entityManager.persist(review);
        entityManager.flush();
        //when
        Review found = reviewRepository.findAll().get(0);
        //then
        assertThat(found.getTitle()).isEqualTo(review.getTitle());
        assertThat(found.getNumberOfStars()).isEqualTo(review.getNumberOfStars());

    }
    @Test
    public void whenfindByTitle_thenReturnReview(){
        //given
        Review review = new Review("title", 5 , LocalDateTime.now());
        entityManager.persist(review);
        entityManager.flush();
        //when
        Review found = reviewRepository.findAll().get(0);
        //then
        assertThat(found.getTitle()).isEqualTo(review.getTitle());
        assertThat(found.getNumberOfStars()).isEqualTo(review.getNumberOfStars());

    }
}
