package ru.clevertec.carshowroom.dto.carshowroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCarShowroomDTO {
    private Long id;
    private String title;
    private String address;
}
