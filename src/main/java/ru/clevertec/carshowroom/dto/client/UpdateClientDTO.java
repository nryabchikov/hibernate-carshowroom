package ru.clevertec.carshowroom.dto.client;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateClientDTO {
    private Long id;
    private String name;
    private Set<String> contacts;
    private Date dateOfRegistration;
}
