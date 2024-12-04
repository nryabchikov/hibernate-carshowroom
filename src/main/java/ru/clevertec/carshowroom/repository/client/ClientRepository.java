package ru.clevertec.carshowroom.repository.client;

import ru.clevertec.carshowroom.entity.CarEntity;
import ru.clevertec.carshowroom.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    ClientEntity addClient(ClientEntity clientEntity);

    List<ClientEntity> findAll();

    Optional<ClientEntity> findClientById(Long id);

    Optional<ClientEntity> update(ClientEntity updatedClientEntity);

    boolean deleteById(Long id);

    void buyCar(ClientEntity clientEntity, CarEntity carEntity);
}
