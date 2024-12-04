package ru.clevertec.carshowroom.util;

import ru.clevertec.carshowroom.dto.car.AddCarDTO;
import ru.clevertec.carshowroom.dto.car.UpdateCarDTO;
import ru.clevertec.carshowroom.dto.carshowroom.AddCarShowroomDTO;
import ru.clevertec.carshowroom.dto.carshowroom.UpdateCarShowroomDTO;
import ru.clevertec.carshowroom.dto.category.AddCategoryDTO;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryDTO;
import ru.clevertec.carshowroom.dto.client.AddClientDTO;
import ru.clevertec.carshowroom.dto.client.UpdateClientDTO;
import ru.clevertec.carshowroom.dto.review.AddReviewDTO;
import ru.clevertec.carshowroom.dto.review.UpdateReviewDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class TestData {
    private TestData() {
    }

    public static AddCarDTO createAddCarDTO() {
        return AddCarDTO.builder()
                .model("Camry")
                .brand("Toyota")
                .year(2022)
                .price(30000)
                .categoryId(1L)
                .build();
    }

    public static UpdateCarDTO createUpdateCarDTO() {
        return UpdateCarDTO.builder()
                .id(8L)
                .model("A4")
                .brand("AUDI")
                .year(2020)
                .price(300200)
                .categoryId(1L)
                .build();
    }

    public static AddCarShowroomDTO createAddCarShowroomDTO() {
        return AddCarShowroomDTO.builder()
                .title("Showroom 1")
                .address("Mogilevska 107")
                .build();
    }

    public static UpdateCarShowroomDTO createUpdateCarShowroomDTO() {
        return UpdateCarShowroomDTO.builder()
                .id(1L)
                .title("Showroom 2")
                .address("Mogilevska 123")
                .build();
    }

    public static AddCategoryDTO createAddCategoryDTO() {
        return AddCategoryDTO.builder()
                .name("Cabriolet")
                .build();

    }

    public static UpdateCategoryDTO createUpdateCategoryDTO() {
        return UpdateCategoryDTO.builder()
                .id(4L)
                .name("Cabriolet1")
                .build();
    }

    public static AddClientDTO createAddClientDTO() {
        return AddClientDTO.builder()
                .name("Nikita")
                .dateOfRegistration(new Date())
                .contacts(new HashSet<>(List.of("123-45-67", "nryabchikov24@gmail.com")))
                .build();
    }

    public static UpdateClientDTO createUpdateClientDTO() {
        return UpdateClientDTO.builder()
                .id(7L)
                .name("Nikita")
                .dateOfRegistration(new Date())
                .contacts(new HashSet<>(List.of("123-45-678", "nryabchikov24@gmail.com")))
                .build();
    }

    public static AddReviewDTO createAddReviewDTO(UpdateCarDTO updateCarDTO, UpdateClientDTO updateClientDTO) {
        return AddReviewDTO.builder()
                .text("Good car")
                .rating(5)
                .car(updateCarDTO)
                .client(updateClientDTO)
                .build();
    }

    public static UpdateReviewDTO createUpdateReviewDTO(UpdateCarDTO updateCarDTO, UpdateClientDTO updateClientDTO) {
        return UpdateReviewDTO.builder()
                .id(4L)
                .text("Bad car")
                .rating(1)
                .car(updateCarDTO)
                .client(updateClientDTO)
                .build();
    }
}
