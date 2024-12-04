package ru.clevertec.carshowroom.service.carshowroom;

import ru.clevertec.carshowroom.dto.carshowroom.AddCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.OutputCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomDTO;

import java.util.List;

public interface CarShowroomService {

    AddCarShowroomDTO addCarShowroom(AddCarShowroomDTO addCarShowroomDTO);

    List<OutputCarShowroomDTO> findAll();

    OutputCarShowroomDTO findById(Long id);

    UpdateCarShowroomDTO update(UpdateCarShowroomDTO updateCarShowroomDTO);

    boolean deleteById(Long id);
}
