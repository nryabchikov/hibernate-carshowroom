package ru.clevertec.carshowroom.repository.category;

import ru.clevertec.carshowroom.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    CategoryEntity addCategory(CategoryEntity categoryEntity);

    List<CategoryEntity> findAll();

    Optional<CategoryEntity> findCategoryById(Long id);

    Optional<CategoryEntity> update(CategoryEntity updatedCategoryEntity);

    boolean deleteById(Long id);
}
