package ru.clevertec.carshowroom.service.review;

import ru.clevertec.carshowroom.dto.review.AddReviewDTO;
import ru.clevertec.carshowroom.dto.review.OutputReviewDTO;
import ru.clevertec.carshowroom.dto.review.UpdateReviewDTO;

import java.util.List;

public interface ReviewService {
    AddReviewDTO addReview(AddReviewDTO addReviewDTO);

    List<OutputReviewDTO> findAll();

    OutputReviewDTO findById(Long id);

    UpdateReviewDTO update(UpdateReviewDTO updateReviewDTO);

    boolean deleteById(Long id);
}
