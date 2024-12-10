package ru.clevertec.carshowroom.service;

import ru.clevertec.carshowroom.dto.car.CarResponse;
import ru.clevertec.carshowroom.dto.client.ClientRequest;
import ru.clevertec.carshowroom.dto.client.ClientResponse;
import ru.clevertec.carshowroom.dto.client.UpdateClientRequest;

import java.util.List;

public interface ClientService {
    ClientRequest addClient(ClientRequest clientRequest);

    List<ClientResponse> findAll();

    ClientResponse findById(Long id);

    UpdateClientRequest update(UpdateClientRequest updateClientRequest);

    void deleteById(Long id);

    void buyCar(ClientResponse clientDTO, CarResponse carDTO);
}
