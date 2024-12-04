package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.client.AddClientDTO;
import ru.clevertec.carshowroom.dto.client.OutputClientDTO;
import ru.clevertec.carshowroom.dto.client.UpdateClientDTO;
import ru.clevertec.carshowroom.entity.ClientEntity;

import java.util.List;

@Mapper
public interface ClientMapper {
    ClientEntity toClientEntity(AddClientDTO addClientDTO);

    AddClientDTO toAddClientDTO(ClientEntity clientEntity);

    @Mapping(source = "carEntities", target = "cars")
    @Mapping(source = "reviewEntities", target = "reviews")
    OutputClientDTO toOutputClientDTO(ClientEntity clientEntity);

    List<OutputClientDTO> toOutputClientDTOs(List<ClientEntity> clientEntities);

    ClientEntity toClientEntity(UpdateClientDTO updateClientDTO);

    UpdateClientDTO toUpdateClientDTO(ClientEntity clientEntity);

    UpdateClientDTO toUpdateClientDTO(OutputClientDTO outputClientDTO);

    @Mapping(source = "cars", target = "carEntities")
    ClientEntity toClientEntity(OutputClientDTO clientDTO);
}
