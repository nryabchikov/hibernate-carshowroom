package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.category.CategoryRequest;
import ru.clevertec.carshowroom.dto.category.CategoryResponse;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryRequest;
import ru.clevertec.carshowroom.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);

    CategoryRequest toAddCategoryDTO(Category category);

    @Mapping(source = "cars", target = "cars")
    CategoryResponse toOutputCategoryDTO(Category category);

    List<CategoryResponse> toOutputCategoryDTOs(List<Category> categoryEntities);

    Category toCategory(UpdateCategoryRequest updateCategoryRequest);

    UpdateCategoryRequest toUpdateCategoryDTO(Category category);
}

