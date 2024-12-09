package ru.clevertec.carshowroom.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewShortResponse {
    @NotBlank(message = "Text should not be blank.")
    private String text;

    @NotNull(message = "Rating should not be null.")
    @Range(min = 1, max = 5, message = "Rating must be between 1 and 5.")
    private int rating;
}
