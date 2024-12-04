package ru.clevertec.carshowroom.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputClientShortDTO {
    private String name;
    private Date registrationDate;
}
