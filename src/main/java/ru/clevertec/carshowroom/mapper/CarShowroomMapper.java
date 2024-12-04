package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.carshowroom.AddCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.OutputCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomDTO;
import ru.clevertec.carshowroom.entity.CarShowroomEntity;

import java.util.List;

@Mapper
public interface CarShowroomMapper {
    CarShowroomEntity toCarShowroomEntity(AddCarShowroomDTO addCarShowroomDTO);

    AddCarShowroomDTO toAddCarShowroomDTO(CarShowroomEntity carShowroomEntity);

    @Mapping(source = "carEntities", target = "cars")
    OutputCarShowroomDTO toOutputCarShowroomDTO(CarShowroomEntity carShowroomEntity);

    List<OutputCarShowroomDTO> toOutputCarShowroomDTOs(List<CarShowroomEntity> carShowroomEntities);

    CarShowroomEntity toCarShowroomEntity(UpdateCarShowroomDTO updateCarShowroomDTO);
    UpdateCarShowroomDTO toUpdateCarShowroomDTO(CarShowroomEntity carShowroomEntity);

    CarShowroomEntity toCarShowroomEntity(OutputCarShowroomDTO outputCarShowroomDTO);
}
