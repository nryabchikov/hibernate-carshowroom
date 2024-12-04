package ru.clevertec.carshowroom.dto.carshowroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.carshowroom.dto.car.OutputCarShortDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputCarShowroomDTO {
    private Long id;
    private String title;
    private String address;
    private List<OutputCarShortDTO> cars;
}
