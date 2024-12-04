package ru.clevertec.carshowroom.service.category.impl;

import lombok.RequiredArgsConstructor;
import ru.clevertec.carshowroom.dto.category.AddCategoryDTO;
import ru.clevertec.carshowroom.dto.category.OutputCategoryDTO;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryDTO;
import ru.clevertec.carshowroom.entity.CategoryEntity;
import ru.clevertec.carshowroom.exception.CategoryNotFoundException;
import ru.clevertec.carshowroom.mapper.CategoryMapper;
import ru.clevertec.carshowroom.repository.category.CategoryRepository;
import ru.clevertec.carshowroom.service.category.CategoryService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public AddCategoryDTO addCategory(AddCategoryDTO addCategoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.addCategory(categoryMapper.toCategoryEntity(addCategoryDTO));
        return categoryMapper.toAddCategoryDTO(categoryEntity);
    }

    @Override
    public List<OutputCategoryDTO> findAll() {
        return categoryMapper.toOutputCategoryDTOs(categoryRepository.findAll());
    }

    @Override
    public OutputCategoryDTO findById(Long id) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findCategoryById(id);
        if (optionalCategoryEntity.isPresent()) {
            return categoryMapper.toOutputCategoryDTO(optionalCategoryEntity.get());
        } else {
            throw CategoryNotFoundException.byId(id);
        }
    }

    @Override
    public UpdateCategoryDTO update(UpdateCategoryDTO updateCategoryDTO) {
        Optional<CategoryEntity> optionalCategoryEntity
                = categoryRepository.update(categoryMapper.toCategoryEntity(updateCategoryDTO));
        if (optionalCategoryEntity.isPresent()) {
            return categoryMapper.toUpdateCategoryDTO(optionalCategoryEntity.get());
        } else {
            throw CategoryNotFoundException.byId(updateCategoryDTO.getId());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (categoryRepository.deleteById(id)) {
            return true;
        } else {
            throw CategoryNotFoundException.byId(id);
        }
    }
}
