package ru.clevertec.carshowroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.carshowroom.dto.car.CarResponse;
import ru.clevertec.carshowroom.dto.client.ClientRequest;
import ru.clevertec.carshowroom.dto.client.ClientResponse;
import ru.clevertec.carshowroom.dto.client.UpdateClientRequest;
import ru.clevertec.carshowroom.entity.Car;
import ru.clevertec.carshowroom.entity.Client;
import ru.clevertec.carshowroom.exception.CarNotFoundException;
import ru.clevertec.carshowroom.exception.ClientNotFoundException;
import ru.clevertec.carshowroom.mapper.ClientMapper;
import ru.clevertec.carshowroom.repository.CarRepository;
import ru.clevertec.carshowroom.repository.ClientRepository;
import ru.clevertec.carshowroom.service.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientRequest addClient(ClientRequest clientRequest) {
        return clientMapper.toAddClientDTO(clientRepository.save(clientMapper.toClient(clientRequest)));
    }

    @Override
    @Transactional
    public List<ClientResponse> findAll() {
        return clientMapper.toOutputClientDTOs(clientRepository.findAll());
    }

    @Override
    @Transactional
    public ClientResponse findById(Long id) {
        return clientMapper.toOutputClientDTO(clientRepository.findById(id).orElseThrow(
                () -> ClientNotFoundException.byId(id))
        );
    }

    @Override
    public UpdateClientRequest update(UpdateClientRequest updateClientRequest) {
        Client client = clientRepository.findById(updateClientRequest.getId()).orElseThrow(
                () -> ClientNotFoundException.byId(updateClientRequest.getId())
        );

        client.setName(updateClientRequest.getName());
        client.setContacts(updateClientRequest.getContacts());
        client.setDateOfRegistration(updateClientRequest.getDateOfRegistration());

        return clientMapper.toUpdateClientDTO(clientRepository.save(client));
    }


    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void buyCar(ClientResponse clientDTO, CarResponse carDTO) {
        Car car = carRepository.findById(carDTO.getId()).orElseThrow(
                () -> CarNotFoundException.byId(carDTO.getId())
        );

        Client client = clientRepository.findById(clientDTO.getId()).orElseThrow(
                () -> ClientNotFoundException.byId(clientDTO.getId())
        );

        car.getClients().add(client);
        carRepository.save(car);
    }
}
