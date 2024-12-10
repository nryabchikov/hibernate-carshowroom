package ru.clevertec.carshowroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.carshowroom.dto.car.CarRequest;
import ru.clevertec.carshowroom.dto.car.CarResponse;
import ru.clevertec.carshowroom.dto.car.UpdateCarRequest;
import ru.clevertec.carshowroom.dto.carshowroom.CarShowroomResponse;
import ru.clevertec.carshowroom.entity.Car;
import ru.clevertec.carshowroom.entity.CarShowroom;
import ru.clevertec.carshowroom.exception.CarNotFoundException;
import ru.clevertec.carshowroom.exception.CategoryNotFoundException;
import ru.clevertec.carshowroom.mapper.CarMapper;
import ru.clevertec.carshowroom.mapper.CarShowroomMapper;
import ru.clevertec.carshowroom.repository.CarRepository;
import ru.clevertec.carshowroom.repository.CategoryRepository;
import ru.clevertec.carshowroom.service.CarService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;
    private final CarMapper carMapper;
    private final CarShowroomMapper carShowroomMapper;

    @Override
    public CarRequest addCar(CarRequest carRequest) {
        return carMapper.toAddCarDTO(carRepository.save(carMapper.toCar(carRequest)));
    }

    @Override
    @Transactional
    public List<CarResponse> findAll() {
        return carMapper.toOutputCarDTOs(carRepository.findAll());
    }

    @Override
    @Transactional
    public CarResponse findById(Long id) {
        return carMapper.toOutputCarDTO(carRepository.findById(id).orElseThrow(() -> CarNotFoundException.byId(id)));
    }

    @Override
    public UpdateCarRequest update(UpdateCarRequest updatedCarDTO) {
        Car car = carRepository.findById(updatedCarDTO.getId()).orElseThrow(
                () -> CarNotFoundException.byId(updatedCarDTO.getId())
        );

        car.setModel(updatedCarDTO.getModel());
        car.setBrand(updatedCarDTO.getBrand());
        car.setProductionDate(updatedCarDTO.getProductionDate());
        car.setPrice(updatedCarDTO.getPrice());
        car.setCategory(categoryRepository.findById(updatedCarDTO.getCategoryId()).orElseThrow(
                () -> CategoryNotFoundException.byId(updatedCarDTO.getCategoryId())
        ));

        return carMapper.toUpdateCarDTO(carRepository.save(car));
    }


    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void assignCarToShowroom(CarResponse carResponse, CarShowroomResponse carShowroomResponse) {
        Car car = carMapper.toCar(carResponse);
        CarShowroom carShowroom = carShowroomMapper.toCarShowroom(carShowroomResponse);
        car.setCarShowroom(carShowroom);
        carRepository.save(car);
    }

    @Override
    @Transactional
    public List<CarResponse> findCarsByFilters(String brand, Integer minPrice, Integer maxPrice) {
        return carMapper.toOutputCarDTOs(carRepository.findCarsByFilters(brand, minPrice, maxPrice));
    }

    @Override
    @Transactional
    public List<CarResponse> findCarsSortedByPrice(boolean ascending) {
        if (ascending) {
            return carMapper.toOutputCarDTOs(carRepository.findCarsSortedByPriceAsc());
        } else {
            return carMapper.toOutputCarDTOs(carRepository.findCarsSortedByPriceDesc());
        }
    }

    @Override
    @Transactional
    public List<CarResponse> findCarsWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return carMapper.toOutputCarDTOs(carRepository.findAll(pageable).getContent());
    }
}

