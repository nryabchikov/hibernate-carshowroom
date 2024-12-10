package ru.clevertec.carshowroom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clevertec.carshowroom.entity.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("""
            SELECT c FROM Car c
            JOIN FETCH c.category
            LEFT JOIN FETCH c.carShowroom
            LEFT JOIN FETCH c.clients
            LEFT JOIN FETCH c.reviews
            """)
    List<Car> findAll();

    @Query("""
            SELECT c FROM Car c
            JOIN FETCH c.category
            LEFT JOIN FETCH c.carShowroom
            LEFT JOIN FETCH c.clients
            LEFT JOIN FETCH c.reviews
            WHERE c.id = :id
            """)
    Optional<Car> findById(@Param("id") Long id);

    @Query("""
            SELECT c FROM Car c
            JOIN FETCH c.category
            LEFT JOIN FETCH c.carShowroom
            LEFT JOIN FETCH c.clients
            LEFT JOIN FETCH c.reviews
            WHERE (:brand IS NULL OR c.brand = :brand)
            AND (:minPrice IS NULL OR c.price >= :minPrice)
            AND (:maxPrice IS NULL OR c.price <= :maxPrice)
            """)
    List<Car> findCarsByFilters(
            @Param("brand") String brand,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice
    );

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.clients LEFT JOIN FETCH c.reviews ORDER BY c.price ASC")
    List<Car> findCarsSortedByPriceAsc();

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.clients LEFT JOIN FETCH c.reviews ORDER BY c.price DESC")
    List<Car> findCarsSortedByPriceDesc();

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.clients LEFT JOIN FETCH c.reviews")
    Page<Car> findAll(Pageable pageable);
}
