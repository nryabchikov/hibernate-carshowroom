package ru.clevertec.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clevertec.carshowroom.entity.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT c FROM Review c JOIN FETCH c.car JOIN FETCH c.client")
    List<Review> findAll();

    @Query("SELECT c FROM Review c JOIN FETCH c.car JOIN FETCH c.client WHERE c.id = :id")
    Optional<Review> findById(@Param("id") Long id);
}