package ru.clevertec.carshowroom.service.client.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.carshowroom.dto.car.OutputCarDTO;
import ru.clevertec.carshowroom.dto.client.AddClientDTO;
import ru.clevertec.carshowroom.dto.client.OutputClientDTO;
import ru.clevertec.carshowroom.dto.client.UpdateClientDTO;
import ru.clevertec.carshowroom.entity.CarEntity;
import ru.clevertec.carshowroom.entity.ClientEntity;
import ru.clevertec.carshowroom.exception.ClientNotFoundException;
import ru.clevertec.carshowroom.mapper.CarMapper;
import ru.clevertec.carshowroom.mapper.ClientMapper;
import ru.clevertec.carshowroom.repository.client.ClientRepository;
import ru.clevertec.carshowroom.service.client.ClientService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CarMapper carMapper;

    @Override
    public AddClientDTO addClient(AddClientDTO addClientDTO) {
        ClientEntity clientEntity = clientRepository.addClient(clientMapper.toClientEntity(addClientDTO));
        return clientMapper.toAddClientDTO(clientEntity);
    }

    @Override
    public List<OutputClientDTO> findAll() {
        return clientMapper.toOutputClientDTOs(clientRepository.findAll());
    }

    @Override
    public OutputClientDTO findById(Long id) {
        Optional<ClientEntity> optionalClientEntity = clientRepository.findClientById(id);
        if (optionalClientEntity.isPresent()) {
            return clientMapper.toOutputClientDTO(optionalClientEntity.get());
        } else {
            throw ClientNotFoundException.byId(id);
        }
    }

    @Override
    public UpdateClientDTO update(UpdateClientDTO updateClientDTO) {
        Optional<ClientEntity> optionalClientEntity
                = clientRepository.update(clientMapper.toClientEntity(updateClientDTO));
        if (optionalClientEntity.isPresent()) {
            return clientMapper.toUpdateClientDTO(optionalClientEntity.get());
        } else {
            throw ClientNotFoundException.byId(updateClientDTO.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (clientRepository.deleteById(id)) {
            return true;
        } else {
            throw ClientNotFoundException.byId(id);
        }
    }

    @Override
    public void buyCar(OutputClientDTO clientDTO, OutputCarDTO carDTO) {
        ClientEntity clientEntity = clientMapper.toClientEntity(clientDTO);
        CarEntity carEntity = carMapper.toCarEntity(carDTO);
        clientRepository.buyCar(clientEntity, carEntity);
    }
}
