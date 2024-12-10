package ru.clevertec.carshowroom.service;

import ru.clevertec.carshowroom.dto.category.CategoryRequest;
import ru.clevertec.carshowroom.dto.category.CategoryResponse;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
    CategoryRequest addCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    UpdateCategoryRequest update(UpdateCategoryRequest updateCategoryRequest);

    void deleteById(Long id);
}
