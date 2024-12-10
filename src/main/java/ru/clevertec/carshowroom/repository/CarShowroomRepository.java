package ru.clevertec.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clevertec.carshowroom.entity.CarShowroom;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {
    @Query("SELECT c FROM CarShowroom c LEFT JOIN FETCH c.cars")
    List<CarShowroom> findAll();

    @Query("SELECT c FROM CarShowroom c LEFT JOIN FETCH c.cars WHERE c.id = :id")
    Optional<CarShowroom> findById(@Param("id") Long id);
}