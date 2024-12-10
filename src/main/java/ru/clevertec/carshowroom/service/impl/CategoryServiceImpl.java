package ru.clevertec.carshowroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.carshowroom.dto.category.CategoryRequest;
import ru.clevertec.carshowroom.dto.category.CategoryResponse;
import ru.clevertec.carshowroom.dto.category.UpdateCategoryRequest;
import ru.clevertec.carshowroom.entity.Category;
import ru.clevertec.carshowroom.exception.CategoryNotFoundException;
import ru.clevertec.carshowroom.mapper.CategoryMapper;
import ru.clevertec.carshowroom.repository.CategoryRepository;
import ru.clevertec.carshowroom.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryRequest addCategory(CategoryRequest categoryRequest) {
        return categoryMapper.toAddCategoryDTO(categoryRepository.save(
                categoryMapper.toCategory(categoryRequest))
        );
    }

    @Override
    //@Transactional ?
    public List<CategoryResponse> findAll() {
        return categoryMapper.toOutputCategoryDTOs(categoryRepository.findAll());
    }

    @Override
    //@Transactional ?
    public CategoryResponse findById(Long id) {
        return categoryMapper.toOutputCategoryDTO(categoryRepository.findById(id).orElseThrow(
                () -> CategoryNotFoundException.byId(id))
        );
    }

    @Override
    public UpdateCategoryRequest update(UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(updateCategoryRequest.getId()).orElseThrow(
                () -> CategoryNotFoundException.byId(updateCategoryRequest.getId())
        );

        category.setName(updateCategoryRequest.getName());
        return categoryMapper.toUpdateCategoryDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
