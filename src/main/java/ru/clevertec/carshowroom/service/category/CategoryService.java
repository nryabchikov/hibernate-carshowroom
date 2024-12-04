package ru.clevertec.carshowroom.service.category;

import ru.clevertec.carshowroom.dto.category.AddCategoryDTO;
import ru.clevertec.carshowroom.dto.category.OutputCategoryDTO;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {
    AddCategoryDTO addCategory(AddCategoryDTO addCategoryDTO);

    List<OutputCategoryDTO> findAll();

    OutputCategoryDTO findById(Long id);

    UpdateCategoryDTO update(UpdateCategoryDTO updateCategoryDTO);

    boolean deleteById(Long id);
}
