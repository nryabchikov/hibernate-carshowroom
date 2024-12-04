package ru.clevertec.carshowroom.service.carshowroom.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.carshowroom.dto.carshowroom.AddCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.OutputCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomDTO;
import ru.clevertec.carshowroom.entity.CarShowroomEntity;
import ru.clevertec.carshowroom.exception.CarShowroomNotFoundException;
import ru.clevertec.carshowroom.mapper.CarShowroomMapper;
import ru.clevertec.carshowroom.repository.carshowroom.CarShowroomRepository;
import ru.clevertec.carshowroom.service.carshowroom.CarShowroomService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CarShowroomServiceImpl implements CarShowroomService {
    private final CarShowroomRepository carShowroomRepository;
    private final CarShowroomMapper carShowroomMapper;

    @Override
    public AddCarShowroomDTO addCarShowroom(AddCarShowroomDTO addCarShowroomDTO) {
        CarShowroomEntity carShowroomEntity
                = carShowroomRepository.addCarShowroom(carShowroomMapper.toCarShowroomEntity(addCarShowroomDTO));
        return carShowroomMapper.toAddCarShowroomDTO(carShowroomEntity);
    }

    @Override
    public List<OutputCarShowroomDTO> findAll() {
        return carShowroomMapper.toOutputCarShowroomDTOs(carShowroomRepository.findAll());
    }

    @Override
    public OutputCarShowroomDTO findById(Long id) {
        Optional<CarShowroomEntity> optionalCarShowroomEntity = carShowroomRepository.findCarShowroomById(id);
        if (optionalCarShowroomEntity.isPresent()) {
            return carShowroomMapper.toOutputCarShowroomDTO(optionalCarShowroomEntity.get());
        } else {
            throw CarShowroomNotFoundException.byId(id);
        }
    }

    @Override
    public UpdateCarShowroomDTO update(UpdateCarShowroomDTO updateCarShowroomDTO) {
        Optional<CarShowroomEntity> optionalCarShowroomEntity
                = carShowroomRepository.update(carShowroomMapper.toCarShowroomEntity(updateCarShowroomDTO));
        if (optionalCarShowroomEntity.isPresent()) {
            return carShowroomMapper.toUpdateCarShowroomDTO(optionalCarShowroomEntity.get());
        } else {
            throw CarShowroomNotFoundException.byId(updateCarShowroomDTO.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (carShowroomRepository.deleteById(id)) {
            return true;
        } else {
            throw CarShowroomNotFoundException.byId(id);
        }
    }
}
