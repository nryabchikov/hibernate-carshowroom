package ru.clevertec.carshowroom.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCarDTO {
    private String model;
    private String brand;
    private int year;
    private int price;
    private Long categoryId;
}
