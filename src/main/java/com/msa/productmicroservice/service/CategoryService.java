package com.msa.productmicroservice.service;

import com.msa.productmicroservice.domain.Category;
import com.msa.productmicroservice.dto.CategoryDto;
import com.msa.productmicroservice.exception.BadReqException;
import com.msa.productmicroservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public List<CategoryDto.show> searchCategoryList(boolean isUse){
       return categoryRepository.findByIsUse(isUse)
                .stream()
                .map(Category::convertShowDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDto.show createCategory(CategoryDto.create create){
        Category category = categoryRepository.save(Category.create(create));
        return category.convertShowDto();
    }

    @Transactional
    public CategoryDto.show removeCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new BadReqException("잘못된 요청입니다."));
        categoryRepository.delete(category);
        return category.convertShowDto();
    }
}
