package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomRequest;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomResponse;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomRequest;
import ru.clevertec.carshowroom.entity.CarShowroom;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarShowroomMapper {
    CarShowroom toCarShowroom(CarShowroomRequest carShowroomRequest);

    CarShowroomRequest toAddCarShowroomDTO(CarShowroom carShowroom);

    @Mapping(source = "cars", target = "cars")
    CarShowroomResponse toOutputCarShowroomDTO(CarShowroom carShowroom);

    List<CarShowroomResponse> toOutputCarShowroomDTOs(List<CarShowroom> carShowrooms);

    CarShowroom toCarShowroom(UpdateCarShowroomRequest updateCarShowroomRequest);
    UpdateCarShowroomRequest toUpdateCarShowroomDTO(CarShowroom carShowroom);

    CarShowroom toCarShowroom(CarShowroomResponse carShowroomResponse);
}
