package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.car.CarRequest;
import ru.clevertec.carshowroom.dto.car.CarResponse;
import ru.clevertec.carshowroom.dto.car.UpdateCarRequest;
import ru.clevertec.carshowroom.entity.Car;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Car toCar(CarRequest carRequest);

    @Mapping(source = "category.id", target = "categoryId")
    CarRequest toAddCarDTO(Car car);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(source = "showroomTitle", target = "carShowroom.title")
    @Mapping(source = "showroomId", target = "carShowroom.id")
    @Mapping(source = "clients", target = "clients")
    @Mapping(source = "reviews", target = "reviews")
    Car toCar(CarResponse carResponse);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "carShowroom.title", target = "showroomTitle")
    @Mapping(source = "carShowroom.id", target = "showroomId")
    @Mapping(source = "clients", target = "clients")
    @Mapping(source = "reviews", target = "reviews")
    CarResponse toOutputCarDTO(Car car);

    List<CarResponse> toOutputCarDTOs(List<Car> cars);

    @Mapping(source = "categoryId", target = "category.id")
    Car toCar(UpdateCarRequest updateCarRequest);

    @Mapping(source = "category.id", target = "categoryId")
    UpdateCarRequest toUpdateCarDTO(Car car);

    UpdateCarRequest toUpdateCarDTO(CarResponse carResponse);
}

