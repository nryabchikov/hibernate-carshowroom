package ru.clevertec.carshowroom.repository.carshowroom;

import ru.clevertec.carshowroom.entity.CarShowroomEntity;

import java.util.List;
import java.util.Optional;

public interface CarShowroomRepository {
    CarShowroomEntity addCarShowroom(CarShowroomEntity carShowroomEntity);

    List<CarShowroomEntity> findAll();

    Optional<CarShowroomEntity> findCarShowroomById(Long id);

    Optional<CarShowroomEntity> update(CarShowroomEntity updatedCarShowroomEntity);

    boolean deleteById(Long id);
}
