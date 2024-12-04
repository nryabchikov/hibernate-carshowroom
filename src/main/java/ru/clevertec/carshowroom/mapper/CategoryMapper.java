package ru.clevertec.carshowroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.carshowroom.dto.category.AddCategoryDTO;
import ru.clevertec.carshowroom.dto.category.OutputCategoryDTO;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryDTO;
import ru.clevertec.carshowroom.entity.CategoryEntity;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryEntity toCategoryEntity(AddCategoryDTO addCategoryDTO);

    AddCategoryDTO toAddCategoryDTO(CategoryEntity categoryEntity);

    @Mapping(source = "carEntities", target = "cars")
    OutputCategoryDTO toOutputCategoryDTO(CategoryEntity categoryEntity);

    List<OutputCategoryDTO> toOutputCategoryDTOs(List<CategoryEntity> categoryEntities);

    CategoryEntity toCategoryEntity(UpdateCategoryDTO updateCategoryDTO);

    UpdateCategoryDTO toUpdateCategoryDTO(CategoryEntity categoryEntity);
}

