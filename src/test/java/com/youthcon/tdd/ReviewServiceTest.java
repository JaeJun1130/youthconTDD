package com.youthcon.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;


class ReviewServiceTest {
    //mock을 이용해 가짜 주입을 함.
    private GiftApi giftApi = mock(GiftApi.class);
    ReviewRepository reviewRepository = mock(ReviewRepository.class);
    ReviewService reviewService = new ReviewService(reviewRepository,giftApi);

    private Long id = 1L;
    private String content = "재밌어요";
    private String phoneNumber = "010-1111-2222";

    /*
    스프링을 의존하게 되면 Delay.class 때문에 정확한 단위테스트를 실행할 수 없게된다.
    그래서 Service 로직을 실행할때는 스프링에 의존하지 않는게 좋은 방법이다.
     */
//    @SpringBootTest
//    @Autowired
//    ReviewService reviewService;

    @Test
    void 후기_조회_성공() {
        //given

        //JPA는 옵셔널 타입으로 리턴을 해줌
        //DB에 직접접근을 못해서 willReturn을 사용
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review(id, content, phoneNumber)));

        //when
        Review review = reviewService.getById(id);

        //then
        assertThat(review.getId()).isEqualTo(id);
        assertThat(review.getContent()).isEqualTo(content);
        assertThat(review.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void 후기_조회_실패() {
        //given

        //JPA는 옵셔널 타입으로 리턴을 해줌
        //DB에 직접접근을 못해서 willReturn을 사용
        given(reviewRepository.findById(id)).willReturn(Optional.empty());

        assertThatThrownBy(() -> reviewService.getById(10000L)).isInstanceOf(ReviewNotFountException.class);
    }

    @Test
    void 선뭃하기_성공() {
        //given
        given(reviewRepository.findById(id)).willReturn(Optional.of(new Review(id,content,phoneNumber)));
        given(giftApi.send(phoneNumber)).willReturn(true);
        given(reviewRepository.save(any(Review.class))).willReturn(new Review(id,content,phoneNumber));

        //when
        Review review = reviewService.sendGift(id);

        //then
        assertThat(review.getId()).isEqualTo(id);
        assertThat(review.getSent()).isEqualTo(true);
    }
}