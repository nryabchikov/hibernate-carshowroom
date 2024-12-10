package ru.clevertec.carshowroom.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateClientRequest {
    @NotNull(message = "ID should not be null.")
    @Positive(message = "ID must be a positive number.")
    private Long id;

    @NotBlank(message = "Name should not be blank.")
    private String name;

    @NotNull(message = "Contacts should not be null.")
    private Set<String> contacts;

    @NotNull(message = "Date of registration should be provided.")
    @PastOrPresent(message = "Date of registration should be in the past or today.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfRegistration;
}
