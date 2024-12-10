package ru.clevertec.carshowroom.dto.carshowroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.carshowroom.dto.car.CarShortResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarShowroomResponse {
    @NotNull(message = "ID should not be null.")
    @Positive(message = "ID should be a positive number.")
    private Long id;

    @NotBlank(message = "Title should not be blank.")
    private String title;

    @NotBlank(message = "Address should not be blank.")
    private String address;

    private List<CarShortResponse> cars;
}
