package ru.clevertec.carshowroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.carshowroom.dto.review.ReviewRequest;
import ru.clevertec.carshowroom.dto.review.ReviewResponse;
import ru.clevertec.carshowroom.dto.review.UpdateReviewRequest;
import ru.clevertec.carshowroom.entity.Car;
import ru.clevertec.carshowroom.entity.Client;
import ru.clevertec.carshowroom.entity.Review;
import ru.clevertec.carshowroom.exception.CarNotFoundException;
import ru.clevertec.carshowroom.exception.ClientNotFoundException;
import ru.clevertec.carshowroom.exception.ReviewNotFoundException;
import ru.clevertec.carshowroom.mapper.ReviewMapper;
import ru.clevertec.carshowroom.repository.CarRepository;
import ru.clevertec.carshowroom.repository.ClientRepository;
import ru.clevertec.carshowroom.repository.ReviewRepository;
import ru.clevertec.carshowroom.service.ReviewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public ReviewRequest addReview(ReviewRequest reviewRequest) {
        Car car = carRepository.findById(reviewRequest.getCar().getId())
                .orElseThrow(() -> CarNotFoundException.byId(reviewRequest.getCar().getId()));

        Client client = clientRepository.findById(reviewRequest.getClient().getId())
                .orElseThrow(() -> ClientNotFoundException.byId(reviewRequest.getClient().getId()));

        Review review = reviewMapper.toReview(reviewRequest);

        review.setCar(car);
        review.setClient(client);

        return reviewMapper.toAddReviewDTO(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public List<ReviewResponse> findAll() {
        List<Review> reviewEntities = reviewRepository.findAll();
        return reviewMapper.toOutputReviewDTOs(reviewEntities);
    }

    @Override
    @Transactional
    public ReviewResponse findById(Long id) {
        return reviewMapper.toOutputReviewDTO(reviewRepository.findById(id).orElseThrow(
                () -> ReviewNotFoundException.byId(id))
        );
    }

    @Override
    @Transactional
    public UpdateReviewRequest update(UpdateReviewRequest updateReviewRequest) {
        Review existingReview = reviewRepository.findById(updateReviewRequest.getId())
                .orElseThrow(() -> ReviewNotFoundException.byId(updateReviewRequest.getId()));

        Car car = carRepository.findById(updateReviewRequest.getCar().getId())
                .orElseThrow(() -> CarNotFoundException.byId(updateReviewRequest.getCar().getId()));

        Client client = clientRepository.findById(updateReviewRequest.getClient().getId())
                .orElseThrow(() -> ClientNotFoundException.byId(updateReviewRequest.getClient().getId()));

        existingReview.setText(updateReviewRequest.getText());
        existingReview.setRating(updateReviewRequest.getRating());
        existingReview.setCar(car);
        existingReview.setClient(client);

        return reviewMapper.toUpdateReviewDTO(reviewRepository.save(existingReview));

    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
