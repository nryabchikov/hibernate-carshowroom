package ru.clevertec.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clevertec.carshowroom.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("""
            SELECT c FROM Client c
            LEFT JOIN FETCH c.cars
            LEFT JOIN FETCH c.reviews
            LEFT JOIN FETCH c.contacts
            """)
    List<Client> findAll();

    @Query("""
            SELECT c FROM Client c
            LEFT JOIN FETCH c.cars
            LEFT JOIN FETCH c.reviews
            LEFT JOIN FETCH c.contacts
            WHERE c.id = :id
                 """)
    Optional<Client> findById(@Param("id") Long id);
}