package ru.clevertec.carshowroom.service;

import ru.clevertec.carshowroom.dto.car.CarRequest;
import ru.clevertec.carshowroom.dto.car.CarResponse;
import ru.clevertec.carshowroom.dto.car.UpdateCarRequest;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomResponse;

import java.util.List;

public interface CarService {

    CarRequest addCar(CarRequest carRequest);

    List<CarResponse> findAll();

    CarResponse findById(Long id);

    UpdateCarRequest update(UpdateCarRequest updatedCarDTO);

    void deleteById(Long id);

    void assignCarToShowroom(CarResponse carResponse, CarShowroomResponse carShowroomResponse);

    List<CarResponse> findCarsByFilters(String brand, Integer minPrice, Integer maxPrice);

    List<CarResponse> findCarsSortedByPrice(boolean ascending);

    List<CarResponse> findCarsWithPagination(int pageNumber, int pageSize);
}
