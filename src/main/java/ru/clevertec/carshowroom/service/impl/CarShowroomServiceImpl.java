package ru.clevertec.carshowroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomRequest;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomResponse;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomRequest;
import ru.clevertec.carshowroom.entity.CarShowroom;
import ru.clevertec.carshowroom.exception.CarShowroomNotFoundException;
import ru.clevertec.carshowroom.mapper.CarShowroomMapper;
import ru.clevertec.carshowroom.repository.CarShowroomRepository;
import ru.clevertec.carshowroom.service.CarShowroomService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarShowroomServiceImpl implements CarShowroomService {
    private final CarShowroomRepository carShowroomRepository;
    private final CarShowroomMapper carShowroomMapper;

    @Override
    public CarShowroomRequest addCarShowroom(CarShowroomRequest carShowroomRequest) {
        return carShowroomMapper.toAddCarShowroomDTO(carShowroomRepository.save(
                carShowroomMapper.toCarShowroom(carShowroomRequest)
        ));
    }

    @Override
    @Transactional
    public List<CarShowroomResponse> findAll() {
        return carShowroomMapper.toOutputCarShowroomDTOs(carShowroomRepository.findAll());
    }

    @Override
    @Transactional
    public CarShowroomResponse findById(Long id) {
        return carShowroomMapper.toOutputCarShowroomDTO(carShowroomRepository.findById(id).orElseThrow(
                () -> CarShowroomNotFoundException.byId(id))
        );
    }

    @Override
    public UpdateCarShowroomRequest update(UpdateCarShowroomRequest updateCarShowroomRequest) {
        CarShowroom carShowroom = carShowroomRepository.findById(updateCarShowroomRequest.getId()).orElseThrow(
                () -> CarShowroomNotFoundException.byId(updateCarShowroomRequest.getId())
        );

        carShowroom.setTitle(updateCarShowroomRequest.getTitle());
        carShowroom.setAddress(updateCarShowroomRequest.getAddress());

        return carShowroomMapper.toUpdateCarShowroomDTO(carShowroomRepository.save(carShowroom));
    }

    @Override
    public void deleteById(Long id) {
        carShowroomRepository.deleteById(id);
    }
}
