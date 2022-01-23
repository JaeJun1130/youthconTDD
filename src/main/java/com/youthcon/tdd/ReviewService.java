package com.youthcon.tdd;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final GiftApi giftApi;

    public ReviewService(ReviewRepository reviewRepository, GiftApi giftApi) {
        this.reviewRepository = reviewRepository;
        this.giftApi = giftApi;
    }

    public Review getById(Long id) {
        return reviewRepository.findById(id).orElseThrow(()->new ReviewNotFountException("no review" + id));
    }

    public Review sendGift(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFountException("no id"));

        if(review.getSent()){
            throw new DuplicateSendGiftException("internal exception");
        }

        review.makeTrue();

        return review;
    }
}
