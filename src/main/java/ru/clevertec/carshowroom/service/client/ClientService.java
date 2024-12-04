package ru.clevertec.carshowroom.service.client;

import ru.clevertec.carshowroom.dto.car.OutputCarDTO;
import ru.clevertec.carshowroom.dto.client.AddClientDTO;
import ru.clevertec.carshowroom.dto.client.OutputClientDTO;
import ru.clevertec.carshowroom.dto.client.UpdateClientDTO;

import java.util.List;

public interface ClientService {
    AddClientDTO addClient(AddClientDTO addClientDTO);

    List<OutputClientDTO> findAll();

    OutputClientDTO findById(Long id);

    UpdateClientDTO update(UpdateClientDTO updateClientDTO);

    boolean deleteById(Long id);

    void buyCar(OutputClientDTO clientDTO, OutputCarDTO carDTO);
}
