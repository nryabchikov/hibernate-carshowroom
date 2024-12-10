package ru.clevertec.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clevertec.carshowroom.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.cars")
    List<Category> findAll();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.cars WHERE c.id = :id")
    Optional<Category> findById(@Param("id") Long id);
}