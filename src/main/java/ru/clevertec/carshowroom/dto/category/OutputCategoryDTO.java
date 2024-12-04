package ru.clevertec.carshowroom.dto.category;

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
public class OutputCategoryDTO {
    private Long id;
    private String name;
    private List<OutputCarShortDTO> cars;
}
