package ru.clevertec.carshowroom.dto.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.carshowroom.dto.client.ClientShortResponse;
import ru.clevertec.carshowroom.dto.review.ReviewShortResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponse {
    @NotNull(message = "ID should not be null.")
    @Positive(message = "ID should be a positive number.")
    private Long id;

    @NotBlank(message = "Model should not be blank.")
    private String model;

    @NotBlank(message = "Brand should not be blank.")
    private String brand;

    @NotNull(message = "Production date should be provided.")
    @PastOrPresent(message = "Production date should be in the past or today.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate productionDate;

    @NotNull(message = "Price should not be null.")
    @DecimalMin(message = "Price should be positive.",
            value = "0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Category ID should not be null.")
    @Positive(message = "Category ID should be a positive number.")
    private Long categoryId;

    @NotBlank(message = "Category name should not be blank.")
    private String categoryName;

    @NotNull(message = "CarShowroom ID should not be null.")
    @Positive(message = "CarShowroom ID should be a positive number.")
    private Long showroomId;

    @NotBlank(message = "CarShowroom title should not be blank.")
    private String showroomTitle;

    @JsonManagedReference
    private List<ClientShortResponse> clients;

    @JsonManagedReference
    private List<ReviewShortResponse> reviews;
}
