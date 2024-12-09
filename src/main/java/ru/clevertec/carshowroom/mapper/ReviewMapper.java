package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.review.ReviewRequest;
import ru.clevertec.carshowroom.dto.review.ReviewResponse;
import ru.clevertec.carshowroom.dto.review.UpdateReviewRequest;
import ru.clevertec.carshowroom.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "car", target = "car")
    @Mapping(source = "client", target = "client")
    Review toReview(ReviewRequest reviewRequest);

    @Mapping(source = "car", target = "car")
    @Mapping(source = "client", target = "client")
    ReviewRequest toAddReviewDTO(Review review);

    @Mapping(source = "car", target = "car")
    @Mapping(source = "client", target = "client")
    ReviewResponse toOutputReviewDTO(Review review);

    List<ReviewResponse> toOutputReviewDTOs(List<Review> reviews);

    @Mapping(source = "car", target = "car")
    @Mapping(source = "client", target = "client")
    Review toReview(UpdateReviewRequest updateReviewRequest);

    @Mapping(source = "car", target = "car")
    @Mapping(source = "client", target = "client")
    UpdateReviewRequest toUpdateReviewDTO(Review review);
}
