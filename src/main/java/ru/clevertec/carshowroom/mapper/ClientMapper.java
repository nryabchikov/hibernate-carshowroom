package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.client.ClientRequest;
import ru.clevertec.carshowroom.dto.client.ClientResponse;
import ru.clevertec.carshowroom.dto.client.UpdateClientRequest;
import ru.clevertec.carshowroom.entity.Client;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toClient(ClientRequest clientRequest);

    ClientRequest toAddClientDTO(Client client);

    @Mapping(source = "cars", target = "cars")
    @Mapping(source = "reviews", target = "reviews")
    ClientResponse toOutputClientDTO(Client client);

    List<ClientResponse> toOutputClientDTOs(List<Client> clients);

    Client toClient(UpdateClientRequest updateClientRequest);

    UpdateClientRequest toUpdateClientDTO(Client client);

    UpdateClientRequest toUpdateClientDTO(ClientResponse clientResponse);

    @Mapping(source = "cars", target = "cars")
    Client toClient(ClientResponse clientDTO);
}
