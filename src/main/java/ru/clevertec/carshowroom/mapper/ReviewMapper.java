package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.review.AddReviewDTO;
import ru.clevertec.carshowroom.dto.review.OutputReviewDTO;
import ru.clevertec.carshowroom.dto.review.UpdateReviewDTO;
import ru.clevertec.carshowroom.entity.ReviewEntity;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Mapping(source = "car", target = "carEntity")
    @Mapping(source = "client", target = "clientEntity")
    ReviewEntity toReviewEntity(AddReviewDTO addReviewDTO);

    @Mapping(source = "carEntity", target = "car")
    @Mapping(source = "clientEntity", target = "client")
    AddReviewDTO toAddReviewDTO(ReviewEntity reviewEntity);

    @Mapping(source = "carEntity", target = "car")
    @Mapping(source = "clientEntity", target = "client")
    OutputReviewDTO toOutputReviewDTO(ReviewEntity reviewEntity);

    List<OutputReviewDTO> toOutputReviewDTOs(List<ReviewEntity> reviewEntities);

    @Mapping(source = "car", target = "carEntity")
    @Mapping(source = "client", target = "clientEntity")
    ReviewEntity toReviewEntity(UpdateReviewDTO updateReviewDTO);

    @Mapping(source = "carEntity", target = "car")
    @Mapping(source = "clientEntity", target = "client")
    UpdateReviewDTO toUpdateReviewDTO(ReviewEntity reviewEntity);
}
