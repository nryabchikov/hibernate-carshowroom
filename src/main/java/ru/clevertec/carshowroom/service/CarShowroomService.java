package ru.clevertec.carshowroom.service;

import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomRequest;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomResponse;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomRequest;

import java.util.List;

public interface CarShowroomService {

    CarShowroomRequest addCarShowroom(CarShowroomRequest carShowroomRequest);

    List<CarShowroomResponse> findAll();

    CarShowroomResponse findById(Long id);

    UpdateCarShowroomRequest update(UpdateCarShowroomRequest updateCarShowroomRequest);

    void deleteById(Long id);
}
