package ru.clevertec.carshowroom.service.review.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.carshowroom.dto.review.AddReviewDTO;
import ru.clevertec.carshowroom.dto.review.OutputReviewDTO;
import ru.clevertec.carshowroom.dto.review.UpdateReviewDTO;
import ru.clevertec.carshowroom.entity.ReviewEntity;
import ru.clevertec.carshowroom.exception.ReviewNotFoundException;
import ru.clevertec.carshowroom.mapper.ReviewMapper;
import ru.clevertec.carshowroom.repository.review.ReviewRepository;
import ru.clevertec.carshowroom.service.review.ReviewService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public AddReviewDTO addReview(AddReviewDTO addReviewDTO) {
        ReviewEntity reviewEntity = reviewRepository.addReview(reviewMapper.toReviewEntity(addReviewDTO));
        return reviewMapper.toAddReviewDTO(reviewEntity);
    }

    @Override
    public List<OutputReviewDTO> findAll() {
        return reviewMapper.toOutputReviewDTOs(reviewRepository.findAll());
    }

    @Override
    public OutputReviewDTO findById(Long id) {
        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findReviewById(id);
        if (optionalReviewEntity.isPresent()) {
            return reviewMapper.toOutputReviewDTO(optionalReviewEntity.get());
        } else {
            throw ReviewNotFoundException.byId(id);
        }
    }

    @Override
    public UpdateReviewDTO update(UpdateReviewDTO updateReviewDTO) {
        Optional<ReviewEntity> optionalReviewEntity
                = reviewRepository.update(reviewMapper.toReviewEntity(updateReviewDTO));
        if (optionalReviewEntity.isPresent()) {
            return reviewMapper.toUpdateReviewDTO(optionalReviewEntity.get());
        } else {
            throw ReviewNotFoundException.byId(updateReviewDTO.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (reviewRepository.deleteById(id)) {
            return true;
        } else {
            throw ReviewNotFoundException.byId(id);
        }
    }
}
