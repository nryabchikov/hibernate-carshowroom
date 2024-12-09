package ru.clevertec.carshowroom.service;

import ru.clevertec.carshowroom.dto.review.ReviewRequest;
import ru.clevertec.carshowroom.dto.review.ReviewResponse;
import ru.clevertec.carshowroom.dto.review.UpdateReviewRequest;

import java.util.List;

public interface ReviewService {
    ReviewRequest addReview(ReviewRequest reviewRequest);

    List<ReviewResponse> findAll();

    ReviewResponse findById(Long id);

    UpdateReviewRequest update(UpdateReviewRequest updateReviewRequest);

    void deleteById(Long id);
}
