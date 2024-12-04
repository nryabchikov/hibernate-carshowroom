package ru.clevertec.carshowroom.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.carshowroom.dto.car.UpdateCarDTO;
import ru.clevertec.carshowroom.dto.client.UpdateClientDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReviewDTO {
    private Long id;
    private String text;
    private int rating;
    private UpdateCarDTO car;
    private UpdateClientDTO client;
}
