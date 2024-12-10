package ru.clevertec.carshowroom.dto.carshowroom;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarShowroomRequest {
    @NotBlank(message = "Title should not be blank.")
    private String title;

    @NotBlank(message = "Address should not be blank.")
    private String address;
}
