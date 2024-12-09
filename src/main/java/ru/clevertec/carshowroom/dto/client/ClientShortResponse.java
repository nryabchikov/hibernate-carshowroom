package ru.clevertec.carshowroom.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientShortResponse {
    @NotBlank(message = "Name should not be blank.")
    private String name;

    @NotNull(message = "Contacts should not be null.")
    private Set<String> contacts;
}
