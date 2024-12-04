package ru.clevertec.carshowroom.repository.review;

import ru.clevertec.carshowroom.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    ReviewEntity addReview(ReviewEntity reviewEntity);

    List<ReviewEntity> findAll();

    Optional<ReviewEntity> findReviewById(Long id);

    Optional<ReviewEntity> update(ReviewEntity updatedReviewEntity);

    boolean deleteById(Long id);
}
